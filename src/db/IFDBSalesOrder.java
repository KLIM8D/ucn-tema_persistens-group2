package db;

import models.SalesOrder;

import java.util.ArrayList;

public interface IFDBSalesOrder
{
	
	/**
	 * Get all SalesOrders in the Database
	 * 
	 * @param retrieveAssociation set to true if all SalesOrders are to be returned
	 * @return ArrayList of all SalesOrder objects in Database
	 */
	public ArrayList<SalesOrder> getAllSalesOrders(boolean retrieveAssociation) throws Exception;
	
	/**
	 * Get a specific SalesOrder by SalesOrder Id
	 * 
	 * @param id the Id of the SalesOrder to be returned
	 * @param retrieveAssociation set to true if SalesOrder with id is to be returned
	 * @return SalesOrder object with ID
	 */
	public SalesOrder getSalesOrderFromId(long id, boolean retrieveAssociation) throws Exception;
	
	/**
	 * Get all SalesOrders for a specific customer from customerName
	 * 
	 * @param customerName the name of a customer for whom all SalesOrders are to be returned
	 * @param retrieveAssociation set to true if you want SalesOrders for customer to be returned
	 * @return ArrayList of SalesOrders for a customer with customerName
	 */
	public ArrayList<SalesOrder> getAllSalesOrdersFromCustomer(String customerName, boolean retrieveAssociation) throws Exception;
	
	/**
	 * Insert a new SalesOrder into the database
	 * 
	 * @param salesOrder salesOrder object with the data to be added
	 * @return int returns the number of rows affected
	 * @throws Exception
	 */
	public int insertSalesOrder(SalesOrder salesOrder) throws Exception;
		
	/**
	 * Update a SalesOrder that already exists in the database
	 * 
	 * @param salesOrder the salesOrder object that contain a valid id and the new data that should be updated
	 * @return int returns the number of rows affected
	 */
	public int updateSalesOrder(SalesOrder salesOrder) throws Exception;

    /**
     * Delete a SalesOrder from the database and all the OrderItems associated with it
     *
     * @param salesOrder the salesOrder object that contain a valid id
     * @return int returns the number of rows affected
     */
    public int deleteSalesOrder(SalesOrder salesOrder) throws Exception;
}
