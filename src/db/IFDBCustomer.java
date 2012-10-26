package db;

import models.Customer;

import java.util.ArrayList;

public interface IFDBCustomer {
	
	/**
	 * Retrieve all customers from database
	 * 
	 * @param retrieveAssociation 	set to true if customer data is wanted
	 * @return ArrayList<Customer>
	 */
	public ArrayList<Customer> getAllCustomers(boolean retrieveAssociation) throws Exception;
	
	/**
	 * Get specific customer by id
	 * 
	 * @param id					the id of the customer you need returned
	 * @param retrieveAssociation	set to true if customer data is wanted
	 * @return Customer
	 */
	public Customer getCustomerById(long id, boolean retrieveAssociation) throws Exception;
	
	/**
	 * Get specific customer by contact name
	 * 
	 * @param name					the name of the customer you need returned
	 * @param retrieveAssociation	set to true if customer data is wanted
	 * @return Customer
	 */
	public Customer getCustomerByName(String name, boolean retrieveAssociation) throws Exception;
	
	/**
	 * Inserts a new customer in the database
	 * 
	 * @param customer				the object containing the information you want stored
	 * @return						returns the number of rows affected
	 */
	public int insertCustomer(Customer customer) throws Exception;
	
	/**
	 * Update a existing contact in database
	 * 
	 * @param customer 				the object containing the updated information you want stored
	 * @return						returns the number of rows affected
	 */
	public int updateCustomer(Customer customer) throws Exception;

}
