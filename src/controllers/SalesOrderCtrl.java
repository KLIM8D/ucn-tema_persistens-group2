package controllers;

import java.util.ArrayList;
import models.SalesOrder;
import db.DBSalesOrder;

public class SalesOrderCtrl
{

	DBSalesOrder _salesOrder;
	
	public SalesOrderCtrl()
	{
		_salesOrder = new DBSalesOrder();
	}

	/**
	 * Retrieves an ArrayList of SalesOrder Obejcts
	 * 
	 */
	public ArrayList<SalesOrder> getAllSalesOrders(boolean retrieveAssociation) throws Exception
	{
		return _salesOrder.getAllSalesOrders(retrieveAssociation);
	}
	
	/**
	 * Retrieves a SalesOrder object given the ID of the order
	 * 
	 */
	public SalesOrder getSalesOrderById(long id, boolean retrieveAssociation) throws Exception
	{
		return _salesOrder.getSalesOrderById(id, retrieveAssociation);
	}
	
	/**
	 * Retrieves an ArrayList of SalesOrder objects by the customers name
	 * 
	 */
	public ArrayList<SalesOrder> getAllSalesOrdersByCustomer(String customerName, boolean retrieveAssociation) throws Exception
	{
		return _salesOrder.getAllSalesOrdersByCustomer(customerName, retrieveAssociation);
	}

    /**
     * Retrieves an ArrayList of SalesOrder objects by the customers id / phone no
     *
     */
    public ArrayList<SalesOrder> getAllSalesOrdersByCustomer(long customerId, boolean retrieveAssociation) throws Exception
    {
        return _salesOrder.getAllSalesOrdersByCustomer(customerId, retrieveAssociation);
    }
	
	/**
	 * Takes a new salesOrder object and adds it in the DB.
	 * 
	 */
	public int insertSalesOrder(SalesOrder salesOrder) throws Exception 
	{
		return _salesOrder.insertSalesOrder(salesOrder);
	}
	
	/**
	 * Edits Existing SalesOrder in the DB with a new SalesOrder object.
	 * 
	 */
	public int updateSalesOrder(SalesOrder salesOrder) throws Exception
	{
		return _salesOrder.updateSalesOrder(salesOrder);
	}

    /**
    * Delete an SalesOrder and the associated OrderItems
    *
    * @param salesOrder the salesOrder object which should be deleted from the database
    * @return number of rows affected
    *
    */
    public int deleteSalesOrder(SalesOrder salesOrder) throws Exception
    {
        return _salesOrder.deleteSalesOrder(salesOrder);
    }
}
