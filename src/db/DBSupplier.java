package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import models.Contact;
import models.Supplier;

public class DBSupplier implements IFDBSupplier
{

    private DataAccess _da;
    public DBSupplier()
    {
        _da = DataAccess.getInstance();
    }

     /**
     * Get all the suppliers from the database
     *
     * @return ArrayList<Supplier>
     *
     */
    @Override
	public ArrayList<Supplier> getAllSuppliers() throws Exception
	{
		ArrayList<Supplier> returnList = new ArrayList<Supplier>();

		PreparedStatement query = _da.getCon().prepareStatement("SELECT * FROM Supplier");
		_da.setSqlCommandText(query);
		ResultSet suppliers = _da.callCommandGetResultSet();
		
		while(suppliers.next())
		{
			Supplier supplier = buildSupplier(suppliers);
			returnList.add(supplier);
		}

        return returnList;
	}

     /**
     * Get a specific supplier by supplier id
     *
     * @param id the ID of the supplier you want returned
     * @return Supplier
     *
     */
    @Override
	public Supplier getSupplierById(long id) throws Exception
	{
        PreparedStatement query = _da.getCon().prepareStatement("SELECT * FROM Supplier WHERE contactsKey = ?");
        query.setLong(1, id);
        _da.setSqlCommandText(query);
        ResultSet supplierResult = _da.callCommandGetRow();
        supplierResult.next();

        return buildSupplier(supplierResult);
	}

     /**
     * Get a specific supplier by supplier name
     *
     * @param name the name of the supplier you want returned
     * @return Supplier
     *
     */
    @Override
	public Supplier getSupplierByName(String name) throws Exception
	{
        PreparedStatement query = _da.getCon().prepareStatement("SELECT * FROM Supplier, Contacts WHERE Contacts.name = ? AND phoneNo = contactsKey");
        query.setString(1, name);
        _da.setSqlCommandText(query);
        ResultSet supplierResult = _da.callCommandGetRow();
        supplierResult.next();

        return buildSupplier(supplierResult);
	}

     /**
     * Insert a new supplier to the database
     *
     * @param supplier the supplier object with the data you want added
     * @return int returns the number of rows affected
     *
     */
    @Override
	public int insertSupplier(Supplier supplier) throws Exception
	{
        if(supplier == null)
            return 0;

        PreparedStatement query = _da.getCon().prepareStatement("INSERT INTO Supplier (contactsKey, contactPerson, bankAccount) " +
                                                                "VALUES (?, ?, ?)");

        query.setLong(1, supplier.getPhoneNo());
        query.setString(2, supplier.getContactPerson());
        query.setString(3, supplier.getBankAccount());
        _da.setSqlCommandText(query);

        return _da.callCommand();
	}

     /**
     * Update a supplier, that already exists in the database
     *
     * @param supplier the supplier object which contains a valid ID and the new data which should be updated
     * @return int returns the number of rows affected
     *
     */
    @Override
	public int updateSupplier(Supplier supplier) throws Exception
	{
        if(supplier == null)
            return 0;

        if(getSupplierById(supplier.getPhoneNo()) == null)
            return 0;

        PreparedStatement query = _da.getCon().prepareStatement("UPDATE Supplier SET contactsKey = ?, contactPerson = ?, bankAccount = ? " +
                                                                "WHERE contactsKey = ?");

        query.setLong(1, supplier.getPhoneNo());
        query.setString(2, supplier.getContactPerson());
        query.setString(3, supplier.getBankAccount());
        query.setLong(4, supplier.getPhoneNo());
        _da.setSqlCommandText(query);

        return _da.callCommand();
	}

     /**
     * Delete a supplier, that already exists in the database
     *
     * @param supplier the supplier object which contains a valid ID
     * @return int returns the number of rows affected
     *
     */
    @Override
    public int deleteSupplier(Supplier supplier) throws Exception
    {
        if(supplier == null)
            return 0;
        
        if(getSupplierById(supplier.getPhoneNo()) == null)
			 return 0;

        int rowsAffected = 0;
        PreparedStatement query = _da.getCon().prepareStatement("DELETE FROM Supplier WHERE contactsKey = ?");
        query.setLong(1, supplier.getPhoneNo());
        _da.setSqlCommandText(query);
        rowsAffected += _da.callCommand();

        DBContact DBCo = new DBContact();
        rowsAffected += DBCo.deleteContact(supplier.getPhoneNo());

        return rowsAffected;
    }
	
	private Supplier buildSupplier(ResultSet row) throws Exception
    {
        if(row == null)
            return null;

        long contactsKey = row.getLong("contactsKey");
        String contactPerson = row.getString("contactPerson");
        String bankAccount = row.getString("bankAccount");
        DBContact DBC = new DBContact();
        Contact contact = DBC.getContactById(contactsKey);

        return new Supplier(contact.getName(), contact.getAddress(), contact.getZipCode(), contact.getCity(), contact.getPhoneNo(), contact.getEmail(), contact.getCountry(), contactPerson, bankAccount);
    }
}
