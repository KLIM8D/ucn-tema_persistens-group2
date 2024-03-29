package db;

import java.util.ArrayList;

import models.OrderItems;

public interface IFDBOrderItems 
{
	/**
	 * Get all OrderItems in the Database
	 * 
	 * @param retrieveAssociation set to true if all OrderItems are to be returned
	 * @return ArrayList of all OrderItem objects in Database
	 */
	public ArrayList<OrderItems> getAllOrderItems(boolean retrieveAssociation) throws Exception;
	
	/**
	 * Get a specific OrderItem by OrderId
	 * 
	 * @param orderKey the Id of the OrderItem to be returned
     * @param productKey the Id of the Product
	 * @param retrieveAssociation set to true if OrderItem with id is to be returned
	 * @return OrderItem object with ID
	 */
	public OrderItems getOrderItemById(long orderKey, long productKey, boolean retrieveAssociation) throws Exception;
	
	/**
	 * Get all OrderItems for a specific salesOrder from salesOrderId
	 * 
	 * @param orderKey the id of a salesOrder for which all OrderItems are to be returned
	 * @param retrieveAssociation set to true if you want all orderItems for salesOrder to be returned
	 * @return ArrayList of OrderItems for a salesOrderObject with salesOrderId
	 */
	public ArrayList<OrderItems> getAllOrderItemsForSalesOrder(long orderKey, boolean retrieveAssociation) throws Exception;
	
	/**
	 * Insert a new OrderItem into the database
	 * 
	 * @param orderItem orderItem object with the data to be added
	 * @return int returns the number of rows affected
	 * @throws Exception
	 */
	public int insertOrderItem(OrderItems orderItem, long orderKey, long productKey) throws Exception;
	
	/**
	 * Update an OrderItem that already exists in the database
	 * 
	 * @param orderItem the orderItem object that contain a valid id and the new data that should be updated
	 * @return int returns the number of rows affected
	 */
	public int updateOrderItem(OrderItems orderItem, long orderKey, long productKey) throws Exception;
	
}
