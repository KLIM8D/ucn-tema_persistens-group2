package models;

/**
 * Created: 24-10-2012
 * @version: 0.1
 * Filename: ProductCategory.java
 * Description:
 * @changes
 */

public class ProductCategory
{
    private long _categoryId;
    private String _categoryName;

    public ProductCategory(long categoryId, String categoryName)
    {
        _categoryId = categoryId;
        _categoryName = categoryName;
    }

    public String getCategoryName()
    { return _categoryName; }
    public void setCategoryName(String value)
    { _categoryName = value; }

    public long getCategoryId()
    { return _categoryId; }
    public void setCategoryId(int value)
    { _categoryId = value; }
}
