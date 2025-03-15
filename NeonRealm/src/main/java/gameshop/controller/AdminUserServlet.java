/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package gameshop.controller;

import gameshop.DAO.AdminUserDAO;
import gameshop.model.AdminUser;
import gameshop.util.SessionUtil;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pham Van Hoai - CE181744
 */
@WebServlet(name = "AdminUserServlet", urlPatterns = {"/admin/users"})

public class AdminUserServlet extends HttpServlet {

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
        AdminUserDAO adminUserDAO = new AdminUserDAO();
        if (!SessionUtil.isAdmin(request)) {
            response.sendRedirect(request.getContextPath() + "/pages/home.jsp");
            return;
        }

        // Nhận từ khóa tìm kiếm
        String searchQuery = request.getParameter("search");

        // Phân trang
        int usersPerPage = 10; // Số user trên mỗi trang
        int currentPage = 1;
        if (request.getParameter("page") != null) {
            try {
                currentPage = Integer.parseInt(request.getParameter("page"));
            } catch (NumberFormatException e) {
                currentPage = 1;
            }
        }
        int offset = (currentPage - 1) * usersPerPage;



        // Lấy danh sách user theo bộ lọc
        List<AdminUser> adminUsers = adminUserDAO.getAllAdminUsers(searchQuery, offset, usersPerPage);
        int totalUsers = adminUserDAO.countUsersByFilter(searchQuery);
        int totalPages = (int) Math.ceil((double) totalUsers / usersPerPage);
        
    int currentTotalUsers = (currentPage < totalPages ? usersPerPage * currentPage : totalUsers);
        
        // Gửi dữ liệu tới JSP
        request.setAttribute("users", adminUsers);
        request.setAttribute("totalUsers", totalUsers);
        request.setAttribute("searchQuery", searchQuery);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("currentTotalUsers", currentTotalUsers);

        request.getRequestDispatcher("/admin/users.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        AdminUserDAO adminCouponsDAO = new AdminUserDAO();

        try {
            if ("delete".equals(action)) {
                int userId = Integer.parseInt(request.getParameter("userId"));
                adminCouponsDAO.deleteUser(userId);
            }
        } catch (Exception e) {
            request.setAttribute("error", "Lỗi trong quá trình xử lý!");
        }

        response.sendRedirect(request.getContextPath() + "/admin/users");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
