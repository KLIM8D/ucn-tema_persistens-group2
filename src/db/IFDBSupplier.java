package db;

import models.Supplier;

import java.util.ArrayList;

/**
 * Created: 25-10-2012
 * @version: 0.1
 * Filename: IFDBSupplier.java
 * Description:
 * @changes
 */

public interface IFDBSupplier
{

    /**
    * Get all the suppliers from the database
    *
    * @return ArrayList<Supplier>
    *
    */
    public ArrayList<Supplier> getAllSuppliers() throws Exception;

    /**
     * Get a specific supplier by supplier id
     *
     * @param id the ID of the supplier you want returned
     * @return Supplier
     *
     */
    public Supplier getSupplierById(long id) throws Exception;

    /**
     * Get a specific supplier by supplier name
     *
     * @param name the name of the supplier you want returned
     * @return Supplier
     *
     */
    public Supplier getSupplierByName(String name) throws Exception;

    /**
    * Insert a new supplier to the database
    *
    * @param supplier the supplier object with the data you want added
    * @return int returns the number of rows affected
    *
    */
    public int insertSupplier(Supplier supplier) throws Exception;

    /**
     * Update a supplier, that already exists in the database
     *
     * @param supplier the supplier object which contains a valid ID and the new data which should be updated
     * @return int returns the number of rows affected
     *
     */
    public int updateSupplier(Supplier supplier) throws Exception;

    /**
     * Delete a supplier, that already exists in the database
     *
     * @param supplier the supplier object which contains a valid ID
     * @return int returns the number of rows affected
     *
     */
    public int deleteSupplier(Supplier supplier) throws Exception;
}