/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsppi.controller.mngt.page.function;

import com.tsppi.controller.account.register.function.RegisterController;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Sydney Cruz
 */
@WebServlet(name = "PaymentMethodVisibilityController", urlPatterns = {"/PaymentMethodVisibilityController"})
public class PaymentMethodVisibilityController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        Connection conn = null;
        PreparedStatement ps = null;
        ServletContext context = request.getSession().getServletContext();
        int i;
        String inText = "";
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(context.getInitParameter("dbURL"),context.getInitParameter("user"),context.getInitParameter("password"));
            
            String pm_id = request.getParameter("pm_id");
            String show_method = request.getParameter("show_method");
            boolean status;
            
            if(show_method.equals("false")){
                status = true;
            }else{
                status = false;
            }
            inText = "UPDATE invoice_pm SET show_method=? WHERE pm_id=?";
            ps = conn.prepareStatement(inText);
            ps.setBoolean(1, status);
            ps.setString(2, pm_id);
            i = ps.executeUpdate();
            if(i>0){
                Cookie mssgStatus=new Cookie("visStatus",show_method);
                mssgStatus.setMaxAge(1);
                response.addCookie(mssgStatus);
                response.sendRedirect(request.getHeader("referer"));
            }else{
                response.sendRedirect(request.getHeader("referer"));
            }
        }catch(Exception e){
            e.printStackTrace();
            context.log("Exception: " + e);
            request.setAttribute("exception_error", e);
            request.getRequestDispatcher("/WEB-INF/error/catch-error.jsp").forward(request, response);
        }finally{
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(ps != null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
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
