/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuongtlc.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author trinh
 */
public class MainController extends HttpServlet {
    private static final String ERROR="error.jsp";
    private static final String LOGIN="LoginController";
     private static final String SEARCH="SearchController";
     private static final String LOGOUT="LogoutController";
    private static final String DELETE="DeleteController"; 
    private static final String UPDATE="UpdateController";
    private static final String CREATE="CreateController";
    private static final String ADD_TO_CART="AddToCartController";
    private static final String VIEW_CART="viewCart.jsp";
     private static final String REMOVE_FROM_CART="RemoveFromCartController";
     private static final String EDIT_FROM_CART="EditFromCartController";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = ERROR;
        try {
            String action = request.getParameter("action");
            if ("Login".equals(action)) {
                url=LOGIN;
            }else if ("Search".equals(action)) {
                url = SEARCH;
            }else if ("Logout".equals(action)) {
                url = LOGOUT;
            }else if ("Delete".equals(action)) {
                url = DELETE;
            }else if ("Update".equals(action)) {
                url = UPDATE;
            }else if ("Create".equals(action)) {
                url = CREATE;
            }else if ("Add".equals(action)) {
                url = ADD_TO_CART;
            }else if ("View".equals(action)) {
                url = VIEW_CART;
            }else if ("Remove".equals(action)) {
                url = REMOVE_FROM_CART;
            }else if ("Edit".equals(action)) {
                url = EDIT_FROM_CART;
            }
        } catch (Exception e) {
            log("ERROR at MainController" + e.toString());
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
