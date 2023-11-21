package com.bonifacio.app.repositories;

import com.bonifacio.app.entities.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ICategoryRepository extends CrudRepository<Category, UUID> {
    boolean existsByName(String name);
}
