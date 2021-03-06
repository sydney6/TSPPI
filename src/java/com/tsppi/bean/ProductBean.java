/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsppi.bean;

import java.io.Serializable;

/**
 *
 * @author cruzsyd
 */
public class ProductBean implements Serializable{
    
    private int product_id;
    private int category_id;
    private String category_name;
    private String product_name;
    private float msrp;
    private int stock;
    private String product_detail;
    private boolean for_sale;
    
    public ProductBean(){
        product_id = 0;
        category_id = 0;
        category_name = "";
        product_name = "";
        msrp = (float) 0.0;
        stock = 0;
        product_detail = "";
        for_sale = false;
    }
    
    public ProductBean(int pid, int cid, String cn, String pn, float m, int s, String pd, boolean fs){
        product_id = pid;
        category_id = cid;
        category_name = cn;
        product_name = pn;
        msrp = m;
        stock = s;
        product_detail = pd;
        for_sale = fs;
    }
    
    public void setProductID(int pid){
        product_id = pid;
    }
    public void setCategoryID(int cid){
        category_id = cid;
    }
    public void setCategoryName(String cn){
        category_name = cn;
    }
    public void setProductName(String pn){
        product_name = pn;
    }
    public void setMSRP(float m){
        msrp = m;
    }
    public void setStock(int s){
        stock = s;
    }
    public void setProductDetail(String pd){
        product_detail = pd;
    }
    public void setForSale(boolean fs){
        for_sale = fs;
    }
    
    public int getProductID(){
        return product_id;
    }
    public int getCategoryID(){
        return category_id;
    }
    public String getCategoryName(){
        return category_name;
    }
    public String getProductName(){
        return product_name;
    }
    public float getMSRP(){
        return msrp;
    }
    public int getStock(){
        return stock;
    }
    public String getProductDetail(){
        return product_detail;
    }
    public boolean getForSale(){
        return for_sale;
    }
}