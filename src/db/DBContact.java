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
	@Override
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
	@Override
	public Contact getContactById(long id) throws Exception
	{
        PreparedStatement query = _da.getCon().prepareStatement("SELECT * FROM Contacts WHERE phoneNo = ?");
        query.setLong(1, id);
        _da.setSqlCommandText(query);
        ResultSet contactResult = _da.callCommandGetRow();
        if(contactResult.next())
            return buildContact(contactResult);

        return null;
	}
	
	/**
	 * Insert contact data in to database
	 * 
	 * @param contact		the object that contains the data you want to add
	 * @return int			returns the number of rows affected
	 */
	@Override
	public int insertContact(Contact contact) throws Exception
	{
		if(contact == null)
			return 0;

        PreparedStatement query = _da.getCon().prepareStatement("INSERT INTO Contacts (phoneNo, name, address, zipCode, city, email, country) " +
                                                                "VALUES (?, ?, ?, ?, ?, ?, ?)");

        query.setLong(1, contact.getPhoneNo());
        query.setString(2, contact.getName());
        query.setString(3, contact.getAddress());
        query.setLong(4, contact.getZipCode());
        query.setString(5, contact.getCity());
        query.setString(6, contact.getEmail());
        query.setString(7, contact.getCountry());
        _da.setSqlCommandText(query);

        return _da.callCommand();
	}
	
	/**
	 * Update a existing contact data in the database
	 * 
	 * @param contact		the object containing the data you want to update
	 * @return int			returns the number of rows affected
	 */
	@Override
	public int updateContact(Contact contact) throws Exception
	{
		if(contact == null)
			return 0;
		
		if(getContactById(contact.getPhoneNo()) == null)
			return 0;
		
		PreparedStatement query = _da.getCon().prepareStatement("UPDATE Contacts SET phoneNo = ?, name = ?, address = ?, zipCode = ?, city = ?, country = ? " +
																"WHERE phoneNo = ?");
		
		query.setLong(1, contact.getPhoneNo());
		query.setString(2, contact.getName());
		query.setString(3, contact.getAddress());
		query.setLong(4, contact.getZipCode());
		query.setString(5, contact.getCity());
		query.setString(6, contact.getEmail());
		query.setString(7, contact.getCountry());
		_da.setSqlCommandText(query);

		return _da.callCommand();
	}

    /**
     * Delete an existing contact from the database
     *
     * @param contact 		the object containing the contact which should be deleted from the database
     * @return int 			returns the number of rows affected
     */
    public int deleteContact(Contact contact) throws Exception
    {
        if(contact == null)
            return 0;

        PreparedStatement query = _da.getCon().prepareStatement("DELETE FROM Contacts WHERE phoneNo = ?");
        query.setLong(1, contact.getPhoneNo());
        _da.setSqlCommandText(query);

        return _da.callCommand();
    }

    /**
     * Delete an existing contact from the database
     *
     * @param phoneNo 		the phoneNo of the contact which should be deleted from the database
     * @return int 			returns the number of rows affected
     */
    public int deleteContact(long phoneNo) throws Exception
    {
        PreparedStatement query = _da.getCon().prepareStatement("DELETE FROM Contacts WHERE phoneNo = ?");
        query.setLong(1, phoneNo);
        _da.setSqlCommandText(query);

        return _da.callCommand();
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

        return new Contact(name, address, zipCode, city, phoneNo, email, country);
	}
}
