package com.aloy.productService.controllers;

import com.aloy.productService.dto.Coupon;
import com.aloy.productService.model.Product;
import com.aloy.productService.repos.ProductRepo;
import com.aloy.productService.restClients.CouponClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/productapi")
public class ProductRestController {

    @Autowired
    CouponClient couponClient;

    @Autowired
    private ProductRepo repo;

    @PostMapping("/products")
    public Product create(@RequestBody Product product) {
        Coupon coupon = couponClient.getCoupon(product.getCouponCode());
        product.setPrice(product.getPrice().subtract(coupon.getDiscount()));
        return repo.save(product);
    }

}
