package models;

import java.util.ArrayList;

public class SalesOrder
{
	private long _orderId;
	private String _orderDate;
	private String _deliveryDate;
	private DeliveryStatus _deliveryStatus;
	private Customer _customer;
    private ArrayList<OrderItems> _orderItems;
	
	
	public SalesOrder(long orderId, String orderDate, String deliveryDate)
	{
		_orderId = orderId;
		_orderDate = orderDate;
		_deliveryDate = deliveryDate;
	}
	
	public SalesOrder(long orderId, String orderDate, String deliveryDate, DeliveryStatus deliveryStatus, Customer customer)
	{
		_orderId = orderId;
		_orderDate = orderDate;
		_deliveryDate = deliveryDate;
		_deliveryStatus = deliveryStatus;
		_customer = customer;
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
	
	public Customer getCustomer()
	{ return _customer; }
	public void setCustomer(Customer customer)
	{ _customer = customer; }

    public ArrayList<OrderItems> getAllOrderItems()
    { return _orderItems; }
    public void setOrderItems(ArrayList<OrderItems> value)
    { _orderItems = value; }
    public void addOrderItem(OrderItems item)
    { _orderItems.add(item); }
}
