/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package gameshop.controller;

import gameshop.DAO.AdminOrdersDAO;
import gameshop.model.AdminOrders;
import gameshop.util.SessionUtil;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author Pham Van Hoai - CE181744
 */
@WebServlet(name = "AdminOrdersServlet", urlPatterns = {"/admin/orders"})
public class AdminOrdersServlet extends HttpServlet {

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

    // Kiểm tra quyền admin
    if (!SessionUtil.isAdmin(request)) {
        response.sendRedirect(request.getContextPath() + "/pages/home.jsp");
        return;
    }

    AdminOrdersDAO adminOrdersDAO = new AdminOrdersDAO();

    // Lấy tham số sắp xếp (nếu không có thì mặc định là null)
    String sortPrice = request.getParameter("sortPrice");

    // Xử lý phân trang
    int pageSize = 3;
    int currentPage = 1;

    try {
        if (request.getParameter("page") != null && !request.getParameter("page").trim().isEmpty()) {
            currentPage = Integer.parseInt(request.getParameter("page"));
        }
    } catch (NumberFormatException e) {
        currentPage = 1; // Nếu lỗi, đặt về trang 1
    }

    int offset = (currentPage - 1) * pageSize;

    // Lấy danh sách đơn hàng theo trang và sắp xếp
    List<AdminOrders> orders = adminOrdersDAO.getAllOrders(sortPrice, offset, pageSize);
    int totalOrders = adminOrdersDAO.countTotalOrders();
    int totalPages = (int) Math.ceil((double) totalOrders / pageSize);

    // Gửi dữ liệu về JSP với tên chính xác
    request.setAttribute("orders", orders);
    request.setAttribute("totalPages", totalPages);  // ✅ Đúng với custom tag
    request.setAttribute("totalOrders", totalOrders); // ✅ Tổng số đơn hàng
    request.setAttribute("currentPage", currentPage);
    request.setAttribute("sortPrice", sortPrice);

    request.getRequestDispatcher("/admin/orders.jsp").forward(request, response);
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
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Admin Orders Servlet";
    }// </editor-fold>

}
