package db;

import models.Product;
import models.ProductCategory;
import models.ProductData;

import javax.sql.rowset.CachedRowSet;
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
    public ArrayList<Product> getAllProducts(boolean retrieveAssociation)
    {
        ArrayList<Product> returnList = new ArrayList<Product>();
        try
        {
            PreparedStatement query = _da.getCon().prepareStatement("SELECT * FROM Products");
            _da.setSqlCommandText(query);
            ResultSet products = _da.callCommandGetResultSet();

            while(products.next())
            {
                Product product = buildProduct(products, retrieveAssociation);
                returnList.add(product);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
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
    public Product getProductById(long id, boolean retrieveAssociation)
    {
        try
        {
            PreparedStatement query = _da.getCon().prepareStatement("SELECT * FROM Products WHERE productId = ?");
            query.setLong(1, id);
            _da.setSqlCommandText(query);
            ResultSet productResult = _da.callCommandGetRow();
            productResult.next();
            Product prod = buildProduct(productResult, retrieveAssociation);
            return prod;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

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
    public Product getProductByName(String name, boolean retrieveAssociation)
    {
        try
        {
            PreparedStatement query = _da.getCon().prepareStatement("SELECT * FROM Products WHERE name = ?");
            query.setString(1, name);
            _da.setSqlCommandText(query);
            ResultSet productResult = _da.callCommandGetRow();
            productResult.next();
            return buildProduct(productResult, retrieveAssociation);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

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

        try
        {
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
            _da.setSqlCommandText(query);
            return _da.callCommand();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return 0;
    }

    /**
     * Update a product, that already exists in the database
     *
     * @param product the product object which contains a valid ID and the new data which should be updated
     * @return int returns the number of rows affected
     */
    @Override
    public int updateProduct(Product product)
    {
        return 0;
    }


    private Product buildProduct(ResultSet row, boolean retrieveAssociation)
    {
        if(row == null)
            return null;

        try
        {
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
                long categoryId = row.getLong("categoryKey");
                PreparedStatement queryCategory = _da.getCon().prepareStatement("SELECT * FROM ProductCategory WHERE categoryId = ?");
                queryCategory.setLong(1, categoryId);
                _da.setSqlCommandText(queryCategory);
                ResultSet categoryResults = _da.callCommandGetRow();
                categoryResults.next();
                ProductCategory category = buildProductCategory(categoryResults);
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
            }

            return product;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return null;
    }

    private ProductCategory buildProductCategory(ResultSet row)
    {
        if(row == null)
            return null;

        try
        {
            long categoryId = row.getInt("categoryId");
            String categoryName = row.getString("categoryName");
            return new ProductCategory(categoryId, categoryName);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return null;
    }

    private ProductData buildProductData(ResultSet row)
    {
        if(row == null)
            return null;

        try
        {
            String attribute = row.getString("attribute");
            String attributeValue = row.getString("attributeValue");
            return new ProductData(attribute, attributeValue);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return null;
    }
}
