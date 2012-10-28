package db;

import java.math.BigDecimal;

import models.OrderItems;
import models.Product;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class DBOrderItems implements IFDBOrderItems
{
	private DataAccess _da;
	public DBOrderItems()
	{
		_da = DataAccess.getInstance();
	}
	
	
	/**
	 * Get all OrderItems in the Database
	 *
	 * @param retrieveAssociation set to true if all OrderItems are to be returned
	 * @return ArrayList of all OrderItem objects in Database
	 */
	@Override
	public ArrayList<OrderItems> getAllOrderItems(boolean retrieveAssociation) throws Exception
	{
		ArrayList<OrderItems> returnList = new ArrayList<OrderItems>();
		
		PreparedStatement query = _da.getCon().prepareStatement("SELECT * FROM OrderItems");
		_da.setSqlCommandText(query);
		ResultSet orderItems = _da.callCommandGetResultSet();
		
		while(orderItems.next())
		{
			OrderItems orderItem = buildOrderItem(orderItems, retrieveAssociation);
			returnList.add(orderItem);
		}

		return returnList;
	}
	
	
	/**
	 * Get a specific OrderItem by orderKey and productKey
	 * 
	 * @param orderKey the Id of the Order
	 * @param productKey the Id of the Product
	 * @param retrieveAssociation set to true if OrderItem with id is to be returned
	 * @return OrderItem object with orderKey and productKey
	 */
	@Override
	public OrderItems getOrderItemById(long orderKey, long productKey, boolean retrieveAssociation) throws Exception
	{
	    PreparedStatement query = _da.getCon().prepareStatement("SELECT * FROM OrderItems WHERE orderKey = ? AND productKey = ?");
	    query.setLong(1, orderKey); 
	    query.setLong(2, productKey);
	    _da.setSqlCommandText(query);
	    ResultSet orderItemResult = _da.callCommandGetRow();
	    if(orderItemResult.next())
	        return buildOrderItem(orderItemResult, retrieveAssociation);

	    return null;
	}

	
	
	/**
	 * Get all OrderItems for a specific salesOrder from salesOrderId
	 * 
	 * @param orderKey the id of a salesOrder for which all OrderItems are to be returned
	 * @param retrieveAssociation set to true if you want all orderItems for salesOrder to be returned
	 * @return ArrayList of OrderItems for a salesOrderObject with salesOrderId
	 */
	@Override
	public ArrayList<OrderItems> getAllOrderItemsForSalesOrder(long orderKey, boolean retrieveAssociation) throws Exception
	{
        ArrayList<OrderItems> returnList = new ArrayList<OrderItems>();

        PreparedStatement query = _da.getCon().prepareStatement("SELECT * FROM OrderItems WHERE orderKey = ?");
        query.setLong(1, orderKey);
        _da.setSqlCommandText(query);
        ResultSet orderItems = _da.callCommandGetResultSet();

        while(orderItems.next())
        {
            OrderItems orderItem = buildOrderItem(orderItems, retrieveAssociation);
            returnList.add(orderItem);
        }

        return returnList;
	}
	
	
	/**
	 * Insert a new OrderItem into the database
	 * 
	 * @param orderItem orderItem object with the data to be added
	 * @return int returns the number of rows affected
	 * @throws Exception
	 */
	@Override
	public int insertOrderItem(OrderItems orderItem, long orderKey, long productKey) throws Exception 
	{
		if(orderItem == null)
			return 0;
		
		PreparedStatement query = _da.getCon().prepareStatement("INSERT INTO OrderItems (orderKey, productKey, quantity, unitPrice) VALUES (?, ?, ?, ?)");
		query.setLong(1, orderKey);
		query.setLong(2, productKey);
		query.setLong(3, orderItem.getQuantity());
		query.setBigDecimal(4, orderItem.getUnitPrice());
		_da.setSqlCommandText(query);

		return _da.callCommand();
	}
	
	
	/**
	 * Update an OrderItem that already exists in the database
	 * 
	 * @param orderItem the orderItem object that contain a valid id and the new data that should be updated
	 * @return int returns the number of rows affected
	 */
	@Override
	public int updateOrderItem(OrderItems orderItem, long orderKey, long productKey) throws Exception 
	{
		if(orderItem == null)
			return 0;

        if(getOrderItemById(orderKey, orderItem.getProduct().getId(), true) == null)
            return 0;
		
		PreparedStatement query = _da.getCon().prepareStatement("UPDATE OrderItems SET productKey = ?, quantity = ?, unitPrice = ? WHERE orderKey = ?");
		query.setLong(1, productKey);
		query.setLong(2, orderItem.getQuantity());
		query.setBigDecimal(3, orderItem.getUnitPrice());
        query.setLong(4, orderKey);
		_da.setSqlCommandText(query);

		return _da.callCommand();
	}
	
	public OrderItems buildOrderItem(ResultSet row, boolean retrieveAssociation) throws Exception
	{
		if(row == null)
			return null;
		
		long quantity = row.getLong("quantity");
		BigDecimal unitPrice = row.getBigDecimal("unitPrice");   
		OrderItems orderItem = new OrderItems(quantity, unitPrice);

		if(retrieveAssociation)
		{	
			long productId = row.getLong("productKey");
			DBProduct dbProduct = new DBProduct();
			Product product = dbProduct.getProductById(productId, true);
			orderItem.setProduct(product);	
		}
		
		return orderItem;	
	}
}
