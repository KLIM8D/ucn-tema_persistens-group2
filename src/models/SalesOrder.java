package models;

public class SalesOrder 
{
	private long _orderId;
	private String _orderDate;
	private String _deliveryDate;
	private DeliveryStatus _deliveryStatus;
	private Contact _contact;
	
	
	public SalesOrder(long orderId, String orderDate, String deliveryDate)
	{
		_orderId = orderId;
		_orderDate = orderDate;
		_deliveryDate = deliveryDate;
	}
	
	public SalesOrder(long orderId, String orderDate, String deliveryDate, DeliveryStatus deliveryStatus, Contact contact)
	{
		_orderId = orderId;
		_orderDate = orderDate;
		_deliveryDate = deliveryDate;
		_deliveryStatus = deliveryStatus;
		_contact = contact;
	}

	public long getOrderId()
	{ return _orderId; }
	public void setOrderId(long orderId)
	{ _orderId = orderId; }
	
	public String getOrderDate() 
	{ return _orderDate; }	
	public void setOrderDate(String orderDate) 
	{ _orderDate = orderDate; }

	public String getDeliveryDate() 
	{ return _deliveryDate; }
	public void setDeliveryDate(String deliveryDate) 
	{ _deliveryDate = deliveryDate; }

	public DeliveryStatus getDeliveryStatus() 
	{ return _deliveryStatus; }
	public void setDeliveryStatus(DeliveryStatus deliveryStatus) 
	{ _deliveryStatus = deliveryStatus; }
	
	public Contact getContact()
	{ return _contact; }
	public void setContact(Contact contact)
	{ _contact = contact; }
}
