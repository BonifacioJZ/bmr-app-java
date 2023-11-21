package com.bonifacio.app.mappers;

import com.bonifacio.app.entities.Category;
import com.bonifacio.app.services.dao.CategoryDetail;
import com.bonifacio.app.services.dao.CategoryInDto;
import com.bonifacio.app.services.dao.CategoryOutDto;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper implements  ICategoryMapper{
    /**
     * @param category 
     * @return
     */
    @Override
    public CategoryOutDto categoryToCategoryOutDto(Category category) {
        if(category ==null){
            return null;
        }
        return CategoryOutDto.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }

    /**
     * @param category 
     * @return
     */
    @Override
    public Category categoryInDtoToCategory(CategoryInDto category) {
        if(category==null) return null;

        return Category.builder()
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }

    /**
     * @param category 
     * @return
     */
    @Override
    public CategoryDetail categoryToCategoryDetail(Category category) {
        if(category ==null)return null;

        return  CategoryDetail.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }
}
