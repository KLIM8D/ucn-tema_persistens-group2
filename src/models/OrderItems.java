package models;

import java.math.BigDecimal;

public class OrderItems
{
	private int _quantity;
	private BigDecimal _unitPrice;
	
	public OrderItems(int quantity, BigDecimal unitPrice)
	{
		_quantity = quantity;
		_unitPrice = unitPrice;
	}
	
	public int getQuantity()
	{ return _quantity; }
	public void setQuantity(int value)
	{ _quantity = value; }
	
	public BigDecimal getUnitPrice()
	{ return _unitPrice; }
	public void setUnitPrice(BigDecimal value)
	{ _unitPrice = value; }
}