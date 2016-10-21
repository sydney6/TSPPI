/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsppi.servlet.function;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author cruzsyd
 */
public class EditProductServlet extends HttpServlet {

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
       
        String product_id = request.getParameter("product_id");
        String product_name = request.getParameter("product_name");
        String price = request.getParameter("msrp");
        String stock = request.getParameter("stock");
        String product_detail = request.getParameter("product_detail");
        String category_id = request.getParameter("product_category");
        
        Connection conn = null;
        PreparedStatement ps;
        ServletContext context;
        int i;
        try{
            context = request.getSession().getServletContext();
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(context.getInitParameter("dbURL"),context.getInitParameter("user"),context.getInitParameter("password"));
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        } 
        
        try{
            if(!product_name.equals("")){
                ps = conn.prepareStatement("UPDATE product SET product_name = ? WHERE product_id = ?");
                ps.setString(1, product_name);
                ps.setString(2, product_id);
                i = ps.executeUpdate();
            }
            if(!price.equals("")){
                ps = conn.prepareStatement("UPDATE product SET msrp = ? WHERE product_id = ?");
                ps.setString(1, price);
                ps.setString(2, product_id);
                i = ps.executeUpdate();
            }
            if(!stock.equals("")){
                ps = conn.prepareStatement("UPDATE product SET stock = ? WHERE product_id = ?");
                ps.setInt(1, Integer.parseInt(stock));
                ps.setString(2, product_id);
                i = ps.executeUpdate();
            }
            if(!product_detail.equals("")){
                ps = conn.prepareStatement("UPDATE product SET product_detail = ? WHERE product_id = ?");
                ps.setString(1, product_detail);
                ps.setString(2, product_id);
                i = ps.executeUpdate();
            }
            if(!category_id.equals("")){
                ps = conn.prepareStatement("UPDATE product SET category_id = ? WHERE product_id = ?");
                ps.setString(1, category_id);
                ps.setString(2, product_id);
                i = ps.executeUpdate();
            } 
            response.sendRedirect("productapproval");
        }catch(Exception e){
            out.println(e);
            e.printStackTrace();
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
