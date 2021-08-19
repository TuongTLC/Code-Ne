/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuongtlc.controllers.order;

import java.io.IOException;
import java.io.PrintWriter;
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
import tuongtlc.products.ProductDAO;
import tuongtlc.products.ProductDTO;
import tuongtlc.users.UserDTO;

/**
 *
 * @author tuongtlc
 */
@WebServlet(name = "SearchOrderController", urlPatterns = {"/SearchOrderController"})
public class SearchOrderController extends HttpServlet {
    private static final String ERROR="viewOrder.jsp";
    private static final String SUCCESS="viewOrder.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            int orderID = Integer.parseInt(request.getParameter("searchOrder"));
            HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
            String userName = user.getUserName();
            OrderDAO dao = new OrderDAO();
            OrderDTO orderDTO = dao.searchOrder(userName, orderID);
            
            ProductDAO proDAO = new ProductDAO();
            List<ProductDTO> proDTO = proDAO.getListProduct("");
            if (orderDTO!= null) {
                List<OrderProductDTO> itemList = dao.searchOrderItems(orderID);
                if (!itemList.isEmpty()) {
                    request.setAttribute("searchedOrder", orderDTO);
                    request.setAttribute("orderItems", itemList);
                    request.setAttribute("productNameList", proDTO);
                    url=SUCCESS;
                }
            }else{
                request.setAttribute("message", "Can not find order with ID: "+ orderID+" !!!");
            }
        } catch (Exception e) {
            log("ERROR at SearchOrderController "+ e.toString());
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
