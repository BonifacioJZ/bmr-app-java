package com.bonifacio.app.mappers;

import com.bonifacio.app.entities.Product;
import com.bonifacio.app.services.dao.CategoryOutDto;
import com.bonifacio.app.services.dao.ProductDetail;
import com.bonifacio.app.services.dao.ProductInDto;
import com.bonifacio.app.services.dao.ProductOutDto;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements IProductMapper {
    /**
     * @param productOutDto 
     * @return
     */
    @Override
    public ProductOutDto productToProductOutDto(Product product) {
        if(product == null) return null;

        return ProductOutDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .is_enable(product.is_enable())
                .price(product.getPrice())
                .build();

    }

    /**
     * @param productInDto 
     * @return
     */
    @Override
    public Product productInDtoToProduct(ProductInDto productInDto) {
        if(productInDto == null) return null;

        return Product.builder()
                .name(productInDto.getName())
                .description(productInDto.getDescription())
                .price(productInDto.getPrice())
                .is_enable(productInDto.is_enable())
                .build();
    }

    /**
     * @param productDetail 
     * @param category
     * @return
     */
    @Override
    public ProductDetail productToProductDetail(Product product, CategoryOutDto category) {
        if(product == null || category ==null){
            return null;
        }

        return ProductDetail.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .is_enable(product.is_enable())
                .id(product.getId())
                .category(category)
                .build();
    }
}
