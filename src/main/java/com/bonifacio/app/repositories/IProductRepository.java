package com.bonifacio.app.repositories;

import com.bonifacio.app.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.UUID;

public interface IProductRepository extends JpaRepository<Product, UUID> {
}
