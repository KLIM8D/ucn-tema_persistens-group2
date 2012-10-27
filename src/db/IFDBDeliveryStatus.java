package db;

import java.util.ArrayList;

import models.DeliveryStatus;

public interface IFDBDeliveryStatus
{

    /**
     * Retrieve all deliveryStatus' from database
     *
     * @return ArrayList<deliveryStatus>
     */
    public ArrayList<DeliveryStatus> getAllDeliveryStatus() throws Exception;

    /**
     * Get specific DeliveryStatus by id
     *
     * @param id					the id of the DeliveryStatus you need returned
     * @return DeliveryStatus
     */
    public DeliveryStatus getDeliveryStatusById(long id) throws Exception;

    /**
     * Inserts a new invoice in the database
     *
     * @param deliveryStatus		the object containing the information you want stored
     * @return						returns the number of rows affected
     */
    public int insertDeliveryStatus(DeliveryStatus deliveryStatus) throws Exception;

    /**
     * Update a existing DeliveryStatus in database
     *
     * @param deliveryStatus 		the object containing the updated information you want stored
     * @return						returns the number of rows affected
     */
    public int updateDeliveryStatus(DeliveryStatus deliveryStatus) throws Exception;

    /**
     * Delete an existing DeliveryStatus from the database
     *
     * @param deliveryStatus 		the object containing a valid ID and which should be deleted from the database
     * @return						returns the number of rows affected
     */
    public int deleteDeliveryStatus(DeliveryStatus deliveryStatus) throws Exception;
}