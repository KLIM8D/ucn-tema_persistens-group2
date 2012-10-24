package models;

public class Customer extends Contact
{
	
	private double discount_;
	private boolean isBusiness_;
	
	public Customer(String name, String address, int zipCode, String city, long phoneNo, String email, String country, double discount, boolean isBusiness)
	{
		super(name, address, zipCode, city, phoneNo, email, country);
		discount_ = discount;
		isBusiness_ = isBusiness;
	}

	public double getDiscount() 
	{ return discount_; }
	
	public void setDiscount(double discount) 
	{ discount_ = discount; }
	
	public boolean isBusiness() 
	{ return isBusiness_; }
	
	public void setBusiness(boolean isBusiness) 
	{ this.isBusiness_ = isBusiness; }
	
}
