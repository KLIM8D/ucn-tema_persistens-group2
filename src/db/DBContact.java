package db;

import models.Contact;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBContact implements IFDBContact {
	
	private DataAccess _da;
	public DBContact()
	{
		_da = DataAccess.getInstance();
	}

	@Override
	public ArrayList<Contact> getAllContacts() {
		
		ArrayList<Contact> returnList = new ArrayList<Contact>();
		try {
			PreparedStatement query = _da.getCon().prepareStatement("");
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

	@Override
	public int insertContact(Contact contact) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateContact(Contact contact) throws Exception {
		// TODO Auto-generated method stub
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
