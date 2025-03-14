/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameshop.model;

import java.math.BigDecimal;

/**
 *
 * @author Ainzle
 */
public class OrderDetails {

    private int orderDetailId;
    private Order order;  // Many-to-One relationship with Order
    private Game game;    // Many-to-One relationship with Game
    private BigDecimal price; // The price of the game at the time of purchase

    public OrderDetails(int orderDetailId, Order order, Game game, BigDecimal price) {
        this.orderDetailId = orderDetailId;
        this.order = order;
        this.game = game;
        this.price = price;
    }

    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
