package db;

import java.util.ArrayList;

import models.ProductCategory;

public class DBProductCategory implements IFDBProductCategory {
	 private DataAccess _da;
	 public DBProductCategory()
	 {	
		 _da = DataAccess.getInstance();
	 }
	 
	@Override
	public ArrayList<ProductCategory> getAllProductCategorys(
			boolean retrieveAssociation) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ProductCategory getProductCategoryById(long id,
			boolean retrieveAssociation) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ProductCategory getProductCategoryByName(String name,
			boolean retrieveAssociation) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int insertProductCategory(ProductCategory productCategory)
			throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int updateProductCategory(ProductCategory productCategory) {
		// TODO Auto-generated method stub
		return 0;
	}
	 
}