package controllers;

import db.DBProduct;
import db.DBProductData;
import models.Product;
import models.ProductData;

import java.util.ArrayList;

/**
 * Created: 26-10-2012
 * @version: 0.1
 * Filename: ProductCtrl.java
 * Description:
 * @changes
 */

public class ProductCtrl
{
    DBProduct _dbProduct;
    DBProductData _dbProductData;
    public ProductCtrl()
    {
        _dbProduct = new DBProduct();
        _dbProductData = new DBProductData();
    }

    public ArrayList<Product> getAllProducts(boolean retrieveAssociation) throws Exception
    {
        return _dbProduct.getAllProducts(retrieveAssociation);
    }

    public Product getProductById(long productId, boolean retrieveAssociation) throws Exception
    {
        return _dbProduct.getProductById(productId,retrieveAssociation);
    }

    public Product getProductByName(String name, boolean retrieveAssociation) throws Exception
    {
        return _dbProduct.getProductByName(name, retrieveAssociation);
    }

    public int insertProduct(Product product) throws Exception
    {
        return _dbProduct.insertProduct(product);
    }

    public int updateProduct(Product product) throws Exception
    {
        return _dbProduct.updateProduct(product);
    }

    public int deleteProduct(Product product) throws Exception
    {
        return _dbProduct.deleteProduct(product);
    }

    public int insertProductData(long productId, ProductData data) throws Exception
    {
        return _dbProductData.insertProductData(productId, data);
    }

    public int updateProductData(long productId, ProductData data) throws Exception
    {
        return _dbProductData.updateProductData(productId, data);
    }

    public int deleteProductData(long productId, ProductData data) throws Exception
    {
        return _dbProductData.deleteProductData(productId, data);
    }
}
