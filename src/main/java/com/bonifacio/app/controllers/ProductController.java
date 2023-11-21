package com.bonifacio.app.controllers;

import com.bonifacio.app.mappers.ICategoryMapper;
import com.bonifacio.app.mappers.IProductMapper;
import com.bonifacio.app.response.CustomResponse;
import com.bonifacio.app.services.ICategoryService;
import com.bonifacio.app.services.IProductService;
import com.bonifacio.app.services.dao.ProductInDto;
import com.bonifacio.app.services.dao.ProductOutDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/product/")
@Tag(name = "Products")
@AllArgsConstructor
public class ProductController {
        private final IProductService productService;
        private final IProductMapper mapper;
        private final ICategoryService categoryService;
        private final ICategoryMapper categoryMapper;

        /**
         * The index function retrieves all products and converts them into a list of
         * ProductOutDto
         * objects, which are then returned in a ResponseEntity with a custom response.
         * 
         * @return The method is returning a ResponseEntity object with a CustomResponse
         *         object as its
         *         body. The CustomResponse object is built with a message, success
         *         status, and a list of
         *         ProductOutDto objects as its data. The HTTP status code of the
         *         response is set to OK (200).
         */
        @GetMapping("")
        public ResponseEntity<CustomResponse<Object>> index() {
                var products = productService.findAll();
                ArrayList<ProductOutDto> out = new ArrayList<>();
                products.forEach(product -> {
                        out.add(mapper.productToProductOutDto(product));
                });
                return new ResponseEntity<>(CustomResponse.builder()
                                .message("products")
                                .success(true)
                                .data(out)
                                .build(), HttpStatus.OK);
        }

        /**
         * This Java function handles the creation of a new product, validating the
         * input, checking if the
         * category exists, and returning a response with the created product.
         * 
         * @param productInDto The productInDto parameter is an object of type
         *                     ProductInDto. It is
         *                     annotated with @RequestBody, which means that it will be
         *                     deserialized from the request body
         *                     JSON. This object contains the data for creating a new
         *                     product.
         * @param result       result is an object of type BindingResult, which is used
         *                     to hold the result of the
         *                     data binding and validation in Spring MVC. It contains
         *                     information about any errors that
         *                     occurred during the binding and validation process.
         * @return The method is returning a ResponseEntity object.
         */
        @PostMapping("")
        public ResponseEntity<CustomResponse<Object>> store(@Valid @RequestBody ProductInDto productInDto,
                        BindingResult result) {
                if (result.hasErrors())
                        return new ResponseEntity<>(CustomResponse.builder()
                                        .success(false)
                                        .message("Error to Create")
                                        .data(result.getFieldError())
                                        .build(), HttpStatus.BAD_REQUEST);
                var category = categoryService.findById(productInDto.getCategoryId());
                if (category.isEmpty())
                        return new ResponseEntity<>(CustomResponse.builder()
                                        .success(false)
                                        .message("Category Not Found")
                                        .data(null)
                                        .build(), HttpStatus.NOT_FOUND);
                var out = mapper.productInDtoToProduct(productInDto);
                out.setCategory(category.get());
                out = productService.save(out);
                return new ResponseEntity<>(CustomResponse.builder()
                                .success(true)
                                .message("Created")
                                .data(out)
                                .build(), HttpStatus.CREATED);
        }

        /**
         * The function retrieves a product by its ID and returns a response entity with
         * the product
         * details if found, or a not found message if not found.
         * 
         * @param id The "id" parameter is a UUID (Universally Unique Identifier) that
         *           is used to identify
         *           a specific product. It is passed as a path variable in the URL.
         * @return The method is returning a ResponseEntity object with a CustomResponse
         *         object as the body
         *         and an HTTP status code. The CustomResponse object contains
         *         information about the success of the
         *         operation, a message, and the data being returned.
         */
        @GetMapping("{id}")
        public ResponseEntity<CustomResponse<Object>> show(@PathVariable("id") UUID id) {
                var product = productService.findById(id);
                if (product.isEmpty())
                        return new ResponseEntity<>(CustomResponse.builder()
                                        .success(false)
                                        .message("Not Found")
                                        .data(null)
                                        .build(), HttpStatus.NOT_FOUND);
                var category = categoryMapper.categoryToCategoryOutDto(product.get().getCategory());
                var out = mapper.productToProductDetail(product.get(), category);
                return new ResponseEntity<>(CustomResponse.builder()
                                .success(true)
                                .message("Product")
                                .data(out)
                                .build(), HttpStatus.OK);
        }

        /**
         * The above function is a Java method that updates a product based on the
         * provided product ID and
         * input data, returning a custom response entity.
         * 
         * @param productInDto  The `productInDto` parameter is an object of type
         *                      `ProductInDto` which
         *                      represents the input data for updating a product. It
         *                      contains information such as the product
         *                      name, description, price, and category ID.
         * @param id            The "id" parameter is a UUID (Universally Unique
         *                      Identifier) that represents the unique
         *                      identifier of the product to be updated.
         * @param bindingResult The `bindingResult` parameter is an object that holds
         *                      the result of the
         *                      validation process for the `productInDto` object. It
         *                      contains information about any validation
         *                      errors that occurred during the validation process.
         * @return The method is returning a ResponseEntity object with a CustomResponse
         *         object as the
         *         response body. The CustomResponse object contains information about
         *         the success of the update
         *         operation, a message, and the updated product data. The HTTP status
         *         code of the response is also
         *         specified.
         */
        @PutMapping("{id}")
        public ResponseEntity<CustomResponse<Object>> update(@Valid @RequestBody ProductInDto productInDto,
                        @PathVariable("id") UUID id, BindingResult bindingResult) {
                if (bindingResult.hasErrors())
                        return new ResponseEntity<>(CustomResponse.builder()
                                        .success(false)
                                        .message("error to update")
                                        .data(bindingResult.getFieldError())
                                        .build(), HttpStatus.BAD_REQUEST);
                var product = productService.findById(id);
                if (product.isEmpty())
                        return new ResponseEntity<>(CustomResponse.builder()
                                        .success(false)
                                        .message("Not Found")
                                        .data(null)
                                        .build(), HttpStatus.NOT_FOUND);
                var category = categoryService.findById(productInDto.getCategoryId());
                if (category.isEmpty())
                        return new ResponseEntity<>(CustomResponse.builder()
                                        .success(false)
                                        .message("Category Not Found")
                                        .data(null)
                                        .build(), HttpStatus.NOT_FOUND);
                var out = mapper.productInDtoToProduct(productInDto);
                out.setCategory(category.get());
                out.setId(id);
                out = productService.save(out);
                return new ResponseEntity<>(CustomResponse.builder()
                                .success(true)
                                .message("Updated")
                                .data(out)
                                .build(), HttpStatus.OK);
        }

        /**
         * The above function is a Java method that handles a DELETE request to delete a
         * product by its ID
         * and returns a response entity with a custom response indicating whether the
         * deletion was
         * successful or not.
         * 
         * @param id The "id" parameter is a UUID (Universally Unique Identifier) that
         *           represents the unique
         *           identifier of the product to be deleted.
         * @return The method is returning a ResponseEntity object with a CustomResponse
         *         object as the
         *         response body. The CustomResponse object contains information about
         *         the success of the operation,
         *         a message, and data. The HTTP status code returned depends on the
         *         outcome of the operation. If
         *         the product is found and deleted successfully, the status code will
         *         be ACCEPTED (202). If the
         *         product is not found, the status code will be
         */
        @DeleteMapping("{id}")
        public ResponseEntity<CustomResponse<Object>> delete(@PathVariable("id") UUID id) {
                var product = productService.findById(id);
                if (product.isEmpty())
                        return new ResponseEntity<>(CustomResponse.builder()
                                        .success(false)
                                        .message("Not Found")
                                        .data(null)
                                        .build(), HttpStatus.NOT_FOUND);
                productService.delete(product.get());

                return new ResponseEntity<>(CustomResponse.builder()
                                .success(true)
                                .message("Deleted")
                                .data(null)
                                .build(), HttpStatus.ACCEPTED);
        }

}
