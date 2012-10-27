package models;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created: 24-10-2012
 * @version: 0.1
 * Filename: Product.java
 * Description:
 * @changes
 */

public class Product
{
    private long _id;
    private String _name;
    private BigDecimal _purchasePrice;
    private BigDecimal _salesPrice;
    private BigDecimal _rentPrice;
    private String _countryOfOrigin;
    private long _minimumStock;
    private ProductCategory _category;
    private Supplier _supplier;
    private ArrayList<ProductData> _productData;

    public Product(long id, String name, String purchasePrice, String salesPrice, String rentPrice, String countryOfOrigin, long minimumStock)
    {
        _id = id;
        _name = name;
        _purchasePrice = new BigDecimal(purchasePrice);
        _salesPrice = new BigDecimal(salesPrice);
        _rentPrice = new BigDecimal(rentPrice);
        _countryOfOrigin = countryOfOrigin;
        _minimumStock = minimumStock;
        _productData = new ArrayList<ProductData>();
    }

    public Product(long id, String name, String purchasePrice, String salesPrice, String rentPrice, String countryOfOrigin, long minimumStock, ProductCategory category, Supplier supplier)
    {
        _id = id;
        _name = name;
        _purchasePrice = new BigDecimal(purchasePrice);
        _salesPrice = new BigDecimal(salesPrice);
        _rentPrice = new BigDecimal(rentPrice);
        _countryOfOrigin = countryOfOrigin;
        _minimumStock = minimumStock;
        _category = category;
        _supplier = supplier;
        _productData = new ArrayList<ProductData>();
    }

    public long getId()
    { return _id; }
    public void setId(long value)
    { _id = value; }

    public String getName()
    { return _name; }
    public void setName(String value)
    { _name = value; }

    public BigDecimal getPurchasePrice()
    { return _purchasePrice; }
    public void setPurchasePrice(BigDecimal value)
    { _purchasePrice = value; }

    public BigDecimal getSalesPrice()
    { return _salesPrice; }
    public void setSalesPrice(BigDecimal value)
    { _salesPrice = value; }

    public BigDecimal getRentPrice()
    { return _rentPrice; }
    public void setRentPrice(BigDecimal value)
    { _rentPrice = value; }

    public String getCountryOfOrigin()
    { return _countryOfOrigin; }
    public void setCountryOfOrigin(String value)
    { _countryOfOrigin = value; }

    public long getMinimumStock()
    { return _minimumStock; }
    public void setMinimumStock(long value)
    { _minimumStock = value; }

    public ProductCategory getCategory()
    { return _category; }
    public void setCategory(ProductCategory value)
    { _category = value; }

    public Supplier getSupplier()
    { return _supplier; }
    public void setSupplier(Supplier value)
    { _supplier = value; }

    public ArrayList<ProductData> getProductData()
    { return _productData; }
    public void setProductData(ArrayList<ProductData> value)
    { _productData  = value; }
    public void addProductData(ProductData value)
    { _productData.add(value); }
}
