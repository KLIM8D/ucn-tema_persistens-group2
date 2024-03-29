package db;

import models.Product;

import java.util.ArrayList;

/**
 * Created: 24-10-2012
 * @version: 0.1
 * Filename: IFDBProduct.java
 * Description:
 * @changes
 */

public interface IFDBProduct
{

    /**
    * Get all the products from the database
    *
    * @param retrieveAssociation set to true if you want ProductData, Supplier and ProductCategory
    * @return ArrayList<Product>
    *
    */
    public ArrayList<Product> getAllProducts(boolean retrieveAssociation) throws Exception;

    /**
     * Get a specific product by product id
     *
     * @param id the ID of the product you want returned
     * @param retrieveAssociation set to true if you want ProductData, Supplier and ProductCategory
     * @return Product
     *
     */
    public Product getProductById(long id, boolean retrieveAssociation) throws Exception;

    /**
     * Get a specific product by product name
     *
     * @param name the name of the product you want returned
     * @param retrieveAssociation set to true if you want ProductData, Supplier and ProductCategory
     * @return Product
     *
     */
    public Product getProductByName(String name, boolean retrieveAssociation) throws Exception;

    /**
    * Insert a new product to the database
    *
    * @param product the product object with the data you want added
    * @return int returns the number of rows affected
    *
    */
    public int insertProduct(Product product) throws Exception;

    /**
     * Update a product, that already exists in the database
     *
     * @param product the product object which contains a valid ID and the new data which should be updated
     * @return int returns the number of rows affected
     *
     */
    public int updateProduct(Product product) throws Exception;

    /**
     * Delete a product from the database
     *
     * @param product the product object which contains a valid ID
     * @return int returns the number of rows affected
     */
    public int deleteProduct(Product product) throws Exception;

}
