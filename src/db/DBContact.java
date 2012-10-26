package db;

import models.Contact;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBContact implements IFDBContact
{
	
	private DataAccess _da;
	public DBContact()
	{
		_da = DataAccess.getInstance();
	}
	
	/**
	 * Retrieve all contacts from database
	 * 
	 * @return ArrayList<Contact>
	 */
	public ArrayList<Contact> getAllContacts() throws Exception
	{
		
		ArrayList<Contact> returnList = new ArrayList<Contact>();

			PreparedStatement query = _da.getCon().prepareStatement("SELECT * FROM Contacts");
			_da.setSqlCommandText(query);
			ResultSet contacts = _da.callCommandGetResultSet();
			
			while(contacts.next())
			{
				Contact contact = buildContact(contacts);
				returnList.add(contact);
			}
			return returnList;
	}
	
	/**
	 * Retrieve a specific contact by contact id
	 * 
	 * @param id 			the ID of the contact that needs to be returned
	 * @return Contact
	 */
	public Contact getContactById(long id) throws Exception
	{

			PreparedStatement query = _da.getCon().prepareStatement("SELECT * FROM Contacts WHERE phoneNo = ?");
			query.setLong(1, id);
			_da.setSqlCommandText(query);
			ResultSet contactResult = _da.callCommandGetRow();
            contactResult.next();
			return buildContact(contactResult);

	}
	
	/**
	 * Insert data in to database
	 */
	public int insertContact(Contact contact) throws Exception
	{
		if(contact == null)
			return 0;
		
		try
		{
			PreparedStatement query = _da.getCon().prepareStatement("INSERT INTO Contacts (phoneNo, name, address, zipCode, city, email, country) " +
												 "VALUES (?, ?, ?, ?, ?, ?, ?)");
			
			query.setLong(1, contact.getPhoneNo());
			query.setString(2, contact.getName());
			query.setString(3, contact.getAddress());
			query.setInt(4, contact.getZipCode());
			query.setString(5, contact.getCity());
			query.setString(6, contact.getEmail());
			query.setString(7, contact.getCountry());
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return 0;
	}

	public int updateContact(Contact contact) throws Exception
	{
		return 0;
	}
	
	private Contact buildContact(ResultSet row) throws Exception
	{
		if(row == null)
			return null;

			String name = row.getString("name");
			String address = row.getString("address");
			int zipCode = row.getInt("zipCode");
			String city = row.getString("city");
			long phoneNo = row.getLong("phoneNo");
			String email = row.getString("email");
			String country = row.getString("country");
			
			Contact contact = new Contact(name, address, zipCode, city, phoneNo, email, country);
			
			return contact;

	}
}
