package models;

/**
 * Created: 24-10-2012
 * @version: 0.1
 * Filename: ProductData.java
 * Description:
 * @changes
 */

public class ProductData
{
    private String _attribute;
    private String _attributeValue;

    public ProductData(String attribute, String attributeValue)
    {
        _attribute = attribute;
        _attributeValue = attributeValue;
    }

    public String getAttribute()
    { return _attribute; }
    public void setAttribute(String value)
    { _attribute = value; }

    public String getAttributeValue()
    { return _attributeValue; }
    public void setAttributeValue(String value)
    { _attributeValue = value; }
}
