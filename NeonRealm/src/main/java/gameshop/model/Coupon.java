/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameshop.model;

import java.time.LocalDate;

/**
 *
 * @author ALIENWARE
 */
public class Coupon {
    private int couponId;
    private String couponCode;
    private int discountPercentage;
    private LocalDate expirationDate;
    private int usageLimit;
    private LocalDate createAt;

    public int getCouponId() {
        return couponId;
    }

    public void setCouponId(int couponId) {
        this.couponId = couponId;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getUsageLimit() {
        return usageLimit;
    }

    public void setUsageLimit(int usageLimit) {
        this.usageLimit = usageLimit;
    }

    public LocalDate getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }

    public Coupon(int couponId, String couponCode, int discountPercentage, LocalDate expirationDate, int usageLimit) {
        this.couponId = couponId;
        this.couponCode = couponCode;
        this.discountPercentage = discountPercentage;
        this.expirationDate = expirationDate;
        this.usageLimit = usageLimit;
    }

    public Coupon(int couponId, String couponCode, int discountPercentage, LocalDate expirationDate, int usageLimit, LocalDate createAt) {
        this.couponId = couponId;
        this.couponCode = couponCode;
        this.discountPercentage = discountPercentage;
        this.expirationDate = expirationDate;
        this.usageLimit = usageLimit;
        this.createAt = createAt;
    }
    
    
}
