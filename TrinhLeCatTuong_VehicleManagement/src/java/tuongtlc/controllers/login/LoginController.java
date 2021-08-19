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
import javax.servlet.http.HttpSession;
import tuongtlc.users.UserDAO;
import tuongtlc.users.UserDTO;

/**
 *
 * @author tuongtlc
 */
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {
    private static final String ERROR="login.jsp";
    private static final String ADMIN_PAGE="admin.jsp";
    private static final String SHOPING_PAGE="shoping.jsp"; 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String userName = request.getParameter("txtUserName");
            String password = request.getParameter("txtPassword");
            UserDAO dao = new UserDAO();
            UserDTO dto = dao.checkLogin(userName, password);
            HttpSession session = request.getSession();
            if (dto != null) {
                session.setAttribute("LOGIN_USER", dto);
                int roleID = dto.getRoleID();
                if (roleID == 1) {
                    url=ADMIN_PAGE;
                }else if (roleID == 2) {
                    url = SHOPING_PAGE;
                }else{
                    request.setAttribute("ERROR", " Role not supported!");
                }
            }else{
                request.setAttribute("ERROR", " Incorrect User Name or Password!!!");
            }
        } catch (Exception e) {
            log("ERROR at LoginController "+ e.toString());
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
