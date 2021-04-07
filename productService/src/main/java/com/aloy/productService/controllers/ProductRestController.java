package com.aloy.productService.controllers;

import com.aloy.productService.dto.Coupon;
import com.aloy.productService.model.Product;
import com.aloy.productService.repos.ProductRepo;
import com.aloy.productService.restClients.CouponClient;
import io.github.resilience4j.retry.annotation.Retry;
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
    @Retry(name = "product-api", fallbackMethod = "handleError")
//    @Retry(name = "product-api")
    public Product create(@RequestBody Product product) {
        Coupon coupon = couponClient.getCoupon(product.getCouponCode());
        product.setPrice(product.getPrice().subtract(coupon.getDiscount()));
        return repo.save(product);
    }

// Method signature should be same as the api causing the trouble
    public Product handleError(Product product, Exception exception) {
//        Other logic can be added here to handle the exception, Something like trying out other services
        System.out.println("Inside handle error method");
        product.setDescription(exception.getMessage());
        return product;
    }

}
