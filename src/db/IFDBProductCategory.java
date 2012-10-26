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
    * @return ArrayList<ProductCategory>
    *
    */
    public ArrayList<ProductCategory> getAllProductCategories() throws Exception;

    /**
     * Get a specific productCategory by productCategory id
     *
     * @param id the ID of the productCategory you want returned
     * @return ProductCategory
     *
     */
    public ProductCategory getProductCategoryById(long id) throws Exception;

    /**
     * Get a specific productCategory by productCategory name
     *
     * @param name the name of the productCategory you want returned
     * @return ProductCategory
     *
     */
    public ProductCategory getProductCategoryByName(String name) throws Exception;

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
    public int updateProductCategory(ProductCategory productCategory) throws Exception;

    /**
     * Delete an existing productCategory from the database
     *
     * @param productCategory the productCategory object which contains a valid ID
     * @return int returns the number of rows affected
     *
     */
    public int deleteProductCategory(ProductCategory productCategory) throws Exception;
}