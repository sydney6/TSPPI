/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsppi.bean;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author cruzsyd
 */
public class InvoiceBean implements Serializable{
    
    protected int invoice_id;
    protected int client_id;
    protected int status_id;
    protected String status_name;
    protected float total_amount;
    protected Date invoice_date;
    
    public InvoiceBean(){
       invoice_id = 0;
       client_id = 0;
       status_id = 0;
       status_name = "";
       total_amount = 0;
       invoice_date = new Date();
    }
    public InvoiceBean(int ii, int ci, int si, String sn, float ta, Date id){
        invoice_id = ii;
        client_id = ci;
        status_id = si;
        status_name = sn;
        total_amount = ta;
        invoice_date = id;
    }
    public void setInvoiceID(int ii){
        invoice_id = ii;
    }
    public void setClientID(int ci){
        client_id = ci;
    }
    public void setStatusID(int si){
        status_id = si;
    }
    public void setStatusName(String sn){
        status_name = sn;
    }
    public void setTotalAmount(float ta){
        total_amount = ta;
    }
    public void setInvoiceDate(Date id){
        invoice_date = id;
    }
    public int getInvoiceID(){
        return invoice_id;
    }
    public int getClientID(){
        return client_id;
    }
    public int getStatusID(){
        return status_id;
    }
    public String getStatusName(){
        return status_name;
    }
    public float getTotalAmount(){
        return total_amount;
    }
    public Date getInvoiceDate(){
        return invoice_date;
    }
}
