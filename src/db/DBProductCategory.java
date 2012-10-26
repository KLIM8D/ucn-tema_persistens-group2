package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import models.ProductCategory;

public class DBProductCategory implements IFDBProductCategory
{
	 private DataAccess _da;
	 public DBProductCategory()
	 {	
		 _da = DataAccess.getInstance();
	 }

    /**
     * Get all the productCategories from the database
     *
     * @return ArrayList<ProductCategory>
     *
     */
	 @Override
 	 public ArrayList<ProductCategory> getAllProductCategories() throws Exception
	 {
 		 ArrayList<ProductCategory> returnList = new ArrayList<ProductCategory>();

 		 PreparedStatement query = _da.getCon().prepareStatement("SELECT * FROM ProductCategories");
 		 _da.setSqlCommandText(query);
 		 ResultSet productCategories = _da.callCommandGetResultSet();

 		 while(productCategories.next())
 		 {
 			 ProductCategory Categories = buildProductCategory(productCategories);
 			 returnList.add(Categories);
 		 }

 		 return returnList;
	 }

    /**
     * Get a specific productCategory by productCategory id
     *
     * @param id the ID of the productCategory you want returned
     * @return ProductCategory
     *
     */
	 @Override
 	 public ProductCategory getProductCategoryById(long id) throws Exception
 	 {
 		 PreparedStatement query = _da.getCon().prepareStatement("SELECT * FROM ProductCategory WHERE categoryId = ?");
 		 query.setLong(1, id);
 		 _da.setSqlCommandText(query);
 		 ResultSet categoryResult = _da.callCommandGetRow();
 		 categoryResult.next();
 		 return buildProductCategory(categoryResult);
 	 }

    /**
     * Get a specific productCategory by productCategory name
     *
     * @param name the name of the productCategory you want returned
     * @return ProductCategory
     *
     */
	 @Override
 	 public ProductCategory getProductCategoryByName(String name) throws Exception
 	 {
 		 PreparedStatement query = _da.getCon().prepareStatement("SELECT * FROM productCategory WHERE categoryName = ?");
 		 query.setString(1, name);
 		 _da.setSqlCommandText(query);
 		 ResultSet categoryResult = _da.callCommandGetRow();
 		 categoryResult.next();
 		 return buildProductCategory(categoryResult);
 	 }

    /**
     * Insert a new productCategory to the database
     *
     * @param productCategory the productCategory object with the data you want added
     * @return int returns the number of rows affected
     *
     */
	 @Override
 	 public int insertProductCategory(ProductCategory productCategory) throws Exception 
 	 {
 		 if(productCategory == null)
 			 return 0;

 		 PreparedStatement query = _da.getCon().prepareStatement("INSERT INTO ProductCategory (categoryName) VALUES (?)");
 		 query.setString(1, productCategory.getCategoryName());
 		 _da.setSqlCommandText(query);
 		 return _da.callCommand();
 	 }

    /**
     * Update a productCategory, that already exists in the database
     *
     * @param productCategory the productCategory object which contains a valid ID and the new data which should be updated
     * @return int returns the number of rows affected
     *
     */
	 @Override
 	 public int updateProductCategory(ProductCategory productCategory) throws Exception
 	 {
 		 if(productCategory == null)
 			 return 0;

 		 if(getProductCategoryById(productCategory.getCategoryId()) == null)
 			 return 0;

 		 PreparedStatement query = _da.getCon().prepareStatement("UPDATE ProductCategory SET categoryName = ? WHERE categoryId = ?");
 		 query.setString(1, productCategory.getCategoryName());
 		 query.setLong(2, productCategory.getCategoryId());
 		 _da.setSqlCommandText(query);
 		 return _da.callCommand();
 	 }

    /**
     * Delete an existing productCategory from the database
     *
     * @param productCategory the productCategory object which contains a valid ID
     * @return int returns the number of rows affected
     *
     */
	 @Override
 	 public int deleteProductCategory(ProductCategory productCategory) throws Exception
 	 {
 		 if(productCategory == null)
 			 return 0;

 		 if(getProductCategoryById(productCategory.getCategoryId()) == null)
 			 return 0;

 		 PreparedStatement query = _da.getCon().prepareStatement("DELETE FROM ProductCategory WHERE categoryId = ?");
 		 query.setLong(1, productCategory.getCategoryId());
 		 _da.setSqlCommandText(query);
 		 return _da.callCommand();
 	 }

 	 private ProductCategory buildProductCategory(ResultSet row) throws Exception
 	 {
 		 if(row == null)
 			 return null;

 		 long categoryId = row.getInt("categoryId");
 		 String categoryName = row.getString("categoryName");
 		 return new ProductCategory(categoryId, categoryName);
 	 }
}