package db;

import models.Contact;
import models.Customer;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBCustomer implements IFDBCustomer
{

	private DataAccess _da;
	public DBCustomer()
	{
		_da = DataAccess.getInstance();
	}
	
	/**
	 * Retrieve all customers records from database
	 * 
	 * @param retrieveAssociation	set to true if customer data is wanted
	 * @return ArrayList<Customer>
	 */
	public ArrayList<Customer> getAllCustomers(boolean retrieveAssociation) throws Exception
	{
		ArrayList<Customer> returnList = new ArrayList<Customer>();

			PreparedStatement query = _da.getCon().prepareStatement("");
			_da.setSqlCommandText(query);
			ResultSet customers = _da.callCommandGetResultSet();
			
			while(customers.next())
			{
				Customer customer = buildCustomer(customers, retrieveAssociation);
				returnList.add(customer);
			}

		return returnList;
	}
	
	/**
	 *  Retrieve specific customer record by id
	 *  
	 *  @param id					the id of the record you wish to return
	 *  @param retrieveAssociation	set to true if customer data is wanted
	 *  @return Customer
	 */
	public Customer getCustomerById(long id, boolean retrieveAssociation) throws Exception
	{
			PreparedStatement query = _da.getCon().prepareStatement("");
			query.setLong(1, id);
			_da.setSqlCommandText(query);
			ResultSet customerResult = _da.callCommandGetRow();
            customerResult.next();
			return buildCustomer(customerResult, true);

	}
	
	/**
	 * Retrieve specific customer record by name
	 * 
	 * @param name					the name of the record you wish to return
	 * @param retrieveAssociation	set to true if customer data is wanted
	 * @return Customer
	 */
	public Customer getCustomerByName(String name, boolean retrieveAssociation) throws Exception
	{
		
			PreparedStatement query = _da.getCon().prepareStatement("");
			query.setString(1, name);
			_da.setSqlCommandText(query);
			ResultSet customerResult = _da.callCommandGetRow();
            customerResult.next();
			return buildCustomer(customerResult, true);
		
	}
	
	public int insertCustomer(Customer customer) throws Exception
	{
		return 0;
	}
	
	public int updateCustomer(Customer customer) throws Exception
	{
		return 0;
	}
	
	private Customer buildCustomer(ResultSet row, boolean retrieveAssociation) throws Exception
	{
		if(row == null)
			return null;
		
			long contactsKey = row.getLong("contactsKey");
			BigDecimal discount = row.getBigDecimal("discount");
			boolean isBusiness = row.getBoolean("isBusiness");
			DBContact dbc = new DBContact();
			Contact contact = dbc.getContactById(contactsKey);
			
			Customer customer = new Customer(contact.getName(), contact.getAddress(), contact.getZipCode(), contact.getCity(), contact.getPhoneNo(), contact.getEmail(), contact.getCountry(), discount, isBusiness);
			
			return customer;

	}
}