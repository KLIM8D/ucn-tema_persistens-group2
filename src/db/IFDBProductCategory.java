package db;

import models.ProductCategory;

import java.util.ArrayList;

/**
 * Created: 25-10-2012
 * @version: 0.1
 * Filename: IFDBProductCategory.java
 * Description:
 * @changes
 */

public interface IFDBProductCategory
{

    /**
    * Get all the productCategories from the database
    *
    * @param retrieveAssociation set to true if you want Contact data.
    * @return ArrayList<ProductCategory>
    *
    */
    public ArrayList<ProductCategory> getAllProductCategorys(boolean retrieveAssociation);

    /**
     * Get a specific productCategory by productCategory id
     *
     * @param id the ID of the productCategory you want returned
     * @param retrieveAssociation set to true if you want Contact data.
     * @return ProductCategory
     *
     */
    public ProductCategory getProductCategoryById(long id, boolean retrieveAssociation);

    /**
     * Get a specific productCategory by productCategory name
     *
     * @param name the name of the productCategory you want returned
     * @param retrieveAssociation set to true if you want Contact data.
     * @return ProductCategory
     *
     */
    public ProductCategory getProductCategoryByName(String name, boolean retrieveAssociation);

    /**
    * Insert a new productCategory to the database
    *
    * @param productCategory the productCategory object with the data you want added
    * @return int returns the number of rows affected
    *
    */
    public int insertProductCategory(ProductCategory productCategory) throws Exception;

    /**
     * Update a productCategory, that already exists in the database
     *
     * @param productCategory the productCategory object which contains a valid ID and the new data which should be updated
     * @return int returns the number of rows affected
     *
     */
    public int updateProductCategory(ProductCategory productCategory);
}