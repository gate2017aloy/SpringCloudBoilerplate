package com.aloy.productService.controllers;

import com.aloy.productService.model.Product;
import com.aloy.productService.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/productapi")
public class ProductRestController {

    @Autowired
    private ProductRepo repo;

    @PostMapping("/products")
    public Product create(@RequestBody Product product) {
        return repo.save(product);
    }

}
