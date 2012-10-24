package models;

/**
 * Created: 24-10-2012
 * @version: 0.1
 * Filename: Contacts.java
 * Description:
 * @changes
 */

public class Contacts
{
    private String _name;
    private String _address;
    private int _zipCode;
    private String _city;
    private long _phoneNo;
    private String _email;
    private String _country;

    public String getName()
    { return _name; }
    public void setName(String value)
    { _name = value; }

    public String getAddress()
    { return _address; }
    public void setAddress(String value)
    { _address = value; }

    public int getZipCode()
    { return _zipCode; }
    public void setZipCode(int value)
    { _zipCode = value; }

    public String getCity()
    { return _city; }
    public void setCity(String value)
    { _city = value; }

    public long getPhoneNo()
    { return _phoneNo; }
    public void setPhoneNo(long value)
    { _phoneNo = value; }

    public String getEmail()
    { return _email; }
    public void setEmail(String value)
    { _email = value; }

    public String getCountry()
    { return _country; }
    public void setCountry(String value)
    { _country = value; }

    public Contacts(String name, String address, int zipCode, String city, long phoneNo, String email, String country)
    {
        _name = name;
        _address = address;
        _zipCode = zipCode;
        _city = city;
        _phoneNo = phoneNo;
        _email = email;
        _country = country;
    }
}
