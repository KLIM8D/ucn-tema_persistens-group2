package controllers;

import java.util.ArrayList;

import models.Customer;
import db.DBCustomer;

public class CustomerCtrl
{

	public CustomerCtrl()
	{
		
	}
	
	public ArrayList<Customer> getAllCustomers() throws Exception
	{
		DBCustomer DBC = new DBCustomer();
        return DBC.getAllCustomers();
	}
	
	public Customer getCustomerById(long id) throws Exception
	{
		DBCustomer DBC = new DBCustomer();
		return DBC.getCustomerById(id);
	}
	
	public Customer getCustomerByName(String name) throws Exception
	{
		DBCustomer DBC = new DBCustomer();
		return DBC.getCustomerByName(name);
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
