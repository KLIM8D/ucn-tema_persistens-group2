package models;

public class SalesOrder {
	
	// instance variables
	private String _orderDate;
	private String _deliveryDate;
	private int _deliveryStatus;
	
	// constructor
	public SalesOrder(String orderDate, String deliveryDate, int deliveryStatus)
	{
		_orderDate = orderDate;
		_deliveryDate = deliveryDate;
		_deliveryStatus = deliveryStatus;
	}

	// methods getters and setters
	public String get_orderDate() 
	{ return _orderDate; }

	public void set_orderDate(String orderDate) 
	{ this._orderDate = orderDate; }

	public String get_deliveryDate() 
	{ return _deliveryDate; }

	public void set_deliveryDate(String deliveryDate) 
	{ this._deliveryDate = deliveryDate; }

	public int get_deliveryStatus() 
	{ return _deliveryStatus; }

	public void set_deliveryStatus(int deliveryStatus) 
	{ this._deliveryStatus = deliveryStatus; }

}
