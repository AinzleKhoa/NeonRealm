/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package gameshop.controller;

import gameshop.DAO.GameDAO;
import gameshop.model.Game;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author ALIENWARE
 */
public class BuyNowServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String gameId = request.getParameter("id");
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            System.out.println("Error: userId is null. Redirecting to login.");
            response.sendRedirect("login"); // Chuyển hướng về trang đăng nhập
            return;
        }
        if (gameId == null || gameId.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Game ID is missing!");
            return;
        }

        // Lấy thông tin game từ database
        GameDAO gameDAO = new GameDAO();
        Game game = gameDAO.getGameById(Integer.parseInt(gameId));

        if (game == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Game not found!");
            return;
        }

        // Gửi game đến trang checkout.jsp
        request.setAttribute("checkoutGame", game);

        // Chuyển tiếp đến trang checkout.jsp thay vì redirect
        request.getRequestDispatcher("WEB-INF/pages/buynow.jsp").forward(request, response);
    }
}
