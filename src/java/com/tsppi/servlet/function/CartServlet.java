/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsppi.servlet.function;

import com.tsppi.bean.CartBean;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author cruzsyd
 * ref = http://www.tech-freaks.com/jsp-servlets/shopping-cart/all-pages.html
 * 
 */
public class CartServlet extends HttpServlet {

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
        
        String action = request.getParameter("action");
        
        if(action != null && !action.equals("")){
            if(action.equals("add")){
                addToCart(request);
            }else if(action.equals("update")){
                updateCart(request);
            }else if(action.equals("x")){ //delete
                deleteCart(request);
            }
        }
        response.sendRedirect("products");
    }
    
    protected void deleteCart(HttpServletRequest request){
        HttpSession session = request.getSession();
        int item_number = Integer.parseInt(request.getParameter("item_number"));
        CartBean cb = null;
        
        Object ocb = session.getAttribute("cart");
        if(ocb != null){
            cb = (CartBean) ocb;
        }else{
            cb = new CartBean();
        }
        
        cb.deleteCartItem(item_number);
    }
    
    protected void updateCart(HttpServletRequest request){
        HttpSession session = request.getSession();
        int item_number = Integer.parseInt(request.getParameter("item_number"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        
        CartBean cb = null;
        
        Object ocb = session.getAttribute("cart");
        if(ocb != null){
            cb = (CartBean) ocb;
        }else {
            cb = new CartBean();
        }
        
        cb.updateCartItem(item_number, quantity);
    }
    
    protected void addToCart(HttpServletRequest request){
        HttpSession session = request.getSession();
        
        int item_number = Integer.parseInt(request.getParameter("item_number"));
        String item_name = request.getParameter("item_name");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        float item_cost = Float.parseFloat(request.getParameter("item_cost"));
        
        CartBean cb = null;
        Object ocb = session.getAttribute("cart");
        
        if(ocb != null){
            cb = (CartBean) ocb;
        }else{
            cb = new CartBean();
            session.setAttribute("cart", cb);
        }
        
        cb.addCartItem(item_number, item_name, quantity, item_cost);
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
