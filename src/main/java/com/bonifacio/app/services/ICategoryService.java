package com.bonifacio.app.services;

import com.bonifacio.app.entities.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ICategoryService extends CrudRepository<Category, UUID> {

}
