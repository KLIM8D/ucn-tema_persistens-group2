package models;

import java.math.BigDecimal;

public class Customer extends Contact
{
	
	private BigDecimal _discount;
	private boolean _isBusiness;
	
	public Customer(String name, String address, long zipCode, String city, long phoneNo, String email, String country, BigDecimal discount, boolean isBusiness)
	{
		super(name, address, zipCode, city, phoneNo, email, country);
		_discount = discount;
		_isBusiness = isBusiness;
	}

    public BigDecimal getDiscount()
    { return _discount; }
    public void setDiscount(BigDecimal discount)
    { _discount = discount; }

    public boolean isBusiness()
    { return _isBusiness; }
    public void setBusiness(boolean isBusiness)
    { _isBusiness = isBusiness; }
}
