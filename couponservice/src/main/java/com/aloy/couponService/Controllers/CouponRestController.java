package com.aloy.couponService.Controllers;

import com.aloy.couponService.Model.Coupon;
import com.aloy.couponService.Repos.CouponsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/couponapi")
public class CouponRestController {

    @Autowired
    private CouponsRepo repo;

    @PostMapping("/coupons")
    public Coupon create(@RequestBody Coupon coupon) {
        return repo.save(coupon);
    }

    @GetMapping("/coupons/{code}")
    public Coupon getCoupon(@PathVariable String code) {
        return repo.findByCode(code);
    }

}
