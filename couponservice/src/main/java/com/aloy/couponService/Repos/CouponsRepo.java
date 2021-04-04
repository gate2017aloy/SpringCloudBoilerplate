package com.aloy.couponService.Repos;

import com.aloy.couponService.Model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponsRepo extends JpaRepository<Coupon, Long> {
    // Spring Data JPA automatically generates a SQL statement
    Coupon findByCode(String code);
}
