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
	public ArrayList<Contact> getAllContacts()
	{
		
		ArrayList<Contact> returnList = new ArrayList<Contact>();
		try 
		{
			PreparedStatement query = _da.getCon().prepareStatement("SELECT * FROM Contacts");
			_da.setSqlCommandText(query);
			ResultSet contacts = _da.callCommandGetResultSet();
			
			while(contacts.next())
			{
				Contact contact = buildContact(contacts);
				returnList.add(contact);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return returnList;
	}
	
	/**
	 * Retrieve a specific contact by contact id
	 * 
	 * @param id 			the ID of the contact that needs to be returned
	 * @return Contact
	 */
	public Contact getContactById(long id)
	{
		try
		{
			PreparedStatement query = _da.getCon().prepareStatement("SELECT * FROM Contacts WHERE phoneNo = ?");
			query.setLong(1, id);
			_da.setSqlCommandText(query);
			ResultSet contactResult = _da.callCommandGetRow();
			return buildContact(contactResult);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public int insertContact(Contact contact) throws Exception
	{
		return 0;
	}

	public int updateContact(Contact contact) throws Exception
	{
		return 0;
	}
	
	private Contact buildContact(ResultSet row)
	{
		if(row == null)
			return null;
		
		try
		{
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
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
	}
}