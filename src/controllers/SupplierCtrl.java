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
	
	public ArrayList<Supplier> getAllSuppliers(boolean retrieveAssociation) throws Exception
	{
		DBSupplier DBS = new DBSupplier();
		ArrayList<Supplier> returnList = DBS.getAllSuppliers();
		return returnList;
	}
	
	public Supplier getSupplierById(long id, boolean retrieveAssociation) throws Exception
	{
		DBSupplier DBS = new DBSupplier();
		return DBS.getSupplierById(id);
	}
	
	public Supplier getSupplierByName(String name, boolean retrieveAssociation) throws Exception
	{
		DBSupplier DBS = new DBSupplier();
		return DBS.getSupplierByName(name);
	}
	
	public int insertSupplier(Supplier supplier) throws Exception
	{
		DBSupplier DBS = new DBSupplier();
		return DBS.insertSupplier(supplier);
	}
	
	public int updateSupplier(Supplier supplier) throws Exception
	{
		DBSupplier DBS = new DBSupplier();
		return DBS.updateSupplier(supplier);
	}
	
	public Contact getContact(long PhoneNo) throws Exception
	{
		DBContact DBC = new DBContact();
		return DBC.getContactById(PhoneNo);
	}
}
