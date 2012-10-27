package db;

import java.math.BigDecimal;

import models.Contact;
import models.DeliveryStatus;
import models.Invoice;
import models.OrderItems;
import models.SalesOrder;
import models.Product;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;

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
	public ArrayList<OrderItems> getAllOrderItemsDatabase(boolean retrieveAssociation) throws Exception 
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
		return null;
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
	public OrderItems getOrderItemFromId(long orderKey, long productKey, boolean retrieveAssociation) throws Exception
	{
	    PreparedStatement query = _da.getCon().prepareStatement("SELECT * FROM OrderItems WHERE orderKey = ? AND productKey = ?");
	    query.setLong(1, orderKey); 
	    query.setLong(2, productKey);
	    _da.setSqlCommandText(query);
	    ResultSet orderItemResult = _da.callCommandGetRow();
	    if(orderItemResult.next())
	        return buildOrderItem(orderItemResult, true);

	    return null;
	}

	
	
	/**
	 * Get all OrderItems for a specific salesOrder from salesOrderId
	 * 
	 * @param id the id of a salesOrder for which all OrderItems are to be returned
	 * @param retrieveAssociation set to true if you want all orderItems for salesOrder to be returned
	 * @return ArrayList of OrderItems for a salesOrderObject with salesOrderId
	 */
	@Override
	public ArrayList<SalesOrder> getAllOrderItemsForSalesOrder(long id, boolean retrieveAssociation) throws Exception 
	{
		return null;
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
		
		PreparedStatement query = _da.getCon().prepareStatement("INSERT INTO OrderItems (orderKey, productKey, quantity, unitPrice) " + "VALUES (?, ?, ?, ?)");
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
	 * @param OrderItem the orderItem object that contain a valid id and the new data that should be updated
	 * @return int returns the number of rows affected
	 */
	@Override
	public int updateOrderItem(OrderItems orderItem, long orderKey, long productKey) throws Exception 
	{
		if(orderItem == null)
			return 0;
		
		PreparedStatement query = _da.getCon().prepareStatement("UPDATE OrderItems SET orderKey = ?, productKey = ?, quantity = ?, unitPrice = ?" + "WHERE orderId = ?");
		query.setLong(1, orderKey);
		query.setLong(2, productKey);
		query.setLong(3, orderItem.getQuantity());
		query.setBigDecimal(4, orderItem.getUnitPrice());
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
	
	
	private Product buildProduct(ResultSet row) throws Exception
	{
		if(row == null)
			return null;
		
		long productId = row.getLong("productKey");
		long contactId = row.getLong("contactsKey");
		long categoryId = row.getLong("categoryKey");
		String name = row.getString("name");
		String purchasePrice = row.getString("purchasePrice");
		String salesPrice = row.getString("salesPrice");
		String rentPrice = row.getString("rentPrice");
		String originCountry = row.getString("countryOfOrigin");
		long minimumStock = row.getLong("minimumStock");
		return new Product(productId, name, purchasePrice, salesPrice, rentPrice, originCountry, minimumStock);
	}
}