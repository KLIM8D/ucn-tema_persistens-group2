package models;

public class Supplier extends Contacts {

	private String contactPerson_;
	private String bankAccount_;
	
	public Supplier(String name, String address, int zipCode, String city, long phoneNo, String email, String country, String contactPerson, String bankAccount)
	{
		super(name, address, zipCode, city, phoneNo, email, country);
		contactPerson_ = contactPerson;
		bankAccount_ = bankAccount;
	}

	public String getContactPerson() 
	{ return contactPerson_; }

	public void setContactPerson(String contactPerson)
	{ contactPerson_ = contactPerson; }

	public String getBankAccount() 
	{ return bankAccount_; }

	public void setBankAccount(String bankAccount) 
	{ bankAccount_ = bankAccount; }
	
}
