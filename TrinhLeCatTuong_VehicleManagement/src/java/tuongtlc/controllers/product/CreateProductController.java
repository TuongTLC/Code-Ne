/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuongtlc.controllers.product;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tuongtlc.products.BrandDTO;
import tuongtlc.products.ProductDAO;
import tuongtlc.products.ProductDTO;
import tuongtlc.products.ProductErr;

/**
 *
 * @author tuongtlc
 */
@WebServlet(name = "CreateProductController", urlPatterns = {"/CreateProductController"})
public class CreateProductController extends HttpServlet {
    private static final String ERROR = "createProduct.jsp";
    private static final String SUCCESS = "admin.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            boolean check = true;
            ProductErr proErr = new ProductErr();
            String productName = request.getParameter("txtProductName");
            float price=0;
            try {
                 price = Float.parseFloat(request.getParameter("txtProductPrice"));
            } catch (Exception e) {
                proErr.setPriceErr("Price is a number!");
                check = false;
            }
            String brandName = request.getParameter("txtBrand");
            String description = request.getParameter("txtDescription");
            int quantity = 0;
            try {
                 quantity = Integer.parseInt(request.getParameter("txtQuantity"));
            } catch (Exception e) {
                proErr.setQuantityErr("Quantity is a number");
                check = false;
            }
            int id = 0;
            int sold = 0;
            
            
            if (productName.trim().length()<5 || productName.trim().length()>50) {
                proErr.setProductNameErr("Product Name length must >5 and <50");
                check = false;
            }
            if (brandName.trim().length()<=0 ||  brandName.trim().length()>50) {
                proErr.setBrandErr("Brand Name length must >0 and <50");
                check = false;
            }
            if (description.trim().length()<=0 ||  description.trim().length()>200) {
                proErr.setBrandErr("Description length must >0 and <200");
                check = false;
            }
            int brandID=0;
            boolean brandCheck=false;
            ProductDAO dao = new ProductDAO();
            List<BrandDTO> brandList = dao.getListBrand();
            for (BrandDTO brand : brandList) {
                if (brand.getBrandName().equalsIgnoreCase(brandName)) {
                    brandID = brand.getBrandID();
                    brandCheck=false;
                    break;
                }else{
                    brandCheck = true;
                }
            }
            if (brandCheck) {
                proErr.setBrandErr("Brand name not in database!");
                check=false;
            }
            if (check) {
                request.setAttribute("PRODUCT_ERROR", proErr);
                ProductDTO pro = new ProductDTO(id, productName, price, description, brandID, quantity, sold);
                boolean checkInsert = dao.insert(pro);
                if (checkInsert) {
                    url = SUCCESS;
                }
            }else{
                request.setAttribute("PRODUCT_ERROR", proErr);
            }
        } catch (Exception e) {
            log("ERROR at CreateProductController "+e.toString());
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
