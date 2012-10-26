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
    
	public ArrayList<Supplier> getAllSuppliers(boolean retrieveAssociation) throws Exception
	{
		ArrayList<Supplier> returnList = new ArrayList<Supplier>();

		PreparedStatement query = _da.getCon().prepareStatement("SELECT * FROM Supplier");
		_da.setSqlCommandText(query);
		ResultSet suppliers = _da.callCommandGetResultSet();
		
		while(suppliers.next())
		{
			Supplier supplier = buildSupplier(suppliers, retrieveAssociation);
			returnList.add(supplier);
		}

        return returnList;
	}
	
	public Supplier getSupplierById(long id, boolean retrieveAssociation) throws Exception
	{
            PreparedStatement query = _da.getCon().prepareStatement("SELECT * FROM Supplier WHERE supplierId = ?");
            query.setLong(1, id);
            _da.setSqlCommandText(query);
            ResultSet supplierResult = _da.callCommandGetRow();
            supplierResult.next();
            return buildSupplier(supplierResult, true);
	}
	
	public Supplier getSupplierByName(String name, boolean retrieveAssociation) throws Exception
	{
            PreparedStatement query = _da.getCon().prepareStatement("SELECT * FROM Supplier, Contacts WHERE name = ?");
            query.setString(1, name);
            _da.setSqlCommandText(query);
            ResultSet supplierResult = _da.callCommandGetRow();
            supplierResult.next();
            return buildSupplier(supplierResult, true);
	}

	public int insertSupplier(Supplier supplier) throws Exception
	{
        if(supplier == null)
            return 0;

            PreparedStatement query = _da.getCon().prepareStatement("INSERT INTO Suppliers (contactsKey, contactPerson, bankAccount) " +
                                                                    "VALUES (?, ?, ?)");

            query.setLong(1, supplier.getPhoneNo());
            query.setString(2, supplier.getContactPerson());
            query.setString(3, supplier.getBankAccount());
            
            _da.setSqlCommandText(query);
            return _da.callCommand();
	}

	public int updateSupplier(Supplier supplier) throws Exception
	{
        if(supplier == null)
            return 0;

            if(getSupplierById(supplier.getPhoneNo(), true) == null)
                return 0;

            PreparedStatement query = _da.getCon().prepareStatement("UPDATE Suppliers SET contactsKey = ?, contantPerson = ?, bankAccount = ? " +
                                                                    "WHERE productId = ?");

            query.setLong(1, supplier.getPhoneNo());
            query.setString(2, supplier.getContactPerson());
            query.setString(3, supplier.getBankAccount());

            _da.setSqlCommandText(query);
            return _da.callCommand();
	}
	
	private Supplier buildSupplier(ResultSet row, boolean retrieveAssociation) throws Exception
    {
        if(row == null)
            return null;

            long contactsKey = row.getLong("contactsKey");
            String contactPerson = row.getString("contactPerson");
            String bankAccount = row.getString("bankAccount");
            DBContact DBC = new DBContact();
        	Contact contact = DBC.getContactById(contactsKey);
            
            Supplier supplier = new Supplier(contact.getName(), contact.getAddress(), contact.getZipCode(), contact.getCity(), contact.getPhoneNo(), contact.getEmail(), contact.getCountry(), contactPerson, bankAccount);

            return supplier;
    }
}
