package models;

public class DeliveryStatus {
	private int _deliveryId;
	private String _deliveryState;
	
	public DeliveryStatus(int deliveryId, String deliveryState)
	{
		_deliveryId = deliveryId;
		_deliveryState = deliveryState;
	}

	public int getDeliveryId()
	{ return _deliveryId; }
	public void setDeliveryId(int value)
	{ _deliveryId = value; }
	
	public String getDeliveryState()
	{ return _deliveryState; }
	public void setDeliveryState(String value)
	{ _deliveryState = value; }	
}