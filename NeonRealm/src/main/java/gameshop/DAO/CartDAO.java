package gameshop.DAO;

import gameshop.db.DBContext;
import gameshop.model.Cart;
import gameshop.model.Game;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDAO extends DBContext {

    public static void main(String[] a) {
        CartDAO cDAO = new CartDAO();
        cDAO.addToCart(3, 6);
        List<Game> gameInCarts = cDAO.getGamesInCartByUserId(3);
        for (Game g : gameInCarts) {
            System.out.println(g.getTitle());
        }
    }

    public double getAllTotalCartPrice() {
        double totalPrice = 0.0;
        String sql = "SELECT SUM(g.price) AS total FROM Cart c JOIN Games g ON c.game_id = g.game_id";
        try {
            PreparedStatement ps = this.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                totalPrice = rs.getDouble("total");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalPrice;
    }

    public double getTotalCartPrice(int userId) {
        double totalPrice = 0;
        String sql = "SELECT SUM(g.price) AS total FROM Cart c JOIN Games g ON c.game_id = g.game_id WHERE c.user_id = ?";
        try {
            PreparedStatement ps = this.getConnection().prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                totalPrice = rs.getDouble("total");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalPrice;
    }

    // Lấy tất cả sản phẩm trong giỏ hàng
    public ArrayList<Cart> getAllCarts() {
        try {
            ArrayList<Cart> cartList = new ArrayList<>();
            String query = "SELECT * FROM Cart";
            PreparedStatement pStatement = this.getConnection().prepareStatement(query);
            ResultSet rs = pStatement.executeQuery();

            while (rs.next()) {
                cartList.add(new Cart(
                        rs.getInt("cart_id"),
                        rs.getInt("user_id"),
                        rs.getInt("game_id"),
                        rs.getTimestamp("created_at")
                ));
            }
            return cartList;
        } catch (SQLException e) {
        }
        return null;
    }

    public List<Game> getGamesInCartByUserId(int userId) {
        List<Game> games = new ArrayList<>();
        String sql = "SELECT g.* FROM Cart c JOIN Games g ON c.game_id = g.game_id WHERE c.user_id = ?";
        try ( PreparedStatement ps = this.getConnection().prepareStatement(sql)) {
            ps.setInt(1, userId);
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Game game = new Game(
                            rs.getInt("game_id"),
                            rs.getString("title"),
                            rs.getString("description"),
                            rs.getString("image_url"),
                            rs.getBigDecimal("price"),
                            rs.getDate("release_date").toLocalDate(),
                            null, // createdAt (nếu cần, bạn có thể lấy `rs.getDate("created_at").toLocalDate()`)
                            null, null, null, null, null // Developers, publishers, genres, platforms, categories
                    );
                    games.add(game);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return games;
    }

    // Lấy giỏ hàng theo user_id
    public List<Cart> getCartByUserId(int userId) {
        List<Cart> cartList = new ArrayList<>();
        String sql = "SELECT * FROM Cart WHERE user_id = ?";
        try ( PreparedStatement ps = this.getConnection().prepareStatement(sql)) {
            ps.setInt(1, userId);
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    cartList.add(new Cart(
                            rs.getInt("cart_id"),
                            rs.getInt("user_id"),
                            rs.getInt("game_id"),
                            rs.getTimestamp("created_at")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cartList;
    }

    public boolean isGameInCart(int userId, int gameId) {
        String sql = "SELECT COUNT(*) FROM Cart WHERE user_id = ? AND game_id = ?";
        try ( PreparedStatement ps = this.getConnection().prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, gameId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Trả về true nếu có ít nhất 1 kết quả
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Thêm sản phẩm vào giỏ hàng
    public boolean addToCart(int userId, int gameId) {
        String insertSql = "INSERT INTO Cart (user_id, game_id, created_at) VALUES (?, ?, GETDATE())";
        try ( PreparedStatement insertStmt = this.getConnection().prepareStatement(insertSql)) {
            insertStmt.setInt(1, userId);
            insertStmt.setInt(2, gameId);
            int rowsInserted = insertStmt.executeUpdate();
            return rowsInserted > 0; // Trả về true nếu thêm thành công  
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Xóa sản phẩm khỏi giỏ hàng
    public void removeFromCart(int userId, int gameId) {
        String sql = "DELETE FROM Cart WHERE user_id = ? AND game_id = ?";
        try ( PreparedStatement ps = this.getConnection().prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, gameId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Xóa toàn bộ giỏ hàng của một user (nếu cần)
    public void clearCartByUserId(int userId) {
        String sql = "DELETE FROM Cart WHERE user_id = ?";
        try ( PreparedStatement ps = this.getConnection().prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
