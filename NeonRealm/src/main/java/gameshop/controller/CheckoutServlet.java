/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package gameshop.controller;

import gameshop.DAO.CartDAO;
import gameshop.model.Cart;
import gameshop.model.Game;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author ALIENWARE
 */
public class CheckoutServlet extends HttpServlet {
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int userId = 3;
        //HttpSession session = request.getSession();
        //Integer userId = (Integer) session.getAttribute("userId");
        String action = request.getParameter("action");
        //if (userId != null) {
        CartDAO cartDAO = new CartDAO();
        List<Game> gamesInCart = cartDAO.getGamesInCartByUserId(userId);
        request.setAttribute("gamesInCart", gamesInCart);
        //List<Cart> cartList = cartDAO.getAllCarts();
        //double totalPrice = cartDAO.getAllTotalCartPrice();
        List<Cart> cartList = cartDAO.getCartByUserId(userId);
        double totalPrice = cartDAO.getTotalCartPrice(userId);

        // Đưa dữ liệu vào request
        request.setAttribute("cartList", cartList);
        request.setAttribute("cartCount", cartList.size());
        request.setAttribute("totalPrice", totalPrice);
        /**
         * }else{ request.setAttribute("cartCount", 0);
         * request.setAttribute("totalPrice", 0.0); }*
         */
        // Forward về trang header.jsp
        request.getRequestDispatcher("pages/checkout.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        int userId = 3; // Giả lập userId, bạn có thể lấy từ session

        if ("delete".equals(action)) {
            int gameId = Integer.parseInt(request.getParameter("gameId"));
            CartDAO cartDAO = new CartDAO();
            cartDAO.removeFromCart(userId, gameId);
        }

        // Chuyển hướng lại phương thức GET để cập nhật giỏ hàng
        response.sendRedirect("CheckoutServlet");
    }

}
