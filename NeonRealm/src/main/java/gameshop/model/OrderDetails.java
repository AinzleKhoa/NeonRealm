/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameshop.model;

import java.math.BigDecimal;

/**
 * Represents the details of an order, including the associated order, game, and
 * price. This class is used to capture the specific items (games) in an order
 * and their purchase price.
 *
 * @author Le Anh Khoa - CE190449
 */
public class OrderDetails {

    private int orderDetailId;
    private Order order;  // Many-to-One relationship with Order
    private Game game;    // Many-to-One relationship with Game
    private BigDecimal price; // The price of the game at the time of purchase

    /**
     * Constructor to initialize the OrderDetails object with the provided
     * details.
     *
     * @param orderDetailId the unique ID for this order detail
     * @param order the order associated with this order detail
     * @param game the game associated with this order detail
     * @param price the price of the game at the time of purchase
     */
    public OrderDetails(int orderDetailId, Order order, Game game, BigDecimal price) {
        this.orderDetailId = orderDetailId;
        this.order = order;
        this.game = game;
        this.price = price;
    }

    /**
     * Gets the unique ID for this order detail.
     *
     * @return the order detail ID
     */
    public int getOrderDetailId() {
        return orderDetailId;
    }

    /**
     * Sets the unique ID for this order detail.
     *
     * @param orderDetailId the order detail ID to set
     */
    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    /**
     * Gets the order associated with this order detail.
     *
     * @return the order
     */
    public Order getOrder() {
        return order;
    }

    /**
     * Sets the order associated with this order detail.
     *
     * @param order the order to set
     */
    public void setOrder(Order order) {
        this.order = order;
    }

    /**
     * Gets the game associated with this order detail.
     *
     * @return the game
     */
    public Game getGame() {
        return game;
    }

    /**
     * Sets the game associated with this order detail.
     *
     * @param game the game to set
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * Gets the price of the game at the time of purchase.
     *
     * @return the price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets the price of the game at the time of purchase.
     *
     * @param price the price to set
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
