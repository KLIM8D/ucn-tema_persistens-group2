package models;

public class Customer extends Contact
{
	
	private double _discount;
	private boolean _isBusiness;
	
	public Customer(String name, String address, int zipCode, String city, long phoneNo, String email, String country, double discount, boolean isBusiness)
	{
		super(name, address, zipCode, city, phoneNo, email, country);
		_discount = discount;
		_isBusiness = isBusiness;
	}

    public double getDiscount()
    { return _discount; }
    public void setDiscount(double discount)
    { _discount = discount; }

    public boolean isBusiness()
    { return _isBusiness; }
    public void setBusiness(boolean isBusiness)
    { _isBusiness = isBusiness; }
}
