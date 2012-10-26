package controllers;

import db.DBProductCategory;
import models.ProductCategory;

import java.util.ArrayList;

/**
 * Created: 26-10-2012
 * @version: 0.1
 * Filename: ProductCategoryCtrl.java
 * Description:
 * @changes
 */

public class ProductCategoryCtrl
{
    private DBProductCategory _dbProductCategory;
    public ProductCategoryCtrl()
    {
        _dbProductCategory = new DBProductCategory();
    }

    public ArrayList<ProductCategory> getAllProductCategories() throws Exception
    {
        return _dbProductCategory.getAllProductCategories();
    }

    public ProductCategory getProductCategoryById(long categoryId) throws Exception
    {
        return _dbProductCategory.getProductCategoryById(categoryId);
    }

    public ProductCategory getProductCategoryByName(String categoryName) throws Exception
    {
        return _dbProductCategory.getProductCategoryByName(categoryName);
    }

    public int insertProductCategory(ProductCategory productCategory) throws Exception
    {
        return _dbProductCategory.insertProductCategory(productCategory);
    }

    public int updateProductCategory(ProductCategory productCategory) throws Exception
    {
        return _dbProductCategory.updateProductCategory(productCategory);
    }

    public int deleteProductCategory(ProductCategory productCategory) throws Exception
    {
        return _dbProductCategory.deleteProductCategory(productCategory);
    }
}
