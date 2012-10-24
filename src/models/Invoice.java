package models;

import java.util.Date;

public class Invoice {
	private int _invoiceNo;
	private Date _paymentDate;
	
	public Invoice(int invoiceNo, Date paymentDate)
	{
		_invoiceNo = invoiceNo;
		_paymentDate = paymentDate;
	}
	
	public int getInvoiceNo()
	{ return _invoiceNo; }
	public void setInvoiceNo(int value)
	{ _invoiceNo = value; }
	
	public Date getPaymentDate()
	{ return _paymentDate; }
	public void setPaymentDate(Date value)
	{ _paymentDate = value; }
}