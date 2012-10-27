package db;

import models.ProductData;

import java.sql.PreparedStatement;

/**
 * Created: 26-10-2012
 * @version: 0.1
 * Filename: DBProductData.java
 * Description:
 * @changes
 */

public class DBProductData implements IFDBProductData
{
    private DataAccess _da;
    public DBProductData()
    {
        _da = DataAccess.getInstance();
    }

    /**
     * Insert new productdata to the database
     *
     * @param productId the ID of the product where the data should be added
     * @param data      the data new data you want to add to the product
     * @return int returns the number of rows affected
     */
    @Override
    public int insertProductData(long productId, ProductData data) throws Exception
    {
        if(data == null)
            return 0;

        PreparedStatement query = _da.getCon().prepareStatement("INSERT INTO ProductData (productKey, attribute, attributeValue) VALUES (?, ?, ?)");
        query.setLong(1, productId);
        query.setString(2, data.getAttribute());
        query.setString(3, data.getAttributeValue());
        _da.setSqlCommandText(query);

        return _da.callCommand();
    }

    /**
     * Update some productdata in the database
     *
     * @param productId the ID of the product where the data should be updated
     * @param data      the data new data you want to updated
     * @return int returns the number of rows affected
     */
    @Override
    public int updateProductData(long productId, ProductData data) throws Exception
    {
        if(data == null)
            return 0;

        PreparedStatement query = _da.getCon().prepareStatement("UPDATE ProductData SET attributeValue = ? WHERE productKey = ? AND attribute = ?");
        query.setString(1, data.getAttributeValue());
        query.setLong(2, productId);
        query.setString(3, data.getAttribute());
        _da.setSqlCommandText(query);

        return _da.callCommand();
    }

    /**
     * Insert a new product to the database
     *
     * @param productId the ID of the product where the data should be deleted
     * @param data      the data which contains the attribute
     * @return int returns the number of rows affected
     */
    @Override
    public int deleteProductData(long productId, ProductData data) throws Exception
    {
        if(data == null)
            return 0;

        PreparedStatement query = _da.getCon().prepareStatement("DELETE FROM ProductData WHERE productKey = ? AND attribute = ?");
        query.setLong(1, productId);
        query.setString(2, data.getAttribute());
        _da.setSqlCommandText(query);

        return _da.callCommand();
    }
}
