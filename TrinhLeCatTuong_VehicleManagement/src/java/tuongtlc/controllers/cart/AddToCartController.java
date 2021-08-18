/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuongtlc.controllers.cart;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tuongtlc.products.CartDTO;
import tuongtlc.products.CartProductDTO;
import tuongtlc.products.ProductDAO;
import tuongtlc.products.ProductDTO;
import tuongtlc.users.UserDTO;

/**
 *
 * @author tuongtlc
 */
@WebServlet(name = "AddToCartController", urlPatterns = {"/AddToCartController"})
public class AddToCartController extends HttpServlet {
    private static final String ERROR="error.jsp";
    private static final String LOGIN="login.jsp";
    private static final String SUCCESS="SearchProductController";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            
            HttpSession session = request.getSession();
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            
            int proID = Integer.parseInt(request.getParameter("productID"));
            ProductDAO dao = new ProductDAO();
            List<ProductDTO> pro = dao.getListProduct("");
            ProductDTO dto = null;
            for (ProductDTO p : pro) {
                if (proID == p.getProductID()) {
                    dto = p;
                }
            }
            String name ="";
            name = dto.getProductName();
            float price = 0;
            price = dto.getProductPrice();
            int quantity = 1;
            CartDTO cart = (CartDTO) session.getAttribute("CART");
            if (cart == null) {
                cart = new CartDTO();
            }
            CartProductDTO tea = new CartProductDTO( name.trim(), quantity, price);
            boolean check = cart.add(tea);
            if (check) {
                if (loginUser == null) {
                    url = LOGIN;
                    request.setAttribute("ERROR", " Please login to buy!");
                }else{
                    session.setAttribute("CART", cart);
                request.setAttribute("message", name +  " add to cart successfull!");
                url= SUCCESS;
                }
            }
            
        } catch (Exception e) {
            log("Error at AddToCartController "+ e.toString());
            e.printStackTrace();
        }finally{
            request.getRequestDispatcher(url).forward(request, response);
        }
        
    }

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
        processRequest(request, response);
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
        processRequest(request, response);
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
