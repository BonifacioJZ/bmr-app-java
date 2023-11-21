package com.bonifacio.app.mappers;

import com.bonifacio.app.entities.Product;
import com.bonifacio.app.services.dao.CategoryOutDto;
import com.bonifacio.app.services.dao.ProductDetail;
import com.bonifacio.app.services.dao.ProductInDto;
import com.bonifacio.app.services.dao.ProductOutDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IProductMapper {
    IProductMapper INSTANCE = Mappers.getMapper(IProductMapper.class);

    ProductOutDto productToProductOutDto(Product productOutDto);
    Product productInDtoToProduct(ProductInDto productInDto);
    ProductDetail productToProductDetail(Product product, CategoryOutDto category);

}
