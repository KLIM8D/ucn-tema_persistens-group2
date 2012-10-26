package models;

public class DeliveryStatus
{
	private long _deliveryId;
	private String _deliveryState;
	
	public DeliveryStatus(long deliveryId, String deliveryState)
	{
		_deliveryId = deliveryId;
		_deliveryState = deliveryState;
	}

	public long getDeliveryId()
	{ return _deliveryId; }
	public void setDeliveryId(long value)
	{ _deliveryId = value; }
	
	public String getDeliveryState()
	{ return _deliveryState; }
	public void setDeliveryState(String value)
	{ _deliveryState = value; }	
}