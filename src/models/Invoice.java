package models;

import java.util.Date;

public class Invoice
{
    private long _orderKey;
	private int _invoiceNo;
	private Date _paymentDate;
	
	public Invoice(long orderKey, int invoiceNo, Date paymentDate)
	{
        _orderKey = orderKey;
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

    public long getOrderKey()
    { return _orderKey; }
    public void setOrderKey(long value)
    { _orderKey = value; }
}