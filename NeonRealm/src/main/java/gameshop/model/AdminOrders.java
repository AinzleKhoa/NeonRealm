/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameshop.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 *
 * @author Pham Van Hoai - CE181744
 */
public class AdminOrders {
    private int orderId;
    private int userId;
    private String username;  // Thêm tên người mua
    private BigDecimal totalPrice;
    private String discountCode;
    private Timestamp createdAt;
    private int gameId;  // Thêm ID game
    private String gameTitle; // Thêm tên game

    public AdminOrders() {
    }

    public AdminOrders(int orderId, int userId, String username, BigDecimal totalPrice, String discountCode, Timestamp createdAt, int gameId, String gameTitle) {
        this.orderId = orderId;
        this.userId = userId;
        this.username = username;
        this.totalPrice = totalPrice;
        this.discountCode = discountCode;
        this.createdAt = createdAt;
        this.gameId = gameId;
        this.gameTitle = gameTitle;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getGameTitle() {
        return gameTitle;
    }

    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }

    
}
