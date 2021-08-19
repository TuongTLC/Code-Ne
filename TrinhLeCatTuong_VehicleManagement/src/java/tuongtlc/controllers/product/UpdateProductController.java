/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuongtlc.controllers.product;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tuongtlc.products.BrandDTO;
import tuongtlc.products.ProductDAO;
import tuongtlc.products.ProductDTO;

/**
 *
 * @author tuongtlc
 */
@WebServlet(name = "UpdateProductController", urlPatterns = {"/UpdateProductController"})
public class UpdateProductController extends HttpServlet {
    private static final String ERROR="admin.jsp";
    private static final String SUCCESS="SearchProductController";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            int productID = Integer.parseInt(request.getParameter("productID"));
            String productName = request.getParameter("productName");
            float price = Float.parseFloat(request.getParameter("productPrice"));
            
            int brandID = 0;
            String brandName= request.getParameter("brandID");
            ProductDAO dao = new ProductDAO();
            List<BrandDTO> brandList = dao.getListBrand();
            for (BrandDTO brand : brandList) {
                if (brand.getBrandName().equalsIgnoreCase(brandName)) {
                    brandID = brand.getBrandID();
                }
            }
            
            String description = request.getParameter("productDescription");
            int  quantity = Integer.parseInt(request.getParameter("productQuantity"));
            int sold = Integer.parseInt(request.getParameter("productSold"));
            
            ProductDTO dto = new ProductDTO(productID, productName, price, description, brandID, quantity, sold);
            boolean check = dao.update(dto);
            if (check) {
                url = SUCCESS; 
            }else{
                request.setAttribute("ERROR", "Can not update!");
            }
            
        } catch (Exception e) {
            log("ERROR at UpdateController"+e.toString());
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
