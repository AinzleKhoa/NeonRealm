package gameshop.model;

import java.sql.Timestamp;

public class Cart {
    
    private int cartId;
    private int userId;
    private int gameId;
    private Timestamp createdAt;

    public Cart(int cartId, int userId, int gameId, Timestamp createdAt) {
        this.cartId = cartId;
        this.userId = userId;
        this.gameId = gameId;
        this.createdAt = createdAt;
    }

    public Cart(Game game, int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getCartId() { return cartId; }
    public int getUserId() { return userId; }
    public int getGameId() { return gameId; }
    public Timestamp getCreatedAt() { return createdAt; }
}
