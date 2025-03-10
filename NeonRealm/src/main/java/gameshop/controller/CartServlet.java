package gameshop.controller;

import gameshop.DAO.CartDAO;
import gameshop.model.Cart;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;
import static java.lang.System.out;

public class CartServlet extends HttpServlet {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //HttpSession session = request.getSession();
        //Integer userId = (Integer) session.getAttribute("userId");
        int userId = 3;
        String action = request.getParameter("action");
        //if (userId != null) {
        CartDAO cartDAO = new CartDAO();
        //List<Cart> cartList = cartDAO.getAllCarts();
        //double totalPrice = cartDAO.getAllTotalCartPrice();
        List<Cart> cartList = cartDAO.getCartByUserId(userId);
        double totalPrice = cartDAO.getTotalCartPrice(userId);

        // Đưa dữ liệu vào request
        request.setAttribute("cartList", cartList);
        request.setAttribute("cartCount", cartList.size());
        request.setAttribute("totalPrice", totalPrice);
        /**
         * }else{ request.setAttribute("cartCount", 0);
         * request.setAttribute("totalPrice", 0.0); }*
         */
        // Forward về trang header.jsp
        request.getRequestDispatcher("pages/home.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        int userId = 3;
        // Lấy userId từ session (chỉ hoạt động nếu đã đăng nhập)
        //HttpSession session = request.getSession();
        //Integer userId = (Integer) session.getAttribute("userId");

        /**
         * if (userId == null) {
         * response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
         * ""); return; }*
         */
        // Lấy gameId từ request
        String gameIdStr = request.getParameter("id");
        if (gameIdStr == null || !gameIdStr.matches("\\d+")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Game id incorect.");
            return;
        }
        int gameId = Integer.parseInt(gameIdStr);

        // Gọi DAO để thêm vào giỏ hàng
        CartDAO cartDAO = new CartDAO();
        
        boolean exists = cartDAO.isGameInCart(userId, gameId);
        if (exists) {
            out.print("{\"status\":\"exists\"}"); // Trả về nếu game đã có trong giỏ
            out.flush();
            return;
        }
        
        boolean success = cartDAO.addToCart(userId, gameId);
        if (success) {
            int cartCount = cartDAO.getCartByUserId(userId).size();
            double totalPrice = cartDAO.getTotalCartPrice(userId);
            out.print("{\"status\":\"success\", \"cartCount\":" + cartCount + ", \"totalPrice\":" + totalPrice + "}");
        } else {
            out.print("{\"status\":\"error\"}");
        }
        out.flush();
        // Nếu thêm thành công, lấy lại giỏ hàng của user
        //List<Cart> cartList = cartDAO.getCartByUserId(userId);
        //double totalPrice = cartDAO.getTotalCartPrice(userId);
        // Trả về JSON
    }
}
