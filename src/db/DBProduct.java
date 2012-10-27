package db;

import models.Product;
import models.ProductCategory;
import models.ProductData;
import models.Supplier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created: 24-10-2012
 * @version: 0.1
 * Filename: DBProduct.java
 * Description:
 * @changes
 */

public class DBProduct implements IFDBProduct
{
    private DataAccess _da;
    public DBProduct()
    {
        _da = DataAccess.getInstance();
    }

    /**
     * Get all the products from the database
     *
     * @param retrieveAssociation set to true if you want ProductData, Supplier and ProductCategory
     * @return ArrayList<Product>
     */
    @Override
    public ArrayList<Product> getAllProducts(boolean retrieveAssociation) throws Exception
    {
        ArrayList<Product> returnList = new ArrayList<Product>();

        PreparedStatement query = _da.getCon().prepareStatement("SELECT * FROM Products");
        _da.setSqlCommandText(query);
        ResultSet products = _da.callCommandGetResultSet();

        while(products.next())
        {
            Product product = buildProduct(products, retrieveAssociation);
            returnList.add(product);
        }

        return returnList;
    }

    /**
     * Get a specific product by product id
     *
     * @param id                  the ID of the product you want returned
     * @param retrieveAssociation set to true if you want ProductData, Supplier and ProductCategory
     * @return Product
     */
    @Override
    public Product getProductById(long id, boolean retrieveAssociation) throws Exception
    {
        PreparedStatement query = _da.getCon().prepareStatement("SELECT * FROM Products WHERE productId = ?");
        query.setLong(1, id);
        _da.setSqlCommandText(query);
        ResultSet productResult = _da.callCommandGetRow();
        if(productResult.next())
            return buildProduct(productResult, retrieveAssociation);

        return null;
    }

    /**
     * Get a specific product by product name
     *
     * @param name                the name of the product you want returned
     * @param retrieveAssociation set to true if you want ProductData, Supplier and ProductCategory
     * @return Product
     */
    @Override
    public Product getProductByName(String name, boolean retrieveAssociation) throws Exception
    {
        PreparedStatement query = _da.getCon().prepareStatement("SELECT * FROM Products WHERE name = ?");
        query.setString(1, name);
        _da.setSqlCommandText(query);
        ResultSet productResult = _da.callCommandGetRow();
        if(productResult.next())
            return buildProduct(productResult, retrieveAssociation);

        return null;
    }

    /**
     * Insert a new product to the database
     *
     * @param product the product object with the data you want added
     * @return int returns the number of rows affected
     */
    @Override
    public int insertProduct(Product product) throws Exception
    {
        if(product == null)
            return 0;

        PreparedStatement query = _da.getCon().prepareStatement("INSERT INTO Products (contactsKey, categoryKey, name, purchasePrice, salesPrice, rentPrice, countryOfOrigin, minimumStock) " +
                                  "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

        query.setLong(1, product.getSupplier().getPhoneNo());
        query.setLong(2, product.getCategory().getCategoryId());
        query.setString(3, product.getName());
        query.setDouble(4, product.getPurchasePrice().doubleValue());
        query.setDouble(5, product.getSalesPrice().doubleValue());
        query.setDouble(6, product.getRentPrice().doubleValue());
        query.setString(7, product.getCountryOfOrigin());
        query.setLong(8, product.getMinimumStock());

        DBProductData dbProductData = new DBProductData();
        for(ProductData data : product.getProductData())
            dbProductData.insertProductData(product.getId(), data);

        _da.setSqlCommandText(query);
        return _da.callCommand();
    }

    /**
     * Update a product, that already exists in the database
     *
     * @param product the product object which contains a valid ID and the new data which should be updated
     * @return int returns the number of rows affected
     */
    @Override
    public int updateProduct(Product product) throws Exception
    {
        if(product == null)
            return 0;

        if(getProductById(product.getId(), true) == null)
            return 0;

        PreparedStatement query = _da.getCon().prepareStatement("UPDATE Products SET contactsKey = ?, categoryKey = ?, name = ?, purchasePrice = ?, salesPrice = ?, rentPrice = ?, countryOfOrigin = ?, minimumStock = ? " +
                "WHERE productId = ?");

        query.setLong(1, product.getSupplier().getPhoneNo());
        query.setLong(2, product.getCategory().getCategoryId());
        query.setString(3, product.getName());
        query.setDouble(4, product.getPurchasePrice().doubleValue());
        query.setDouble(5, product.getSalesPrice().doubleValue());
        query.setDouble(6, product.getRentPrice().doubleValue());
        query.setString(7, product.getCountryOfOrigin());
        query.setLong(8, product.getMinimumStock());
        query.setLong(9, product.getId());
        _da.setSqlCommandText(query);
        return _da.callCommand();
    }

    /**
     * Delete a product from the database
     *
     * @param product the product object which contains a valid ID
     * @return int returns the number of rows affected
     */
    @Override
    public int deleteProduct(Product product) throws Exception
    {
        if(product == null)
            return 0;

        if(getProductById(product.getId(), true) == null)
            return 0;

        PreparedStatement query = _da.getCon().prepareStatement("DELETE FROM Products WHERE productId = ?");
        query.setLong(1, product.getId());
        _da.setSqlCommandText(query);
        return _da.callCommand();
    }

    private Product buildProduct(ResultSet row, boolean retrieveAssociation) throws Exception
    {
        if(row == null)
            return null;

        long productId = row.getLong("productId");
        String name = row.getString("name");
        String purchasePrice = row.getString("purchasePrice");
        String salesPrice = row.getString("salesPrice");
        String rentPrice = row.getString("rentPrice");
        String countryOfOrigin = row.getString("countryOfOrigin");
        int minimumStock = row.getInt("minimumStock");
        Product product = new Product(productId, name, purchasePrice, salesPrice, rentPrice, countryOfOrigin, minimumStock);

        if(retrieveAssociation)
        {
            //ProductCategory
            DBProductCategory dbProductCategory = new DBProductCategory();
            long categoryId = row.getLong("categoryKey");
            ProductCategory category = dbProductCategory.getProductCategoryById(categoryId);
            product.setCategory(category);

            //ProductData
            PreparedStatement queryProductData = _da.getCon().prepareStatement("SELECT * FROM ProductData WHERE productKey = ?");
            queryProductData.setLong(1, productId);
            _da.setSqlCommandText(queryProductData);
            ResultSet dataResults = _da.callCommandGetResultSet();
            while(dataResults.next())
            {
               ProductData prodData = buildProductData(dataResults);
               product.addProductData(prodData);
            }

            //Supplier
            DBSupplier dbSupplier = new DBSupplier();
            long supplierId = row.getLong("contactsKey");
            Supplier supplier = dbSupplier.getSupplierById(supplierId);
            product.setSupplier(supplier);
        }

        return product;
    }

    private ProductData buildProductData(ResultSet row) throws Exception
    {
        if(row == null)
            return null;

        String attribute = row.getString("attribute");
        String attributeValue = row.getString("attributeValue");
        return new ProductData(attribute, attributeValue);
    }
}
