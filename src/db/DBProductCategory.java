package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import models.Contact;
import models.ProductCategory;
import models.Supplier;

public class DBProductCategory implements IFDBProductCategory
{
	 private DataAccess _da;
	 public DBProductCategory()
	 {	
		 _da = DataAccess.getInstance();
	 }
	 
	public ArrayList<ProductCategory> getAllProductCategorys(boolean retrieveAssociation)
	{
		ArrayList<ProductCategory> returnList = new ArrayList<ProductCategory>();
        try
        {
            PreparedStatement query = _da.getCon().prepareStatement("SELECT * FROM ProductCategories");
            _da.setSqlCommandText(query);
            ResultSet productCategories = _da.callCommandGetResultSet();

            while(productCategories.next())
            {
            	ProductCategory Categories = buildProductCategory(productCategories, retrieveAssociation);
                returnList.add(Categories);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return returnList;
	}

	public ProductCategory getProductCategoryById(long id, boolean retrieveAssociation) 
	{
		try
        {
            PreparedStatement query = _da.getCon().prepareStatement("SELECT * FROM ProductCategory WHERE categoryId = ?");
            query.setLong(1, id);
            _da.setSqlCommandText(query);
            ResultSet categoryResult = _da.callCommandGetRow();
            categoryResult.next();
            return buildProductCategory(categoryResult, true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
	}
	
	public ProductCategory getProductCategoryByName(String name, boolean retrieveAssociation) 
	{
        try
        {
            PreparedStatement query = _da.getCon().prepareStatement("SELECT * FROM productCategory WHERE categoryName = ?");
            query.setString(1, name);
            _da.setSqlCommandText(query);
            ResultSet categoryResult = _da.callCommandGetRow();
            categoryResult.next();
            return buildProductCategory(categoryResult, true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
	}
	
	public int insertProductCategory(ProductCategory productCategory) throws Exception 
	{
		return 0;
	}
	
	public int updateProductCategory(ProductCategory productCategory) 
	{
		return 0;
	}
	 
	public ProductCategory buildProductCategory(ResultSet row, boolean retrieveAssociation)
	{
		 if(row == null)
	            return null;

	        try
	        {
	            long categoryId = row.getLong("categoryId");
	            String categoryName = row.getString("categoryName");          
	        	
	            ProductCategory PDC = new ProductCategory(categoryId, categoryName);
	            return PDC;
	        }
	        catch (Exception ex) 
	        {
	            ex.printStackTrace();
	        }
	        return null;
	}
}