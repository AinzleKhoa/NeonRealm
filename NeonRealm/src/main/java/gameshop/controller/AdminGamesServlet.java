/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package gameshop.controller;

import gameshop.DAO.AdminGamesDAO;
import gameshop.model.AdminGames;
import gameshop.util.SessionUtil;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Pham Van Hoai - CE181744
 */
@WebServlet(name = "AdminGamesServlet", urlPatterns = {"/admin/games"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class AdminGamesServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String UPLOAD_DIR = "/assets/img/cards/"; // Thư mục lưu ảnh

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Kiểm tra quyền admin
        if (!SessionUtil.isAdmin(request)) {
            response.sendRedirect(request.getContextPath() + "/pages/home.jsp");
            return;
        }

        AdminGamesDAO adminGamesDAO = new AdminGamesDAO();

        // Xử lý yêu cầu thêm game
        if (request.getParameter("add") != null) {
            loadFormData(request, adminGamesDAO);
            request.getRequestDispatcher("/admin/add-game.jsp").forward(request, response);
            return;
        }

        // Xử lý yêu cầu chỉnh sửa game
        String editId = request.getParameter("editId");
        if (editId != null) {
            try {
                int gameId = Integer.parseInt(editId);
                AdminGames game = adminGamesDAO.getGameById(gameId);

                if (game == null) {
                    response.sendRedirect(request.getContextPath() + "/pages/404.jsp");
                    return;
                }

                // Lấy dữ liệu danh sách để hiển thị trong form chỉnh sửa
                loadFormData(request, adminGamesDAO);
                request.setAttribute("game", game);
                request.getRequestDispatcher("/admin/edit-game.jsp").forward(request, response);
                return;
            } catch (NumberFormatException e) {
                response.sendRedirect(request.getContextPath() + "/admin/games");
                return;
            }
        }

        // Xử lý hiển thị danh sách game (lọc theo thể loại nếu có)
        List<String> allGenres = adminGamesDAO.getAllGenres();
        request.setAttribute("allGenres", allGenres);

        String selectedGenre = request.getParameter("genre");
        List<AdminGames> games = (selectedGenre == null || selectedGenre.isEmpty())
                ? adminGamesDAO.getAllGames()
                : adminGamesDAO.getGamesByGenre(selectedGenre);

        request.setAttribute("games", games);
        request.getRequestDispatcher("/admin/games.jsp").forward(request, response);
    }

    /**
     * Lấy dữ liệu danh sách để hiển thị trong form chỉnh sửa hoặc thêm game
     */
    private void loadFormData(HttpServletRequest request, AdminGamesDAO adminGamesDAO) {
        request.setAttribute("allGenres", adminGamesDAO.getAllGenres());
        request.setAttribute("allCategories", adminGamesDAO.getAllCategories());
        request.setAttribute("allDevelopers", adminGamesDAO.getAllDevelopers());
        request.setAttribute("allPublishers", adminGamesDAO.getAllPublishers());
        request.setAttribute("allPlatforms", adminGamesDAO.getAllPlatforms());
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
        AdminGamesDAO adminGamesDAO = new AdminGamesDAO();

        if ("add".equals(action) || "edit".equals(action)) {
            try {
                // Nhận dữ liệu từ form
                int gameId = "edit".equals(action) ? Integer.parseInt(request.getParameter("game_id")) : 0;
                String title = request.getParameter("title");
                String description = request.getParameter("description");
                BigDecimal price = new BigDecimal(request.getParameter("price"));
                Date releaseDate = Date.valueOf(request.getParameter("release_date"));

                // Xử lý upload ảnh
                String imageUrl = handleFileUpload(request, gameId, adminGamesDAO);

                // Nhận danh sách từ form, nếu null thì dùng danh sách rỗng
                List<String> genres = getSafeList(request.getParameterValues("genres"));
                List<String> categories = getSafeList(request.getParameterValues("categories"));
                List<String> developers = getSafeList(request.getParameterValues("developers"));
                List<String> publishers = getSafeList(request.getParameterValues("publishers"));
                List<String> platforms = getSafeList(request.getParameterValues("platforms"));

                AdminGames game = new AdminGames(gameId, title, description, imageUrl, price, releaseDate, developers, publishers, genres, platforms, categories);

                if ("add".equals(action)) {
                    adminGamesDAO.addGame(game);
                    request.getSession().setAttribute("successMessage", "Add game successfully!");
                } else {
                    adminGamesDAO.updateGame(game);
                    request.getSession().setAttribute("successMessage", "Game updated successfully!");
                }

                response.sendRedirect(request.getContextPath() + "/admin/games");
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect(request.getContextPath() + "/admin/games?error=1");
            }
        } else if ("delete".equals(action)) {
            int gameId = Integer.parseInt(request.getParameter("gameId"));
            adminGamesDAO.deleteGame(gameId);
            request.getSession().setAttribute("successMessage", "Game deleted successfully!");
            response.sendRedirect(request.getContextPath() + "/admin/games");
        }
    }

    /**
     * Xử lý upload ảnh, nếu không có ảnh mới thì giữ ảnh cũ.
     */
    private String handleFileUpload(HttpServletRequest request, int gameId, AdminGamesDAO adminGamesDAO) throws IOException, ServletException {
        Part filePart = request.getPart("image");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String imageUrl = null;

        if (fileName != null && !fileName.isEmpty()) {
            String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String filePath = uploadPath + File.separator + fileName;
            filePart.write(filePath);
            imageUrl = fileName;
        } else if (gameId != 0) {
            // Nếu không có ảnh mới, lấy ảnh cũ từ DB
            AdminGames existingGame = adminGamesDAO.getGameById(gameId);
            if (existingGame != null) {
                imageUrl = existingGame.getImageUrl();
            }
        }
        return imageUrl;
    }

    /**
     * Kiểm tra danh sách từ request, nếu null thì trả về danh sách rỗng để
     * tránh lỗi.
     */
    private List<String> getSafeList(String[] values) {
        return (List<String>) ((values != null) ? Arrays.asList(values) : new ArrayList<>());
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
