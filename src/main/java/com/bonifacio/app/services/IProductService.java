package com.bonifacio.app.services;

import com.bonifacio.app.entities.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface IProductService extends CrudRepository<Product, UUID> {
}
