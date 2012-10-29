package controllers;

import java.util.ArrayList;

import models.OrderItems;
import models.SalesOrder;
import db.DBSalesOrder;
import db.DBOrderItems;

public class SalesOrderCtrl
{

	private DBSalesOrder _salesOrder;
    private DBOrderItems _dbOrderItems;
	
	public SalesOrderCtrl()
	{
		_salesOrder = new DBSalesOrder();
        _dbOrderItems = new DBOrderItems();
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

    /**
     * Get all OrderItems in the Database
     *
     * @param retrieveAssociation set to true if all OrderItems are to be returned
     * @return ArrayList of all OrderItem objects in Database
     */
    public ArrayList<OrderItems> getAllOrderItems(boolean retrieveAssociation) throws Exception
    {
        return _dbOrderItems.getAllOrderItems(retrieveAssociation);
    }


    /**
     * Get a specific OrderItem by orderKey and productKey
     *
     * @param orderKey the Id of the Order
     * @param productKey the Id of the Product
     * @param retrieveAssociation set to true if OrderItem with id is to be returned
     * @return OrderItem object with orderKey and productKey
     */
    public OrderItems getOrderItemById(long orderKey, long productKey, boolean retrieveAssociation) throws Exception
    {
        return _dbOrderItems.getOrderItemById(orderKey, productKey, retrieveAssociation);
    }



    /**
     * Get all OrderItems for a specific salesOrder from salesOrderId
     *
     * @param orderKey the id of a salesOrder for which all OrderItems are to be returned
     * @param retrieveAssociation set to true if you want all orderItems for salesOrder to be returned
     * @return ArrayList of OrderItems for a salesOrderObject with salesOrderId
     */
    public ArrayList<OrderItems> getAllOrderItemsForSalesOrder(long orderKey, boolean retrieveAssociation) throws Exception
    {
        return _dbOrderItems.getAllOrderItemsForSalesOrder(orderKey, retrieveAssociation);
    }


    /**
     * Insert a new OrderItem into the database
     *
     * @param orderItem orderItem object with the data to be added
     * @return int returns the number of rows affected
     * @throws Exception
     */
    public int insertOrderItem(OrderItems orderItem, long orderKey, long productKey) throws Exception
    {
        return _dbOrderItems.insertOrderItem(orderItem, orderKey, productKey);
    }


    /**
     * Update an OrderItem that already exists in the database
     *
     * @param orderItem the orderItem object that contain a valid id and the new data that should be updated
     * @return int returns the number of rows affected
     */
    public int updateOrderItem(OrderItems orderItem, long orderKey, long productKey) throws Exception
    {
        return _dbOrderItems.updateOrderItem(orderItem, orderKey, productKey);
    }
}
