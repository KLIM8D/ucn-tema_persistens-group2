package models;

public class Supplier extends Contact
{

	private String _contactPerson;
	private String _bankAccount;
	
	public Supplier(String name, String address, long zipCode, String city, long phoneNo, String email, String country, String contactPerson, String bankAccount)
	{
		super(name, address, zipCode, city, phoneNo, email, country);
		_contactPerson = contactPerson;
		_bankAccount = bankAccount;
	}

	public String getContactPerson() 
	{ return _contactPerson; }
	public void setContactPerson(String contactPerson)
	{ _contactPerson = contactPerson; }

	public String getBankAccount() 
	{ return _bankAccount; }
	public void setBankAccount(String bankAccount) 
	{ _bankAccount = bankAccount; }
	
}
