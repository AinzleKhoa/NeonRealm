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
public class CatalogServlet extends HttpServlet {

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

        GameDAO gDAO = new GameDAO();

        // Get platform and genre name
        List<String> platformNames = gDAO.getPlatformName();
        List<String> genreNames = gDAO.getGenreName();
        request.setAttribute("platformNames", platformNames);
        request.setAttribute("genreNames", genreNames);

        int currentPage = 1;
        int totalGamesPerPage = 8;
        int totalGames = gDAO.countGames();
        if (request.getParameter("page") != null) {
            try {
                currentPage = Integer.parseInt(request.getParameter("page"));
            } catch (NumberFormatException e) {
                currentPage = 1; // Default to page 1 if invalid input
            }
        }
        // Calculate OFFSET correctly
        int nextGame = (currentPage - 1) * totalGamesPerPage; // First game start at index 0 thus why - 1
        List<Game> gameListPerPage = gDAO.getPagination(nextGame, totalGamesPerPage);
        request.setAttribute("gameListPerPage", gameListPerPage);

        // Calculate total pages correctly, e.g if 8.5 it will round up to 9
        int numOfPages = (int) Math.ceil((double) totalGames / totalGamesPerPage);
        request.setAttribute("numOfPages", numOfPages);

        // Calculate current total games. At final page will display max from max numOfPages
        int currentTotalGames = (currentPage < numOfPages ? totalGamesPerPage * currentPage : totalGames);
        request.setAttribute("currentTotalGames", currentTotalGames);

        // Calculate total games
        request.setAttribute("totalGames", gDAO.countGames());

        request.getRequestDispatcher("/WEB-INF/pages/catalog.jsp")
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
        GameDAO gDAO = new GameDAO();

        // Get platform and genre name
        List<String> platformNames = gDAO.getPlatformName();
        List<String> genreNames = gDAO.getGenreName();
        request.setAttribute("platformNames", platformNames);
        request.setAttribute("genreNames", genreNames);

        // Retrieve selected filters (if any)
        String[] selectedPlatforms = request.getParameterValues("platforms");
        String[] selectedGenres = request.getParameterValues("genres");
        String keyword = request.getParameter("keyword");

        if (selectedPlatforms == null) {
            selectedPlatforms = new String[0]; // Prevent null errors
        }
        if (selectedGenres == null) {
            selectedGenres = new String[0]; // Prevent null errors
        }

        // Store selected Platform and Genre in request attributes
        request.setAttribute("selectedPlatforms", selectedPlatforms);
        request.setAttribute("selectedGenres", selectedGenres);

        List<Game> gameList = gDAO.getGameList();
        List<Game> matchingGames = new ArrayList<>();
        for (Game game : gameList) {
            boolean matchesPlatform = (selectedPlatforms.length == 0); // If no filter applied, allow all
            boolean matchesGenre = (selectedGenres.length == 0); // If no filter applied, allow all
            boolean matchesKeyword = (keyword == null);

            for (String platform : selectedPlatforms) {
                if (game.getFormattedPlatforms().contains(platform)) {
                    matchesPlatform = true;
                    break;
                }
            }
            for (String genre : selectedGenres) {
                if (game.getFormattedGenres().contains(genre)) {
                    matchesGenre = true;
                    break;
                }
            }
            if (matchesPlatform && matchesGenre) {
                matchingGames.add(game);
            }
        }

        request.setAttribute("filteredGameList", matchingGames);

        request.getRequestDispatcher("/WEB-INF/pages/catalog.jsp")
                .forward(request, response);
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
