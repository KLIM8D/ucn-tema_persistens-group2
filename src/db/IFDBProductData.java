package db;

import models.ProductData;

/**
 * Created: 26-10-2012
 * @version: 0.1
 * Filename: IFDBProductData.java
 * Description:
 * @changes
 */

public interface IFDBProductData
{
    /**
     * Insert new productdata to the database
     *
     * @param productId the ID of the product where the data should be added
     * @param data the data new data you want to add to the product
     * @return int returns the number of rows affected
     *
     */
    public int insertProductData(long productId, ProductData data) throws Exception;

    /**
     * Update some productdata in the database
     *
     * @param productId the ID of the product where the data should be updated
     * @param data the data new data you want to updated
     * @return int returns the number of rows affected
     *
     */
    public int updateProductData(long productId, ProductData data) throws Exception;

    /**
     * Insert a new product to the database
     *
     * @param productId the ID of the product where the data should be deleted
     * @param data the data which contains the attribute
     * @return int returns the number of rows affected
     *
     */
    public int deleteProductData(long productId, ProductData data) throws Exception;

    /**
     * Delete ALL product data associated to a product from the database
     *
     * @param productId the ID of the product where the data should be deleted
     * @return int returns the number of rows affected
     */
    public int deleteProductData(long productId) throws Exception;
}
