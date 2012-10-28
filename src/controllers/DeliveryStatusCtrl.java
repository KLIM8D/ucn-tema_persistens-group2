package controllers;

import java.util.ArrayList;
import models.DeliveryStatus;
import db.DBDeliveryStatus;

public class DeliveryStatusCtrl
{

	public DeliveryStatusCtrl()
	{
		
	}
	
	public ArrayList<DeliveryStatus> getAllDeliveryStatus() throws Exception
	{
		DBDeliveryStatus DBD = new DBDeliveryStatus();
        return DBD.getAllDeliveryStatus();
	}
	
	public DeliveryStatus getDeliveryStatusById(int id) throws Exception
	{
		DBDeliveryStatus DBD = new DBDeliveryStatus();
		return DBD.getDeliveryStatusById(id);
	}
	
	public int insertDeliveryStatus(DeliveryStatus deliveryStatus) throws Exception
	{
		DBDeliveryStatus DBD = new DBDeliveryStatus();
		return DBD.insertDeliveryStatus(deliveryStatus);
	}
	
	public int updateDeliveryStatus(DeliveryStatus deliveryStatus) throws Exception
	{
		DBDeliveryStatus DBD = new DBDeliveryStatus();
		return DBD.updateDeliveryStatus(deliveryStatus);
	}
	
	public int deleteDeliveryStatus(DeliveryStatus deliveryStatus) throws Exception
	{
		DBDeliveryStatus DBD = new DBDeliveryStatus();
		return DBD.deleteDeliveryStatus(deliveryStatus);
	}
}
