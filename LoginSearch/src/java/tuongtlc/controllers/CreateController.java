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
import tuongtlc.user.UserDAO;
import tuongtlc.user.UserDTO;
import tuongtlc.user.UserError;

/**
 *
 * @author trinh
 */
public class CreateController extends HttpServlet {
    private static final String ERROR="createUser.jsp";
    private static final String SUCCESS="login.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        UserError userErr = new UserError();
        try {
            String userID = request.getParameter("txtUserID");
            String fullName = request.getParameter("txtFullName");
            String password = request.getParameter("txtPassword");
            String confirm = request.getParameter("txtConfirm");
            String roleID ="US";
            boolean check = true;
            
            if (userID.length()<=3 || userID.length()>10) {
                check = false;
                userErr.setUserIDError("UserID must >=3 and <=10");
            }
            if (fullName.length()<=5 || fullName.length()>20) {
                check = false;
                userErr.setFullNameError("Full Name must >=3 and <=10");
            }
            if (password.equals(confirm)) {
                check = false;
                userErr.setConfirmError("Confirm error!!!");
            }
            if ( check) {
               UserDAO dao = new UserDAO();
               
               boolean checkDup = dao.checkDuplicate(userID);
                if (checkDup) {
                    userErr.setUserIDError("Duplidate UserID!!!");
                    request.setAttribute("ERROR_USER", userErr);;
                }else{
                    UserDTO user = new UserDTO(userID, fullName, roleID, password);
                    boolean checkInsert = dao.insert(user);
                    if (checkInsert) {
                        url = SUCCESS;
                    }
                }
//            if (check) {
//                UserDAO dao = new UserDAO();
//                UserDTO user = new UserDTO(userID, fullName, roleID, password);
//                boolean checkInsert = dao.insert2(user);
//                if (checkInsert) {
//                    url = SUCCESS;
//                }
//            }
            }
            else{
             request.setAttribute("ERROR_USER", userErr);
            }
            
        } catch (Exception e) {
            if (e.toString().contains("duplicate")) {
                userErr.setUserIDError("Duplicated!!!");
                request.setAttribute("ERROR_USER", userErr);
            }
            e.printStackTrace();
            //log("ERROR at CreateCOntroller"+ e.toString());
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
