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
	 * @return ArrayList<Customer>
	 */
    @Override
	public ArrayList<Customer> getAllCustomers() throws Exception
	{
		ArrayList<Customer> returnList = new ArrayList<Customer>();

        PreparedStatement query = _da.getCon().prepareStatement("SELECT * FROM Customer");
        _da.setSqlCommandText(query);
        ResultSet customers = _da.callCommandGetResultSet();

        while(customers.next())
        {
            Customer customer = buildCustomer(customers);
            returnList.add(customer);
        }

		return returnList;
	}
	
	/**
	 *  Retrieve specific customer record by id
	 *  
	 *  @param id					the id of the record you wish to return
	 *  @return Customer
	 */
    @Override
	public Customer getCustomerById(long id) throws Exception
	{
        PreparedStatement query = _da.getCon().prepareStatement("SELECT * FROM Customer WHERE contactsKey = ?");
        query.setLong(1, id);
        _da.setSqlCommandText(query);
        ResultSet customerResult = _da.callCommandGetRow();
        if(customerResult.next())
            return buildCustomer(customerResult);

        return null;
	}
	
	/**
	 * Retrieve specific customer record by name
	 * 
	 * @param name					the name of the record you wish to return
	 * @return Customer
	 */
    @Override
	public Customer getCustomerByName(String name) throws Exception
	{
        PreparedStatement query = _da.getCon().prepareStatement("SELECT * FROM Customer, Contacts WHERE name = ?");
        query.setString(1, name);
        _da.setSqlCommandText(query);
        ResultSet customerResult = _da.callCommandGetRow();
        if(customerResult.next())
            return buildCustomer(customerResult);

        return null;
	}

    /**
     * Inserts a new customer in the database
     *
     * @param customer				the object containing the information you want stored
     * @return						returns the number of rows affected
     */
    @Override
	public int insertCustomer(Customer customer) throws Exception
	{
        if(customer == null)
            return 0;

        PreparedStatement query = _da.getCon().prepareStatement("INSERT INTO Customer (contactsKey, discount, isBusiness) VALUES (?, ?, ?)");

        query.setLong(1, customer.getPhoneNo());
        query.setBigDecimal(2, customer.getDiscount());
        query.setBoolean(3, customer.getIsBusiness());
        _da.setSqlCommandText(query);

        return _da.callCommand();
	}

    /**
     * Update a existing contact in database
     *
     * @param customer 				the object containing the updated information you want stored
     * @return						returns the number of rows affected
     */
    @Override
	public int updateCustomer(Customer customer) throws Exception
	{
		if(customer == null)
            return 0;

        PreparedStatement query = _da.getCon().prepareStatement("UPDATE Customer SET discount = ?, isBusiness = ? WHERE contactsKey = ?");
        query.setBigDecimal(1, customer.getDiscount());
        query.setBoolean(2, customer.getIsBusiness());
        query.setLong(3, customer.getPhoneNo());
        _da.setSqlCommandText(query);

        return _da.callCommand();
	}

    /**
     * Delete an existing customer from the database
     *
     * @param customer 		the object containing the customer which should be deleted from the database
     * @return int 			returns the number of rows affected
     */
    @Override
    public int deleteCustomer(Customer customer) throws Exception
    {
        if(customer == null)
            return 0;
        
        if(getCustomerById(customer.getPhoneNo()) == null)
            return 0;

        int rowsAffected = 0;
        PreparedStatement query = _da.getCon().prepareStatement("DELETE FROM Customer WHERE contactsKey = ?");
        query.setLong(1, customer.getPhoneNo());
        _da.setSqlCommandText(query);
        rowsAffected += _da.callCommand();

        DBContact DBCo = new DBContact();
        rowsAffected += DBCo.deleteContact(customer.getPhoneNo());

        return rowsAffected;
    }

	private Customer buildCustomer(ResultSet row) throws Exception
	{
		if(row == null)
			return null;
		
        long contactsKey = row.getLong("contactsKey");
        BigDecimal discount = row.getBigDecimal("discount");
        boolean isBusiness = row.getBoolean("isBusiness");
        DBContact dbc = new DBContact();
        Contact contact = dbc.getContactById(contactsKey);

        return new Customer(contact.getName(), contact.getAddress(), contact.getZipCode(), contact.getCity(), contact.getPhoneNo(), contact.getEmail(), contact.getCountry(), discount, isBusiness);
	}
}
