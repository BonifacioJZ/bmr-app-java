package com.bonifacio.app.validations;

import com.bonifacio.app.repositories.ICategoryRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UniqueNameCategoryValidation implements ConstraintValidator<UniqueNameCategory,String> {
    private ICategoryRepository categoryRepository;
    /**
     * @param constraintAnnotation 
     */
    @Override
    public void initialize(UniqueNameCategory constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    /**
     * @param s 
     * @param constraintValidatorContext
     * @return
     */
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !categoryRepository.existsByName(s);
    }
}
