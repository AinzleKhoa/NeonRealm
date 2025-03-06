/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package gameshop.controller;

import gameshop.DAO.GameDAO;
import gameshop.model.Game;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ainzle
 */
public class GameDetailsServlet extends HttpServlet {

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

        int id = Integer.parseInt(request.getParameter("id"));
        GameDAO gDAO = new GameDAO();

        // Find that specific game
        Game thisGame = gDAO.getGameById(id);
        if (thisGame == null) {
            response.sendRedirect("/WEB-INF/pages/404.jsp");
            return;
        }
        request.setAttribute("thisGame", thisGame);

        // For game List
        List<Game> gameList = gDAO.getGameList();
        if (gameList == null || gameList.isEmpty()) {
            response.sendRedirect("/WEB-INF/pages/404.jsp");
            return;
        }
        
        // Matching game (Similar genre) for displaying in the recommend list
        List<Game> matchingGames = new ArrayList<>();
        for (Game g : gameList) {
            for (String genre : g.getFormattedGenres().split(", ")) {
                if (thisGame.getFormattedGenres().contains(genre)) {
                    matchingGames.add(g);
                    break; // Break to not duplicating game
                }
            }
        }
        request.setAttribute("matchingGames", matchingGames);

        request.getRequestDispatcher("/WEB-INF/pages/game-details.jsp")
                .forward(request, response);
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
