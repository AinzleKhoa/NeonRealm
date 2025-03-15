/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package gameshop.controller;

import gameshop.DAO.AdminCouponsDAO;
import gameshop.model.AdminCoupons;
import gameshop.util.SessionUtil;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pham Van Hoai - CE181744
 */
@WebServlet(name = "AdminCouponsServlet", urlPatterns = {"/admin/coupons"})
public class AdminCouponsServlet extends HttpServlet {

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

        if (!SessionUtil.isAdmin(request)) {
            response.sendRedirect(request.getContextPath() + "/pages/home.jsp");
            return;
        }

        AdminCouponsDAO adminCouponsDAO = new AdminCouponsDAO();
        
        // Xử lý yêu cầu thêm coupon
        if (request.getParameter("add") != null) {
            request.getRequestDispatcher("/admin/add-coupon.jsp").forward(request, response);
            return;
        }
        
        // Kiểm tra nếu có yêu cầu chỉnh sửa
        String editId = request.getParameter("editId");
        if (editId != null) {
            try {
                int couponId = Integer.parseInt(editId);
                AdminCoupons coupon = adminCouponsDAO.getCouponById(couponId);

                if (coupon == null) {
                    response.sendRedirect(request.getContextPath() + "/pages/404.jsp");
                    return;
                }

                request.setAttribute("coupon", coupon);
                request.getRequestDispatcher("/admin/edit-coupon.jsp").forward(request, response);
                return;
            } catch (NumberFormatException e) {
                response.sendRedirect(request.getContextPath() + "/admin/coupons");
                return;
            }
        }

        // Nếu không phải yêu cầu chỉnh sửa, hiển thị danh sách coupon
        List<AdminCoupons> coupons = adminCouponsDAO.getAllCoupons();
        request.setAttribute("coupons", coupons);
        request.getRequestDispatcher("/admin/coupons.jsp").forward(request, response);
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
        AdminCouponsDAO adminCouponsDAO = new AdminCouponsDAO();

        try {
            if ("delete".equals(action)) {
                int couponId = Integer.parseInt(request.getParameter("couponId"));
                adminCouponsDAO.deleteCoupon(couponId);
            } else if ("add".equals(action)) {
                String code = request.getParameter("code");
                int discount = Integer.parseInt(request.getParameter("discount"));
                String expiration = request.getParameter("expiration");
                int usageLimit = Integer.parseInt(request.getParameter("usageLimit"));

                if (code == null || code.isEmpty() || discount < 1 || discount > 100 || expiration.isEmpty() || usageLimit < 1) {
                    request.setAttribute("error", "Vui lòng nhập đúng thông tin!");
                    request.getSession().setAttribute("successMessage", "Add coupon successfully!");
                    response.sendRedirect(request.getContextPath() + "/admin/add-coupon");
                    return;
                }

                AdminCoupons coupon = new AdminCoupons(0, code, discount, Date.valueOf(expiration), usageLimit, null);
                adminCouponsDAO.addCoupon(coupon);
            } else if ("edit".equals(action)) {
                int couponId = Integer.parseInt(request.getParameter("couponId"));
                String code = request.getParameter("code");
                int discount = Integer.parseInt(request.getParameter("discount_percentage"));
                String expiration = request.getParameter("expiration_date");
                int usageLimit = Integer.parseInt(request.getParameter("usage_limit"));

                if (code == null || code.isEmpty() || discount < 1 || discount > 100 || expiration.isEmpty() || usageLimit < 1) {
                    request.setAttribute("error", "Vui lòng nhập đúng thông tin!");
                    request.getRequestDispatcher("/admin/edit-coupon.jsp").forward(request, response);
                    return;
                }

                AdminCoupons coupon = new AdminCoupons(couponId, code, discount, Date.valueOf(expiration), usageLimit, null);
                adminCouponsDAO.updateCoupon(coupon);
            }
        } catch (Exception e) {
            request.setAttribute("error", "Lỗi trong quá trình xử lý!");
        }

        response.sendRedirect(request.getContextPath() + "/admin/coupons");
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
