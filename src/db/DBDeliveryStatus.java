package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import models.DeliveryStatus;
import models.Product;

public class DBDeliveryStatus implements IFDBDeliveryStatus {

	private DataAccess _da;
	public DBDeliveryStatus()
	{
		_da = DataAccess.getInstance();
	}
	
	public ArrayList<DeliveryStatus> getAllDeliveryStatus() throws Exception {
		
		ArrayList<DeliveryStatus> returnList = new ArrayList<DeliveryStatus>();

        PreparedStatement query = _da.getCon().prepareStatement("SELECT * FROM Invoice");
        _da.setSqlCommandText(query);
        ResultSet deliveryStatus = _da.callCommandGetResultSet();

        while(deliveryStatus.next())
        {
        	DeliveryStatus dls = buildDeliveryStatus(deliveryStatus);
            returnList.add(dls);
        }

		return returnList;
	}

	public DeliveryStatus getDeliveryStatusById(long id) throws Exception {
        PreparedStatement query = _da.getCon().prepareStatement("SELECT * FROM DeliveryStatus WHERE deliveryId = ?");
        query.setLong(1, id);
        _da.setSqlCommandText(query);
        ResultSet deliveryResult = _da.callCommandGetRow();
        deliveryResult.next();

        return buildDeliveryStatus(deliveryResult);
	}

	public int insertDeliveryStatus(DeliveryStatus deliveryStatus) throws Exception {
		 if(deliveryStatus == null)
	            return 0;

	        PreparedStatement query = _da.getCon().prepareStatement("INSERT INTO DeliveryStatus (deliveryId, deliveryState) VALUES (?, ?)");

	        query.setLong(1, deliveryStatus.getDeliveryId());
	        query.setString(2, deliveryStatus.getDeliveryState());
	        _da.setSqlCommandText(query);

	        return _da.callCommand();
	}

	public int updateDeliveryStatus(DeliveryStatus deliveryStatus) throws Exception {
		if(deliveryStatus == null)
            return 0;

        PreparedStatement query = _da.getCon().prepareStatement("UPDATE DeliveryStatus SET deliverState = ? WHERE deliveryId = ?");
        query.setString(1, deliveryStatus.getDeliveryState());
        query.setLong(2,  deliveryStatus.getDeliveryId());
        _da.setSqlCommandText(query);

        return _da.callCommand();
	}
	
	public int deleteDeliveryStatus(DeliveryStatus deliveryStatus) throws Exception {
		if(deliveryStatus == null)
			return 0;

		if(getDeliveryStatusById(deliveryStatus.getDeliveryId()) == null)
			return 0;

		PreparedStatement query = _da.getCon().prepareStatement("DELETE FROM DeliveryStatus WHERE deliveryId = ?");	 
		query.setLong(1, deliveryStatus.getDeliveryId());
		_da.setSqlCommandText(query);
		return _da.callCommand();
	}
	
	private DeliveryStatus buildDeliveryStatus(ResultSet row) throws Exception
	{
		if(row == null)
			return null;
		
        int deliveryId = row.getInt("deliveryId");
        String deliveryState = row.getString("deliveryState");

        return new DeliveryStatus(deliveryId, deliveryState);
	}

}