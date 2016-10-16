/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsppi.servlet.function;

import com.tsppi.bean.ProductBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author Jasteen
 * Reference: http://www.javaknowledge.info/search-from-database-using-servlet-and-jsp/
 */

public class SearchServlet extends HttpServlet {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
 protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "tsppi";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "";
        ServletContext context;

        Statement st;
        try {
            context = request.getSession().getServletContext();
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(context.getInitParameter("dbURL"),context.getInitParameter("user"),context.getInitParameter("password"));
            String srch = request.getParameter("srch");

           
            String query = "SELECT product_name,product_detail,for_sale FROM product WHERE product_name LIKE '%"+srch+"%'";

            st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            ArrayList<ProductBean> al = new ArrayList<>();
            ProductBean pb;
            while (rs.next()) {
                pb = new ProductBean();
                pb.setProductName(rs.getString("product_name"));
                pb.setProductDetail(rs.getString("product_detail"));
                pb.setForSale(rs.getBoolean("for_sale"));
                al.add(pb);
            }
            request.setAttribute("al", al);
            request.getRequestDispatcher("/WEB-INF/auth-page/search-result.jsp").forward(request,response);
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            out.println(e);
        }
    }
 
    }
