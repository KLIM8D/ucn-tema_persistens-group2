package db;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import models.Contact;
import models.Customer;
import models.Invoice;

public class DBInvoice implements IFDBInvoice{

	private DataAccess _da;
	public DBInvoice()
	{
		_da = DataAccess.getInstance();
	}
	
	public ArrayList<Invoice> getAllInvoices() throws Exception {
		
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

	public Invoice getInvoiceById(int id) throws Exception {
        PreparedStatement query = _da.getCon().prepareStatement("SELECT * FROM Invoice WHERE invoiceNo = ?");
        query.setLong(1, id);
        _da.setSqlCommandText(query);
        ResultSet invoiceResult = _da.callCommandGetRow();
        invoiceResult.next();

        return buildInvoice(invoiceResult);
	}

	public int insertInvoice(Invoice invoice) throws Exception {
		 if(invoice == null)
	            return 0;

	        PreparedStatement query = _da.getCon().prepareStatement("INSERT INTO Invoice (invoiceNo, paymentDay) VALUES (?, ?)");

	        query.setInt(1, invoice.getInvoiceNo());
	        query.setDate(2, (java.sql.Date) invoice.getPaymentDate());
	        _da.setSqlCommandText(query);

	        return _da.callCommand();
	}

	public int updateInvoice(Invoice invoice) throws Exception {
		if(invoice == null)
            return 0;

        PreparedStatement query = _da.getCon().prepareStatement("UPDATE Invoice SET invoiceNo = ?, paymentDay = ? WHERE orderKey = ?");
        query.setInt(1, invoice.getInvoiceNo());
        query.setDate(2, (java.sql.Date) invoice.getPaymentDate());
        _da.setSqlCommandText(query);

        return _da.callCommand();
	}

	private Invoice buildInvoice(ResultSet row) throws Exception
	{
		if(row == null)
			return null;
		
        int invoiceNo = row.getInt("invoiceNo");
        Date paymentDate = row.getDate("paymentDate");

        return new Invoice(invoiceNo, paymentDate);
	}
	
}
