package gameshop.DAO;

import gameshop.model.Coupon;
import gameshop.db.DBContext;
import gameshop.db.DBContext;
import java.sql.*;

public class CouponDAO {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    public static void main(String[]a){
        CouponDAO cDAO = new CouponDAO();
        Coupon couponcode = cDAO.getValidCoupon("WELCOME10");
        System.out.println(couponcode.getDiscountPercentage());
    }
    public CouponDAO() {
        try {
            conn = new DBContext().getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Lấy thông tin mã giảm giá hợp lệ
    public Coupon getValidCoupon(String couponCode) {
        String query = "SELECT * FROM Coupons WHERE code = ? AND expiration_date >= GETDATE() AND usage_limit > 0";
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, couponCode);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new Coupon(
                    rs.getInt("coupon_id"),
                    rs.getString("code"),
                    rs.getInt("discount_percentage"),
                    rs.getDate("expiration_date").toLocalDate(),
                    rs.getInt("usage_limit"),
                    rs.getDate("created_at").toLocalDate()
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Giảm số lần sử dụng của mã giảm giá
    public boolean decrementUsageLimit(String couponCode) {
        String query = "UPDATE Coupons SET usage_limit = usage_limit - 1 WHERE code = ? AND usage_limit > 0";
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, couponCode);
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
