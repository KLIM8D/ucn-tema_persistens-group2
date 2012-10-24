package models;

public class SalesOrder 
{
	
	private String _orderDate;
	private String _deliveryDate;
	private int _deliveryStatus;
	
	public SalesOrder(String orderDate, String deliveryDate, int deliveryStatus)
	{
		_orderDate = orderDate;
		_deliveryDate = deliveryDate;
		_deliveryStatus = deliveryStatus;
	}

	public String getOrderDate() 
	{ return _orderDate; }	
	public void setOrderDate(String orderDate) 
	{ _orderDate = orderDate; }

	public String getDeliveryDate() 
	{ return _deliveryDate; }
	public void setDeliveryDate(String deliveryDate) 
	{ _deliveryDate = deliveryDate; }

	public int getDeliveryStatus() 
	{ return _deliveryStatus; }
	public void setDeliveryStatus(int deliveryStatus) 
	{ _deliveryStatus = deliveryStatus; }

}
