/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameshop.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Represents an order placed by a user in the system. This class contains all
 * the details related to an order, including the user ID, total price, discount
 * code, and the order creation timestamp.
 *
 * @author Le Anh Khoa - CE190449
 */
public class Order {

    private int orderId;
    private int userId;
    private BigDecimal totalPrice;
    private String discountCode;
    private LocalDateTime createdAt;

    /**
     * Constructor to initialize an order with the provided details.
     *
     * @param orderId the unique ID of the order
     * @param userId the ID of the user who placed the order
     * @param totalPrice the total price of the order
     * @param discountCode the discount code applied to the order (if any)
     * @param createdAt the timestamp when the order was created
     */
    public Order(int orderId, int userId, BigDecimal totalPrice, String discountCode, LocalDateTime createdAt) {
        this.orderId = orderId;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.discountCode = discountCode;
        this.createdAt = createdAt;
    }

    /**
     * Gets the unique ID of the order.
     *
     * @return the order ID
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * Sets the unique ID of the order.
     *
     * @param orderId the order ID to set
     */
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    /**
     * Gets the ID of the user who placed the order.
     *
     * @return the user ID
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the ID of the user who placed the order.
     *
     * @param userId the user ID to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the total price of the order.
     *
     * @return the total price
     */
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    /**
     * Sets the total price of the order.
     *
     * @param totalPrice the total price to set
     */
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * Gets the discount code applied to the order.
     *
     * @return the discount code
     */
    public String getDiscountCode() {
        return discountCode;
    }

    /**
     * Sets the discount code applied to the order.
     *
     * @param discountCode the discount code to set
     */
    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    /**
     * Gets the timestamp when the order was created.
     *
     * @return the creation timestamp
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the timestamp when the order was created.
     *
     * @param createdAt the creation timestamp to set
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
