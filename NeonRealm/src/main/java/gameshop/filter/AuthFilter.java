/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameshop.filter;

import gameshop.util.SessionUtil;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author Ainzle
 */
//@WebFilter(filterName = "auth", urlPatterns = {"/artist", "/album"})
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // Kiem tra session co hop le hay khong (dang nhap chua)
        HttpServletRequest req = (HttpServletRequest) request;
        
        if (SessionUtil.isLoggedIn(req)) {
            chain.doFilter(req, response);
            System.out.println("User has logged in!");
        } else {
            ((HttpServletResponse) response).sendRedirect(req.getContextPath() + "/login");
            System.out.println("❌ User not logged in! Redirecting to login page");
        }
    }

}
