package com.bonifacio.app.controllers;

import com.bonifacio.app.mappers.ICategoryMapper;
import com.bonifacio.app.mappers.IProductMapper;
import com.bonifacio.app.response.CustomResponse;
import com.bonifacio.app.services.ICategoryService;
import com.bonifacio.app.services.dao.CategoryInDto;
import com.bonifacio.app.services.dao.CategoryOutDto;
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
@RequestMapping("api/v1/category")
@Tag(name = "Category")
@AllArgsConstructor
public class CategoryController {

    private final ICategoryService categoryService;
    private final ICategoryMapper categoryMapper;
    private final IProductMapper productMapper;

    /**
     * The above function returns a list of categories in the form of a
     * ResponseEntity with a custom
     * response object.
     * 
     * @return The method is returning a ResponseEntity object with a CustomResponse
     *         object as its
     *         body. The CustomResponse object is built with a message, success
     *         status, and data. The
     *         HttpStatus is set to OK.
     */
    @GetMapping("/")
    public ResponseEntity<CustomResponse<Object>> index() {
        ArrayList<CategoryOutDto> out = new ArrayList<>();
        var categories = categoryService.findAll();
        categories.forEach(category -> {
            out.add(categoryMapper.categoryToCategoryOutDto(category));
        });
        return new ResponseEntity<>(CustomResponse.builder()
                .message("categories")
                .success(true)
                .data(out)
                .build(), HttpStatus.OK);
    }

    /**
     * This function saves a category object, performs validation, and returns a
     * response entity with
     * the saved category.
     * 
     * @param category      The "category" parameter is of type CategoryInDto, which
     *                      is a DTO (Data Transfer
     *                      Object) used for transferring data between the client
     *                      and the server. It represents the input
     *                      data for creating a new category.
     * @param bindingResult The `bindingResult` parameter is an object that holds
     *                      the result of the
     *                      validation process for the `category` object. It
     *                      contains information about any validation errors
     *                      that occurred during the validation process.
     * @return The method is returning a ResponseEntity object.
     */
    @PostMapping("/")
    public ResponseEntity<CustomResponse<Object>> save(@Valid @RequestBody CategoryInDto category,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(CustomResponse.builder()
                    .message("Error to create")
                    .success(false)
                    .data(bindingResult.getFieldError())
                    .build(), HttpStatus.BAD_REQUEST);
        }
        var cate = categoryMapper.categoryInDtoToCategory(category);
        cate = categoryService.save(cate);
        var out = categoryMapper.categoryToCategoryOutDto(cate);
        return new ResponseEntity<>(CustomResponse.builder()
                .message("category")
                .success(true)
                .data(out)
                .build(), HttpStatus.CREATED);
    }

    /**
     * The function retrieves a category by its ID and returns a response entity
     * containing the
     * category details and associated products.
     * 
     * @param id The "id" parameter is a UUID (Universally Unique Identifier) that
     *           is used to identify
     *           a specific category.
     * @return The method is returning a ResponseEntity object with a CustomResponse
     *         object as the
     *         response body. The CustomResponse object contains a message, success
     *         status, and data. The HTTP
     *         status code returned depends on the condition. If the category is
     *         found, it returns
     *         HttpStatus.OK (200), and if the category is not found, it returns
     *         HttpStatus.NOT_FOUND (404).
     */
    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Object>> show(@PathVariable("id") UUID id) {
        var category = categoryService.findById(id);
        if (category.isEmpty())
            return new ResponseEntity<>(CustomResponse.builder()
                    .message("Not Found")
                    .success(false)
                    .data(null)
                    .build(), HttpStatus.NOT_FOUND);
        ArrayList<ProductOutDto> products = new ArrayList<>();
        category.get().getProducts().forEach(product -> {
            products.add(productMapper.productToProductOutDto(product));
        });
        var out = categoryMapper.categoryToCategoryDetail(category.get());
        out.setProducts(products);
        return new ResponseEntity<>(CustomResponse.builder()
                .message("category")
                .success(true)
                .data(out)
                .build(), HttpStatus.OK);

    }

    /**
     * This function updates a category with the given ID using the provided
     * category input DTO, and
     * returns a custom response entity.
     * 
     * @param categoryInDto The categoryInDto parameter is an object of type
     *                      CategoryInDto, which is
     *                      used to represent the updated category data that is
     *                      being sent in the request body. It contains
     *                      the new values for the category's properties such as
     *                      name, description, etc.
     * @param id            The "id" parameter is a UUID (Universally Unique
     *                      Identifier) that represents the
     *                      unique identifier of the category to be updated.
     * @param bindingResult The `bindingResult` parameter is an object that holds
     *                      the result of the
     *                      validation process for the `categoryInDto` object. It
     *                      contains information about any validation
     *                      errors that occurred during the validation process.
     * @return The method is returning a ResponseEntity object with a generic type
     *         of
     *         CustomResponse<Object>.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<Object>> update(@Valid @RequestBody CategoryInDto categoryInDto,
            @PathVariable("id") UUID id, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(CustomResponse.builder()
                    .message("Error to create")
                    .success(false)
                    .data(bindingResult.getFieldError())
                    .build(), HttpStatus.BAD_REQUEST);
        }
        var category = categoryService.findById(id);
        if (category.isEmpty())
            return new ResponseEntity<>(CustomResponse.builder()
                    .success(false)
                    .message("Not Found")
                    .data(null)
                    .build(), HttpStatus.NOT_FOUND);
        var out = categoryMapper.categoryInDtoToCategory(categoryInDto);
        out.setId(id);
        categoryService.save(out);
        return new ResponseEntity<>(CustomResponse.builder()
                .message("Updated")
                .success(true)
                .data(out)
                .build(), HttpStatus.OK);
    }

    /**
     * The above function is a Java method that handles a DELETE request to delete a
     * category by its ID
     * and returns a response with a success status and message.
     * 
     * @param id The "id" parameter is a UUID (Universally Unique Identifier) that
     *           represents the
     *           unique identifier of the category to be deleted.
     * @return The method is returning a ResponseEntity object with a CustomResponse
     *         object as the
     *         response body. The CustomResponse object contains information about
     *         the success of the
     *         operation, a message, and data. The HTTP status code returned depends
     *         on the outcome of the
     *         operation. If the category is found and deleted successfully, the
     *         status code will be ACCEPTED
     *         (202). If the category is not found, the status code will be
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<Object>> delete(@PathVariable("id") UUID id) {
        var category = categoryService.findById(id);
        if (category.isEmpty())
            return new ResponseEntity<>(CustomResponse.builder()
                    .success(false)
                    .message("Not Found")
                    .data(null)
                    .build(), HttpStatus.NOT_FOUND);
        categoryService.delete(category.get());
        return new ResponseEntity<>(CustomResponse.builder()
                .success(true)
                .message("Deleted")
                .data(null)
                .build(), HttpStatus.ACCEPTED);
    }

}
