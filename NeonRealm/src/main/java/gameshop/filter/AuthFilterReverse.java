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
public class AuthFilterReverse implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // Kiem tra session co hop le hay khong (dang nhap chua)
        HttpServletRequest req = (HttpServletRequest) request;

        if (SessionUtil.isLoggedIn(req)) {
            System.out.println("❌ User logged in! Redirecting to home page"); // Move log here
            ((HttpServletResponse) response).sendRedirect(req.getContextPath() + "/home");
        } else {
            System.out.println("User has not logged in!"); // Move log here
            chain.doFilter(req, response);
        }
    }

}
