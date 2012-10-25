package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import models.Contact;
import models.Supplier;

public class DBSupplier implements IFDBSupplier{

    private DataAccess _da;
    public DBSupplier()
    {
        _da = DataAccess.getInstance();
    }
    
	public ArrayList<Supplier> getAllSuppliers(boolean retrieveAssociation) 
	{
		ArrayList<Supplier> returnList = new ArrayList<Supplier>();
        try
        {
            PreparedStatement query = _da.getCon().prepareStatement("SELECT * FROM Supplier");
            _da.setSqlCommandText(query);
            ResultSet suppliers = _da.callCommandGetResultSet();

            while(suppliers.next())
            {
                Supplier supplier = buildSupplier(suppliers, retrieveAssociation);
                returnList.add(supplier);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return returnList;
	}

	public Supplier getSupplierById(long id, boolean retrieveAssociation)
	{
        try
        {
            PreparedStatement query = _da.getCon().prepareStatement("SELECT * FROM Supplier WHERE supplierId = ?");
            query.setLong(1, id);
            _da.setSqlCommandText(query);
            ResultSet supplierResult = _da.callCommandGetRow();
            supplierResult.next();
            return buildSupplier(supplierResult, true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
	}
	
	public Supplier getSupplierByName(String name, boolean retrieveAssociation)
	{
        try
        {
            PreparedStatement query = _da.getCon().prepareStatement("SELECT * FROM Supplier, Contacts WHERE name = ?");
            query.setString(1, name);
            _da.setSqlCommandText(query);
            ResultSet supplierResult = _da.callCommandGetRow();
            supplierResult.next();
            return buildSupplier(supplierResult, true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
	}

	public int insertSupplier(Supplier supplier) throws Exception
	{
		return 0;
	}

	public int updateSupplier(Supplier supplier)
	{
		return 0;
	}
	
	private Supplier buildSupplier(ResultSet row, boolean retrieveAssociation)
    {
        if(row == null)
            return null;

        try
        {
            long contactsKey = row.getLong("contactsKey");
            String contactPerson = row.getString("contactPerson");
            String bankAccount = row.getString("bankAccount");
            DBContact DBC = new DBContact();
        	Contact contact = DBC.getContactById(contactsKey);
            
            Supplier supplier = new Supplier(contact.getName(), contact.getAddress(), contact.getZipCode(), contact.getCity(), contact.getPhoneNo(), contact.getEmail(), contact.getCountry(), contactPerson, bankAccount);

            return supplier;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }
}
