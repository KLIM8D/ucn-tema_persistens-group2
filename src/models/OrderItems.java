package models;

import java.math.BigDecimal;

public class OrderItems
{
	private long _quantity;
	private BigDecimal _unitPrice;
	private Product _product;
	
	public OrderItems(long quantity, BigDecimal unitPrice)
	{
		_quantity = quantity;
		_unitPrice = unitPrice;
	}
	
	public OrderItems(long quantity, BigDecimal unitPrice, Product product)
	{
		_quantity = quantity;
		_unitPrice = unitPrice;
		_product = product;
	}
	
	public long getQuantity()
	{ return _quantity; }
	public void setQuantity(long value)
	{ _quantity = value; }
	
	public BigDecimal getUnitPrice()
	{ return _unitPrice; }
	public void setUnitPrice(BigDecimal value)
	{ _unitPrice = value; }
	
	public Product getProduct()
	{ return _product; }
	public void setProduct(Product product)
	{ _product = product; }
	
}