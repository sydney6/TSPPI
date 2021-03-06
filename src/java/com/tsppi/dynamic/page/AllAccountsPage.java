/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsppi.dynamic.page;

import com.tsppi.bean.AllAccountBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author cruzsyd
 */
public class AllAccountsPage extends HttpServlet {

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
        
        Connection conn = null;
        PreparedStatement ps;
        ServletContext context;
        ResultSet rs;
        int i;
        try{
            context = request.getSession().getServletContext();
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(context.getInitParameter("dbURL"),context.getInitParameter("user"),context.getInitParameter("password"));
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        
        try{
            //client accounts retrieval
            ps = conn.prepareStatement("SELECT a.*, t.account_type, c.mobile, c.telephone, c.address FROM account a RIGHT JOIN client c ON a.account_num = c.account_num RIGHT JOIN type_of_account t ON a.account_type_id = t.account_type_id WHERE t.account_type='client'");
            rs = ps.executeQuery();
            
            ArrayList<AllAccountBean> cb = new ArrayList<>();
            AllAccountBean aa;
            while(rs.next()){
                aa = new AllAccountBean();
                aa.setAccountNum(rs.getInt("account_num"));
                aa.setUsername(rs.getString("username"));
                aa.setPassword(rs.getString("password"));
                aa.setFirstName(rs.getString("first_name"));
                aa.setLastName(rs.getString("last_name"));
                aa.setEmail(rs.getString("email"));
                aa.setMobile(rs.getString("mobile"));
                aa.setTelephone(rs.getString("telephone"));
                aa.setAddress(rs.getString("address"));
                aa.setAccountType(rs.getString("account_type"));
                aa.setAccountStatus(rs.getBoolean("account_status"));
                cb.add(aa);
            }
            request.setAttribute("cb", cb);
            //client accounts retrieval
           
            
            
           //employee accounts retrieval
           ps = conn.prepareStatement("SELECT a.*, t.account_type, j.job_type FROM account a RIGHT JOIN type_of_account t ON a.account_type_id = t.account_type_id RIGHT JOIN employee e ON a.account_num = e.account_num RIGHT JOIN job_position j ON e.job_id = j.job_id WHERE t.account_type='employee'");
            rs = ps.executeQuery();
            
            ArrayList<AllAccountBean> eb = new ArrayList<>();
            while(rs.next()){
                aa = new AllAccountBean();
                aa.setAccountNum(rs.getInt("account_num"));
                aa.setUsername(rs.getString("username"));
                aa.setPassword(rs.getString("password"));
                aa.setFirstName(rs.getString("first_name"));
                aa.setLastName(rs.getString("last_name"));
                aa.setEmail(rs.getString("email"));
                aa.setAccountType(rs.getString("account_type"));
                aa.setJobType(rs.getString("job_type"));
                aa.setAccountStatus(rs.getBoolean("account_status"));
                eb.add(aa);
            }
            request.setAttribute("eb", eb);
           //employee accounts retrieval
        }catch(Exception e){
            e.printStackTrace();
        }
        
        request.getRequestDispatcher("/WEB-INF/auth-page/all-accounts.jsp").forward(request,response);
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
