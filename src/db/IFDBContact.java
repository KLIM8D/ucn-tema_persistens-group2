package db;

import models.Contact;

import java.util.ArrayList;

public interface IFDBContact {
	
	/**
	 * Retrieve all contacts from database
	 * 
	 * @return ArrayList<Contact>
	 */
	public ArrayList<Contact> getAllContacts();
	
	/**
	 * Inserts a new contact in database
	 * 
	 * @param contact the object containing the information you want stored
	 * @return int returns the number of rows affected
	 */
	public int insertContact(Contact contact) throws Exception;
	
	/**
	 * Update a existing contact in database
	 * 
	 * @param contact the object containing the updated information you want stored
	 * @return int returns the number of rows affected
	 */
	public int updateContact(Contact contact) throws Exception;
}