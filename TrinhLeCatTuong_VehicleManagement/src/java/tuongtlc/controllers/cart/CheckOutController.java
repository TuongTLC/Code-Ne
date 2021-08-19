/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuongtlc.controllers.cart;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tuongtlc.order.OrderDAO;
import tuongtlc.order.OrderDTO;
import tuongtlc.order.OrderProductDTO;
import tuongtlc.products.CartProductDTO;
import tuongtlc.products.ProductDTO;
import tuongtlc.users.UserDTO;

/**
 *
 * @author tuongtlc
 */
@WebServlet(name = "CheckOutController", urlPatterns = {"/CheckOutController"})
    public class CheckOutController extends HttpServlet {
    private static final String ERROR="error.jsp";
    private static final String SUCCESS="shoping.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = ERROR;
        try {
            OrderDAO dao = new OrderDAO();
            HttpSession session = request.getSession();
            UserDTO loginUser = (UserDTO)session.getAttribute("LOGIN_USER");
            boolean orderCheck = dao.insertOrder(loginUser.getUserName());
            List<CartProductDTO> cart = (List<CartProductDTO>)session.getAttribute("orderList");
            List<OrderProductDTO> order = new ArrayList<>();
            List<ProductDTO> proList = dao.getListProduct("");
            OrderDTO orderDTO = dao.getOrderList(loginUser.getUserName());
            String productName;
            int productID = 0;
            int quantity;
            for (CartProductDTO cartP : cart) {
                productName = cartP.getName();
                quantity = cartP.getQuantity();
                for (ProductDTO proDTO : proList) {
                    if (productName.equalsIgnoreCase(proDTO.getProductName())) {
                        productID = proDTO.getProductID();
                    }
                }
              order.add(new OrderProductDTO(orderDTO.getOrderID(), productID, quantity));
            }
            
            boolean itemCheck = false;
            for (OrderProductDTO c : order) {
                dao.insertOrderItem(c);
                itemCheck = true;
            }
            if (orderCheck==true && itemCheck==true) {
                url = SUCCESS;
                request.setAttribute("message", "Check out complete, your order ID is "+orderDTO.getOrderID()+" !!!");
            }
            
        } catch (SQLException e) {
            log("ERROR at CheckOutController "+e.toString());
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
