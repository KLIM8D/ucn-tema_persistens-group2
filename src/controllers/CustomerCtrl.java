package controllers;

import java.util.ArrayList;

import models.Customer;
import db.DBCustomer;

public class CustomerCtrl {

	public CustomerCtrl()
	{
		
	}
	
	public ArrayList<Customer> getAllCustomers(boolean retrieveAssociation) throws Exception
	{
		DBCustomer DBC = new DBCustomer();
		ArrayList<Customer> returnList = DBC.getAllCustomers(retrieveAssociation);
		return returnList;
	}
	
	public Customer getCustomerById(long id, boolean retrieveAssociation) throws Exception
	{
		DBCustomer DBC = new DBCustomer();
		return DBC.getCustomerById(id, retrieveAssociation);
	}
	
	public Customer getCustomerByName(String name, boolean retrieveAssociation) throws Exception
	{
		DBCustomer DBC = new DBCustomer();
		return DBC.getCustomerByName(name, retrieveAssociation);
	}
	
	public int insertCustomer(Customer supplier) throws Exception
	{
		DBCustomer DBC = new DBCustomer();
		return DBC.insertCustomer(supplier);
	}
	
	public int updateCustomer(Customer supplier) throws Exception
	{
		DBCustomer DBC = new DBCustomer();
		return DBC.updateCustomer(supplier);
	}
	
}
