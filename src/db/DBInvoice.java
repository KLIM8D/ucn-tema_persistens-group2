package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import models.Invoice;

public class DBInvoice implements IFDBInvoice
{

	private DataAccess _da;
	public DBInvoice()
	{
		_da = DataAccess.getInstance();
	}

    /**
     * Retrieve all invoices from database
     *
     * @return ArrayList<Invoice>
     */
	public ArrayList<Invoice> getAllInvoices() throws Exception
    {
		ArrayList<Invoice> returnList = new ArrayList<Invoice>();

        PreparedStatement query = _da.getCon().prepareStatement("SELECT * FROM Invoice");
        _da.setSqlCommandText(query);
        ResultSet invoice = _da.callCommandGetResultSet();

        while(invoice.next())
        {
            Invoice inv = buildInvoice(invoice);
            returnList.add(inv);
        }

		return returnList;
	}

    /**
     * Get specific invoice by id
     *
     * @param id					the id of the invoice you need returned
     * @return Invoice
     */
	public Invoice getInvoiceById(int id) throws Exception
    {
        PreparedStatement query = _da.getCon().prepareStatement("SELECT * FROM Invoice WHERE invoiceNo = ?");
        query.setLong(1, id);
        _da.setSqlCommandText(query);
        ResultSet invoiceResult = _da.callCommandGetRow();
        if(invoiceResult.next())
            return buildInvoice(invoiceResult);

        return null;
	}

    /**
     * Inserts a new invoice in the database
     *
     * @param invoice				the object containing the information you want stored
     * @return						returns the number of rows affected
     */
	public int insertInvoice(Invoice invoice) throws Exception
    {
		if(invoice == null)
	        return 0;

        PreparedStatement query = _da.getCon().prepareStatement("INSERT INTO Invoice (orderKey, invoiceNo, paymentDay) VALUES (?, ?, ?)");
        query.setLong(1, invoice.getOrderKey());
        query.setInt(2, invoice.getInvoiceNo());
        query.setDate(3, (java.sql.Date) invoice.getPaymentDate());
        _da.setSqlCommandText(query);

        return _da.callCommand();
	}

    /**
     * Update a existing invoice in database
     *
     * @param invoice 				the object containing the updated information you want stored
     * @return						returns the number of rows affected
     */
	public int updateInvoice(Invoice invoice) throws Exception
    {
		if(invoice == null)
            return 0;

        PreparedStatement query = _da.getCon().prepareStatement("UPDATE Invoice SET invoiceNo = ?, paymentDay = ? WHERE orderKey = ?");
        query.setInt(1, invoice.getInvoiceNo());
        query.setDate(2, (java.sql.Date) invoice.getPaymentDate());
        query.setLong(3, invoice.getOrderKey());
        _da.setSqlCommandText(query);

        return _da.callCommand();
	}

	private Invoice buildInvoice(ResultSet row) throws Exception
	{
		if(row == null)
			return null;

        long orderKey = row.getLong("orderKey");
        int invoiceNo = row.getInt("invoiceNo");
        Date paymentDate = row.getDate("paymentDate");

        return new Invoice(orderKey, invoiceNo, paymentDate);
	}
}
