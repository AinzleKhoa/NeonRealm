/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package gameshop.controller;

import gameshop.DAO.CartDAO;
import gameshop.DAO.CouponDAO;
import gameshop.DAO.OrderDAO;
import gameshop.DAO.OrderDetailsDAO;
import gameshop.db.DBContext;
import gameshop.model.Cart;
import gameshop.model.Coupon;
import gameshop.model.Game;
import gameshop.model.Order;
import gameshop.model.OrderDetails;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import static java.lang.System.console;
import static java.lang.System.out;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ALIENWARE
 */
public class PayServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Request received!");

        HttpSession session = request.getSession();
        String couponCode = request.getParameter("couponCode"); // Lấy coupon từ request
        Double totalPrice = (Double) session.getAttribute("totalPrice");
        String action = request.getParameter("action");
        double discountPercent = 0.0;
        double newTotal = 0.0;
        if ("couponCheck".equals(action)) {
            if (couponCode == null || couponCode.isEmpty()) {
                System.out.println("Error: Coupon code is empty");
                response.getWriter().print("{\"status\":\"error\"}");
                return;
            }

            CouponDAO cDAO = new CouponDAO();
            Coupon coupon = cDAO.getValidCoupon(couponCode);

            if (coupon != null && totalPrice != null) {
                discountPercent = coupon.getDiscountPercentage();
                session.setAttribute("discountPercent", discountPercent);

                double discountAmount = totalPrice * (discountPercent / 100);
                newTotal = totalPrice - discountAmount;
                session.setAttribute("totalPrice", newTotal);

                // Trả về JSON đúng format
                Map<String, Object> jsonResponse = new HashMap<>();
                jsonResponse.put("status", "success");
                jsonResponse.put("discountPercent", discountPercent);
                jsonResponse.put("newTotal", newTotal);

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(new com.google.gson.Gson().toJson(jsonResponse));
            } else {
                response.getWriter().write("{\"status\": \"exists\"}");
            }
        }

        if ("complete".equals(action)) {
            int userId = (Integer) session.getAttribute("userId"); // Lấy userId từ session

            CartDAO cartDAO = new CartDAO();
            OrderDAO ordersDAO = new OrderDAO();
            List<Game> cartItems = cartDAO.getGamesInCartByUserId(userId);
            if (cartItems == null || cartItems.isEmpty()) {
                response.sendRedirect("/cart?error=empty");
                return;
            }
            BigDecimal BDCTotal = BigDecimal.valueOf(totalPrice);

            Order order = new Order(userId, BDCTotal, couponCode);
            int orderId = ordersDAO.addOrder(order);
            if (orderId > 0) {
                for (Game game : cartItems) {
                    OrderDetailsDAO oDDAO = new OrderDetailsDAO();
                    oDDAO.addOrderDetails(orderId, game.getGameId(), game.getPrice());
                    System.out.println(game.getGameId());
                    System.out.println(orderId);
                    System.out.println(game.getPrice());
                    System.out.println("Succsess add game to order details");
                }

                cartDAO.clearCart(userId);
                System.out.println("succsess payment");
                response.sendRedirect("home");
            } else {
                response.sendRedirect("/cart?error=payment_failed");
            }

        }
    }
}
