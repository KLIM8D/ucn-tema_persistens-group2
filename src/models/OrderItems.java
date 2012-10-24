package models;

import java.math.BigDecimal;

public class OrderItems {
	private int _quantity;
	private BigDecimal _unitPrice;
	
	public OrderItems(int quantity, BigDecimal unitPrice)
	{
		_quantity = quantity;
		_unitPrice = unitPrice;
	}
	
	public int get_quantity()
	{ return _quantity; }
	public void set_quantity(int value)
	{ _quantity = value; }
	
	public BigDecimal get_unitPrice()
	{ return _unitPrice; }
	public void set_unitPrice(BigDecimal value)
	{ _unitPrice = value; }
}