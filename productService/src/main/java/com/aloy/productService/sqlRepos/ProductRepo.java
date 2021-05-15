package com.aloy.productService.sqlRepos;

import com.aloy.productService.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {

}
