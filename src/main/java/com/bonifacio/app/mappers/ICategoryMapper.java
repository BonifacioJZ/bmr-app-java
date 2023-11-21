package com.bonifacio.app.mappers;

import com.bonifacio.app.entities.Category;
import com.bonifacio.app.services.dao.CategoryDetail;
import com.bonifacio.app.services.dao.CategoryInDto;
import com.bonifacio.app.services.dao.CategoryOutDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ICategoryMapper {
    ICategoryMapper INSTANCE = Mappers.getMapper(ICategoryMapper.class);
    CategoryOutDto categoryToCategoryOutDto(Category category);
    Category categoryInDtoToCategory(CategoryInDto category);
    CategoryDetail categoryToCategoryDetail(Category category);

}
