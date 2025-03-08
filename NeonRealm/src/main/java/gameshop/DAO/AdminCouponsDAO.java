/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameshop.DAO;

import gameshop.db.DBContext;
import gameshop.model.AdminCoupons;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * AdminCouponsDAO - Thao tac voi bang Coupons
 * 
 * @author Pham Van Hoai - CE181744
 */
public class AdminCouponsDAO extends DBContext {

    // Lấy danh sách tất cả các mã giảm giá
    public List<AdminCoupons> getAllCoupons() {
        List<AdminCoupons> coupons = new ArrayList<>();
        String query = "SELECT coupon_id, code, discount_percentage, expiration_date, usage_limit, created_at FROM Coupons";
        
        try (ResultSet rs = execSelectQuery(query)) {
            while (rs.next()) {
                coupons.add(new AdminCoupons(
                        rs.getInt("coupon_id"),
                        rs.getString("code"),
                        rs.getInt("discount_percentage"),
                        rs.getDate("expiration_date"),
                        rs.getInt("usage_limit"),
                        rs.getTimestamp("created_at")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminCouponsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return coupons;
    }

    // Thêm một mã giảm giá mới
    public boolean addCoupon(AdminCoupons coupon) {
        String query = "INSERT INTO Coupons (code, discount_percentage, expiration_date, usage_limit) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement ps = getConnection().prepareStatement(query)) {
            ps.setString(1, coupon.getCode());
            ps.setInt(2, coupon.getDiscountPercentage());
            ps.setDate(3, new java.sql.Date(coupon.getExpirationDate().getTime()));
            ps.setInt(4, coupon.getUsageLimit());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(AdminCouponsDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    // Xóa một mã giảm giá theo ID
    public boolean deleteCoupon(int couponId) {
        String query = "DELETE FROM Coupons WHERE coupon_id = ?";
        
        try (PreparedStatement ps = getConnection().prepareStatement(query)) {
            ps.setInt(1, couponId);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(AdminCouponsDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    // Cập nhật thông tin mã giảm giá
    public boolean updateCoupon(AdminCoupons coupon) {
        String query = "UPDATE Coupons SET code = ?, discount_percentage = ?, expiration_date = ?, usage_limit = ? WHERE coupon_id = ?";
        
        try (PreparedStatement ps = getConnection().prepareStatement(query)) {
            ps.setString(1, coupon.getCode());
            ps.setInt(2, coupon.getDiscountPercentage());
            ps.setDate(3, new java.sql.Date(coupon.getExpirationDate().getTime()));
            ps.setInt(4, coupon.getUsageLimit());
            ps.setInt(5, coupon.getCouponId());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(AdminCouponsDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    // Lấy thông tin mã giảm giá theo ID
    public AdminCoupons getCouponById(int couponId) {
        String query = "SELECT * FROM Coupons WHERE coupon_id = ?";
        
        try (PreparedStatement ps = getConnection().prepareStatement(query)) {
            ps.setInt(1, couponId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new AdminCoupons(
                        rs.getInt("coupon_id"),
                        rs.getString("code"),
                        rs.getInt("discount_percentage"),
                        rs.getDate("expiration_date"),
                        rs.getInt("usage_limit"),
                        rs.getTimestamp("created_at")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminCouponsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
