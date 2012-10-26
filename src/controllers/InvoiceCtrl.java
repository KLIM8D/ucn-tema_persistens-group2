package controllers;

import java.util.ArrayList;

import models.Invoice;
import db.DBInvoice;

public class InvoiceCtrl {
	
	public InvoiceCtrl()
	{
		
	}
	
	public ArrayList<Invoice> getAllInvoice() throws Exception
	{
		DBInvoice DBI = new DBInvoice();
        return DBI.getAllInvoices();
	}
	
	public Invoice getInvoiceById(int id) throws Exception
	{
		DBInvoice DBI = new DBInvoice();
		return DBI.getInvoiceById(id);
	}
	
	public int insertInvoice(Invoice invoice) throws Exception
	{
		DBInvoice DBI = new DBInvoice();
		return DBI.insertInvoice(invoice);
	}
	
	public int updateInvoice(Invoice invoice) throws Exception
	{
		DBInvoice DBI = new DBInvoice();
		return DBI.updateInvoice(invoice);
	}
}
