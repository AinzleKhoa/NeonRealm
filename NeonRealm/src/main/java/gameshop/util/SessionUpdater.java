/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameshop.util;

import gameshop.DAO.UserDAO;
import gameshop.model.User;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Ainzle
 */
public class SessionUpdater {

    public static void sessionUpdate(HttpSession session, int id) {
        UserDAO uDAO = new UserDAO();
        User updatedUser = uDAO.findById(id); // Retrieve updated user info from DB to dynamically update the profile

        if (updatedUser != null) {
            session.setAttribute("currentUser", updatedUser);
            session.setAttribute("currentEmail", updatedUser.getEmail());
        }
    }
}
