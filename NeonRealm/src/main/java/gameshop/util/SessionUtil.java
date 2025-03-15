/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameshop.util;

import gameshop.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Ainzle
 */
public class SessionUtil {

    // P/thuc kiem tra trang thai dang nhap : boolean
    public static boolean isLoggedIn(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null && session.getAttribute("currentUser") != null;
    }

    //Pthuc getUsername from Session
    public static String getLoggedInUsername(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            return (String) session.getAttribute("username");
        }
        return null;
    }

    // Kiểm tra user có phải admin không (1 = Admin, 0 = User)
    public static boolean isAdmin(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return false;
        }

        User user = (User) session.getAttribute("currentUser");
        return (user != null && "admin".equalsIgnoreCase(user.getRole()));
    }
}
