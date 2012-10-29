package controllers;

import java.util.ArrayList;

import models.Contact;
import models.Customer;
import db.DBContact;
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
		DBContact DBCon = new DBContact();
		DBCon.insertContact(new Contact(supplier.getName(), supplier.getAddress(), supplier.getZipCode(), supplier.getCity(), supplier.getPhoneNo(), supplier.getEmail(), supplier.getCountry()));
		return DBC.insertCustomer(supplier);
	}
	
	public int updateCustomer(Customer supplier) throws Exception
	{
		DBCustomer DBC = new DBCustomer();
		DBContact DBCon = new DBContact();
		DBCon.updateContact(new Contact(supplier.getName(), supplier.getAddress(), supplier.getZipCode(), supplier.getCity(), supplier.getPhoneNo(), supplier.getEmail(), supplier.getCountry()));
		return DBC.updateCustomer(supplier);
	}
	
	public int deleteCustomer(Customer customer) throws Exception
	{
		DBCustomer DBC = new DBCustomer();
		return DBC.deleteCustomer(customer);
	}
	
	public int deleteContact(long data) throws Exception
	{
		DBContact DBCon = new DBContact();
		return DBCon.deleteContact(data);
	}
}
