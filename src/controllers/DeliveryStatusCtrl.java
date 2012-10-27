package controllers;

import java.util.ArrayList;
import models.DeliveryStatus;
import db.DBDeliveryStatus;

public class DeliveryStatusCtrl
{

	public DeliveryStatusCtrl()
	{
		
	}
	
	public ArrayList<DeliveryStatus> getAllInvoice() throws Exception
	{
		DBDeliveryStatus DBD = new DBDeliveryStatus();
        return DBD.getAllDeliveryStatus();
	}
	
	public DeliveryStatus getInvoiceById(int id) throws Exception
	{
		DBDeliveryStatus DBD = new DBDeliveryStatus();
		return DBD.getDeliveryStatusById(id);
	}
	
	public int insertInvoice(DeliveryStatus deliveryStatus) throws Exception
	{
		DBDeliveryStatus DBD = new DBDeliveryStatus();
		return DBD.insertDeliveryStatus(deliveryStatus);
	}
	
	public int updateInvoice(DeliveryStatus deliveryStatus) throws Exception
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
