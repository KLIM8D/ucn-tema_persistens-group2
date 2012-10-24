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
    private String _name;
    private BigDecimal _purchasePrice;
    private BigDecimal _salesPrice;
    private BigDecimal _rentPrice;
    private String _countryOfOrigin;
    private long _minimumStock;
    private ProductCategory _category;
    private Supplier _supplier;
    private ArrayList<ProductData> _productData;

    public Product(String name, String purchasePrice, String salesPrice, String rentPrice, String countryOfOrigin, long minimumStock)
    {
        _name = name;
        _purchasePrice = new BigDecimal(purchasePrice);
        _salesPrice = new BigDecimal(salesPrice);
        _rentPrice = new BigDecimal(rentPrice);
        _countryOfOrigin = countryOfOrigin;
        _minimumStock = minimumStock;
    }

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

    public ArrayList<ProductData> getProductData()
    { return _productData; }
    public void insertProductData(ProductData value)
    { _productData.add(value); }
}
