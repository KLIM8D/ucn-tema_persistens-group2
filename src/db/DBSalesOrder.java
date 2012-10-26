package db;

import models.SalesOrder;
import models.DeliveryStatus;
import models.Contact;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBSalesOrder implements IFDBSalesOrder
{
	private DataAccess _da;
	public DBSalesOrder()
	{
		_da = DataAccess.getInstance();
	}
	
	/**
     * Get all the salesOrders from the database
     *
     * @param retrieveAssociation set to true if you want salesOrders
     * @return ArrayList<SalesOrder>
     */
	@Override
	public ArrayList<SalesOrder> getAllSalesOrders(boolean retrieveAssociation) throws Exception
	{
		// TODO Auto-generated method stub
		ArrayList<SalesOrder> returnList = new ArrayList<SalesOrder>();
		
		PreparedStatement query = _da.getCon().prepareStatement("SELECT * FROM SalesOrders");
		_da.setSqlCommandText(query);
		ResultSet salesOrders = _da.callCommandGetResultSet();
			
		while (salesOrders.next())
		{
			SalesOrder salesOrder = buildSalesOrder(salesOrders, retrieveAssociation);      // buildSalesOrder not written 
			returnList.add(salesOrder);
		}
		return returnList;
	}
	
	
	/**
     * Get a specific salesOrder by salesOrder id
     *
     * @param id the ID of the salesOrder you want returned
     * @param retrieveAssociation set to true if you want salesOrder
     * @return salesOrder
     */
	@Override
	public SalesOrder getSalesOrderFromId(long id, boolean retrieveAssociation) throws Exception
	{	
		PreparedStatement query = _da.getCon().prepareStatement("SELECT FROM * SalesOrder WHERE id = ?");
		query.setLong(1, id);
		_da.setSqlCommandText(query);
		ResultSet salesOrderResult = _da.callCommandGetRow();
		return buildSalesOrder(salesOrderResult, true);					
	}
	
	
	/**
	 * Get all SalesOrders for a specific customer from customerName
	 * 
	 * @param customerName the name of a customer for whom all SalesOrders are to be returned
	 * @param retrieveAssociation set to true if you want SalesOrders for customer to be returned
	 * @return ArrayList of SalesOrders for a customer with customerName
	 */
	public ArrayList<SalesOrder> getAllSalesOrdersFromCustomer(String customerName, boolean retrieveAssociation)
	{
		return null;
	}
	
	
	/**
     * Insert a new salesOrder to the database
     *
     * @param salesOrder the salesOrder object with the data you want added
     * @return int returns the number of rows affected
     */
	@Override
	public int insertSalesOrder(SalesOrder salesOrder) throws Exception 
	{
		if(salesOrder == null)
			return 0;
		
		PreparedStatement query = _da.getCon().prepareStatement("INSERT INTO SalesOrder (contactsKey, deliveryKey, orderDate, deliveryDate) " + "VALUES (?, ?, ?, ?)");
		query.setLong(1, salesOrder.getContact().getPhoneNo());
		query.setLong(2, salesOrder.getDeliveryStatus().getDeliveryId());
		query.setString(3, salesOrder.getOrderDate());
		query.setString(4, salesOrder.getDeliveryDate());
		_da.setSqlCommandText(query);
		return _da.callCommand();
	}
	
	
	/**
     * Update a salesOrder, that already exists in the database
     *
     * @param salesOrder the salesOrder object which contains a valid ID and the new data which should be updated
     * @return int returns the number of rows affected
     */
	@Override
	public int updateSalesOrder(SalesOrder salesOrder) throws Exception
	{
		if(salesOrder == null)
			return 0;
		
		
		if(getSalesOrderFromId(salesOrder.getOrderId(), true) == null)
			return 0;
			
		PreparedStatement query = _da.getCon().prepareStatement("UPDATE SalesOrder SET contactsKey = ?, deliveryKey = ?, orderDate = ?, deliveryDate = ? " + "WHERE orderId = ?");
		query.setLong(1, salesOrder.getContact().getPhoneNo());
		query.setLong(2, salesOrder.getDeliveryStatus().getDeliveryId());
		query.setString(3, salesOrder.getOrderDate());
		query.setString(4, salesOrder.getDeliveryDate());
		_da.setSqlCommandText(query);
		return _da.callCommand();
	}
	
	
	private SalesOrder buildSalesOrder(ResultSet row, boolean retrieveAssociation) throws Exception
	{
		if(row == null)
			return null;
		
		long orderId = row.getLong("orderId");
		String orderDate = row.getString("orderDate");
		String deliveryDate = row.getString("deliveryDate");
		SalesOrder salesOrder = new SalesOrder(orderId, orderDate, deliveryDate);
		
		if(retrieveAssociation)
		{
			// DeliveryStatus
			long deliveryId = row.getLong("deliveryKey");
			PreparedStatement queryDelivery = _da.getCon().prepareStatement("SELECT * FROM DeliveryStatus WHERE deliveryId = ?");
			queryDelivery.setLong(1, deliveryId);
			_da.setSqlCommandText(queryDelivery);
			ResultSet deliveryResults = _da.callCommandGetRow();
			DeliveryStatus status = buildDeliveryStatus(deliveryResults);
			salesOrder.setDeliveryStatus(status);
			
			// Contact
			long contactsKey = row.getLong("contactsKey");
			DBContact dbContact = new DBContact(); 				
			Contact contact = dbContact.getContactById(contactsKey);
			salesOrder.setContact(contact);
		}
		return salesOrder;	
	}
	
	
	private DeliveryStatus buildDeliveryStatus(ResultSet row) throws Exception
	{
		if(row == null)
			return null;
		
		long deliveryId = row.getLong("deliveryId");
		String deliveryState = row.getString("deliveryState");
		return new DeliveryStatus(deliveryId, deliveryState);
	}
	
	public Contact buildContact(ResultSet row) throws Exception
	{
		if(row == null)
			return null;
		
		String name = row.getString("name");
		String address = row.getString("address");
		long zipCode = row.getLong("zipCode");
		String city = row.getString("city");
		long phoneNo = row.getLong("phoneNo");
		String email = row.getString("email");
		String country = row.getString("country");
		return new Contact(name, address, zipCode, city, phoneNo, email, country);
	}
}
