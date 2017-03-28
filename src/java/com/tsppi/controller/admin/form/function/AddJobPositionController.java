/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsppi.controller.admin.form.function;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sydney Cruz
 */
public class AddJobPositionController extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        Connection conn = null;
        PreparedStatement ps;
        ServletContext context = request.getSession().getServletContext();;
        String inText = "";
        int i;
        int management_score = Integer.parseInt(request.getParameter("management_score"));
        int inventory_score = Integer.parseInt(request.getParameter("inventory_score"));
        boolean ms = false;
        boolean is = false;
        boolean show_job = true;
        if(management_score != 0){
            ms = true;
        }
        if(inventory_score != 0){
            is = true;
        }
        String job_type = request.getParameter("job_type");
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(context.getInitParameter("dbURL"),context.getInitParameter("user"),context.getInitParameter("password"));
            
            
            inText = "INSERT INTO job_position (job_type, management_score, inventory_score, show_job) VALUES (?,?,?,?)";
            ps = conn.prepareStatement(inText);
            ps.setString(1, job_type);
            ps.setBoolean(2, ms);
            ps.setBoolean(3, is);
            ps.setBoolean(4, show_job);
            i = ps.executeUpdate();
            
            response.sendRedirect("addjobposition");
//            if(i>0){
//                response.sendRedirect("alljobposition");
//            }else{
//                response.sendRedirect("addjobposition");
//            }
        }catch(Exception e){
            e.printStackTrace();
            out.print(e);
            context.log("Exception: " + e);
        }
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