/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuongtlc.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tuongtlc
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {
    private static final String ERROR="error.jsp";
    private static final String LOGIN = "LoginController";
    private static final String LOGOUT = "LogoutController";
    private static final String CREATE_USER = "CreateUserController";
    private static final String SEARCH_PRODUCT = "SearchProductController";
    private static final String SEARCH_ALL_PRODUCT = "SearchProductController?search=";
    private static final String DELETE_PRODUCT = "DeleteProductController";
    private static final String UPDATE_PRODUCT = "UpdateProductController";
    private static final String CREATE_PRODUCT = "CreateProductController";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String action = request.getParameter("action");
            if ("Login".equals(action)) {
                url = LOGIN;
            }else if ("Logout".equals(action)) {
                url = LOGOUT;
            }else if ("Create".equals(action)) {
                url = CREATE_USER;
            }else if ("Search".equals(action)) {
                url = SEARCH_PRODUCT;
            }else if ("Show All".equals(action)) {
                url = SEARCH_ALL_PRODUCT;
            }else if ("Delete".equals(action)) {
                url = DELETE_PRODUCT;
            }else if ("Update".equals(action)) {
                url = UPDATE_PRODUCT;
            }else if ("Create Product".equals(action)) {
                url = CREATE_PRODUCT;
            }
            
        } catch (Exception e) {
            log("ERROR at MainController " + e.toString());
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
