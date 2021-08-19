/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuongtlc.controllers.login;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tuongtlc.users.UserDAO;
import tuongtlc.users.UserDTO;
import tuongtlc.users.UserErr;

/**
 *
 * @author tuongtlc
 */
@WebServlet(name = "CreateUserController", urlPatterns = {"/CreateUserController"})
public class CreateUserController extends HttpServlet {

    private static final String ERROR = "createUser.jsp";
    private static final String SUCCESS = "login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        UserErr userErr = new UserErr();
        try {
            String userName = request.getParameter("txtUserName");
            String fullName = request.getParameter("txtUserFullName");
            String password = request.getParameter("txtPassword");
            String confirm = request.getParameter("txtConfirm");
            int roleID = 2;
            boolean check = true;

            UserDAO dao = new UserDAO();
            boolean checkDup = dao.checkDuplicate(userName);
            if (checkDup) {
                check = false;
                userErr.setUserNameError("Duplidate UserName!!!");
            }
            if (userName.length() <= 3 || userName.length() > 10) {
                check = false;
                userErr.setUserNameError("UserID must >=3 and <=10");
            }
            if (fullName.length() <= 5 || fullName.length() > 20) {
                check = false;
                userErr.setUserFullNameError("Full Name must >=3 and <=10");
            }
            if (password.length() < 6 || password.length() > 20) {
                check = false;
                userErr.setPasswordError("Password must >=6 and <=20");
            }
            if (!password.equals(confirm)) {
                check = false;
                userErr.setConfirmError("Confirm not match !!!");
            }
            if (check) {

                request.setAttribute("ERROR_USER", userErr);

                UserDTO user = new UserDTO(userName, fullName, password, roleID);
                boolean checkInsert = dao.insert(user);
                if (checkInsert) {
                    url = SUCCESS;

                }
            } else {
                request.setAttribute("ERROR_USER", userErr);
            }

        } catch (Exception e) {
            e.printStackTrace();
            log("ERROR at CreateUserController" + e.toString());
        } finally {
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
