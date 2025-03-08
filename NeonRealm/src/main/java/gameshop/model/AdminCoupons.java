/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameshop.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 *
 * @author Pham Van Hoai - CE181744
 */
public class AdminCoupons {
    private int couponId;
    private String code;
    private int discountPercentage;
    private Date expirationDate;
    private int usageLimit;
    private Timestamp createdAt;

    // Constructor không tham số
    public AdminCoupons() {
    }

    public AdminCoupons(int couponId, String code, int discountPercentage, Date expirationDate, int usageLimit, Timestamp createdAt) {
        this.couponId = couponId;
        this.code = code;
        this.discountPercentage = discountPercentage;
        this.expirationDate = expirationDate;
        this.usageLimit = usageLimit;
        this.createdAt = createdAt;
    }

    public int getCouponId() {
        return couponId;
    }

    public void setCouponId(int couponId) {
        this.couponId = couponId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getUsageLimit() {
        return usageLimit;
    }

    public void setUsageLimit(int usageLimit) {
        this.usageLimit = usageLimit;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    
    

    
}