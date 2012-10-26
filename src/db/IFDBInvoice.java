package db;

import java.util.ArrayList;

import models.Invoice;

	public interface IFDBInvoice {
		
		/**
		 * Retrieve all invoices from database
		 *
		 * @return ArrayList<Invoice>
		 */
		public ArrayList<Invoice> getAllInvoices() throws Exception;
		
		/**
		 * Get specific invoice by id
		 * 
		 * @param id					the id of the invoice you need returned
		 * @return Invoice
		 */
		public Invoice getInvoiceById(int id) throws Exception;
		
		/**
		 * Inserts a new invoice in the database
		 * 
		 * @param invoice				the object containing the information you want stored
		 * @return						returns the number of rows affected
		 */
		public int insertInvoice(Invoice invoice) throws Exception;
		
		/**
		 * Update a existing contact in database
		 * 
		 * @param invoice 				the object containing the updated information you want stored
		 * @return						returns the number of rows affected
		 */
		public int updateInvoice(Invoice invoice) throws Exception;
}
