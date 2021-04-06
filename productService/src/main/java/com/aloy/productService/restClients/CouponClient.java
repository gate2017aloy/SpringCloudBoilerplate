package com.aloy.productService.restClients;

import com.aloy.productService.dto.Coupon;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("GATEWAY-SERVICE")
public interface CouponClient {

    @GetMapping("/couponapi/coupons/{code}")
    Coupon getCoupon(@PathVariable String code);

}
