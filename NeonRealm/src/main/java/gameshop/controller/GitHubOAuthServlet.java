/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package gameshop.controller;

import gameshop.DAO.UserDAO;
import gameshop.model.User;
import gameshop.util.GitHubOAuthUtil;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;

/**
 *
 * @author Ainzle
 */
public class GitHubOAuthServlet extends HttpServlet {

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
        String code = request.getParameter("code");
        if (code == null) {
            response.sendRedirect("login.jsp?error=GitHub authorization failed");
            return;
        }

        // Get access token
        String accessToken = GitHubOAuthUtil.getAccessToken(code);
        if (accessToken == null) {
            response.sendRedirect("login.jsp?error=GitHub token retrieval failed");
            return;
        }

        // Get user info
        JSONObject userInfo = GitHubOAuthUtil.getUserInfo(accessToken);
        if (userInfo == null) {
            response.sendRedirect("login.jsp?error=GitHub user retrieval failed");
            return;
        }

        // Extract user details
        String githubId = String.valueOf(userInfo.getLong("id")); // Ensure it's stored as Long then as String for User
        String username = userInfo.optString("login");
        String email = userInfo.optString("email", githubId + "@github.com"); // Email might be null

        UserDAO userDAO = new UserDAO();
        User user = userDAO.findByGitHubId(githubId);

        // If user doesn't exist, create a new one, To the sign up
        if (user == null) {
            user = new User(username, email, null, githubId, "github");
            userDAO.signup(user);
        }

        // If user already had an account, login them in without validate further
        // Store user session
        HttpSession session = request.getSession(true);
        session.setAttribute("currentUser", user);

        // Redirect to home
        response.sendRedirect(request.getContextPath() + "/home");
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
        return "Short description";
    }// </editor-fold>

}
