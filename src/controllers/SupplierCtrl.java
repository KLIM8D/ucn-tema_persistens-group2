package controllers;

import java.util.ArrayList;
import models.Supplier;
import models.Contact;
import db.DBSupplier;
import db.DBContact;

public class SupplierCtrl
{

	public SupplierCtrl()
	{
		
	}
	
	public ArrayList<Supplier> getAllSuppliers() throws Exception
	{
		DBSupplier DBS = new DBSupplier();
		ArrayList<Supplier> returnList = DBS.getAllSuppliers();
		return returnList;
	}
	
	public Supplier getSupplierById(long id) throws Exception
	{
		DBSupplier DBS = new DBSupplier();
		return DBS.getSupplierById(id);
	}
	
	public Supplier getSupplierByName(String name) throws Exception
	{
		DBSupplier DBS = new DBSupplier();
		return DBS.getSupplierByName(name);
	}
	
	public int insertSupplier(Supplier supplier) throws Exception
	{
		DBSupplier DBS = new DBSupplier();
		DBContact DBC = new DBContact();
		DBC.insertContact(new Contact(supplier.getName(), supplier.getAddress(), supplier.getZipCode(), supplier.getCity(), supplier.getPhoneNo(), supplier.getEmail(), supplier.getCountry()));
		return DBS.insertSupplier(supplier);
	}
	
	public int updateSupplier(Supplier supplier) throws Exception
	{
		DBSupplier DBS = new DBSupplier();
		DBContact DBC = new DBContact();
		DBC.updateContact(new Contact(supplier.getName(), supplier.getAddress(), supplier.getZipCode(), supplier.getCity(), supplier.getPhoneNo(), supplier.getEmail(), supplier.getCountry()));
		return DBS.updateSupplier(supplier);
	}
	
	public int deleteSupplier(Supplier supplier) throws Exception
	{
		DBSupplier DBS = new DBSupplier();
		return DBS.deleteSupplier(supplier);
	}
}
