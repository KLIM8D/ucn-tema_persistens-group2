package views.contact;

import controllers.SupplierCtrl;
import models.Supplier;
import utils.Helper;
import utils.JTextFieldLimit;
import utils.Logging;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;


public class SupplierEditUI
{
    private static JFrame _frame;
    private static SupplierEditUI _instance;
    private JPanel contentPane;

    //Controllers
    private SupplierCtrl _suppCtrl;
    private long _phoneNo;

    private JTextField txtName;
    private JTextField txtAddress;
    private JTextField txtZipCode;
    private JTextField txtCity;
    private JTextField txtPhoneNo;
    private JTextField txtEmail;
    private JTextField txtCountry;
    private JTextField txtBankAccount;
    private JTextField txtContactPerson;

    public static JFrame createWindow(long data)
    {
        if(_instance == null)
            _instance = new SupplierEditUI(data);

        return _frame;
    }

    public SupplierEditUI(long data)
    {
    	_phoneNo = data;
        createElements();
    }

    private void createElements()
    {
        _suppCtrl = new SupplierCtrl();

        _frame = new JFrame();
        _frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        _frame.setTitle("Rediger leverandør");
        _frame.setBounds(0,0,509,295);
        _frame.setResizable(false);
        _frame.setVisible(true);
        _frame.setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        contentPane.setLayout(null);
        _frame.setContentPane(contentPane);

        JLabel lblSupplierName = new JLabel("Navn");
        lblSupplierName.setBounds(12,12,120,15);
        contentPane.add(lblSupplierName);
        
        JLabel lblSupplierAddress = new JLabel("Adresse");
        lblSupplierAddress.setBounds(12,37,120,15);
        contentPane.add(lblSupplierAddress);
        
        JLabel lblSupplierZipCode = new JLabel("Postnummer");
        lblSupplierZipCode.setBounds(12,62,120,15);
        contentPane.add(lblSupplierZipCode);
        
        JLabel lblSupplierCity = new JLabel("By");
        lblSupplierCity.setBounds(222,62,120,15);
        contentPane.add(lblSupplierCity);
        
        JLabel lblSupplierNumber = new JLabel("Telefonnummer");
        lblSupplierNumber.setBounds(12,87,120,15);
        contentPane.add(lblSupplierNumber);
        
        JLabel lblSupplierEmail = new JLabel("Email");
        lblSupplierEmail.setBounds(244,87,120,15);
        contentPane.add(lblSupplierEmail);
        
        JLabel lblSupplierCountry = new JLabel("Landkode");
        lblSupplierCountry.setBounds(12,112,120,15);
        contentPane.add(lblSupplierCountry);
        
        JLabel lblSupplierContact = new JLabel("Kontakt Person");
        lblSupplierContact.setBounds(12,145,120,15);
        contentPane.add(lblSupplierContact);
        
        JLabel lblSupplierBankAcc = new JLabel("Bankkonto");
        lblSupplierBankAcc.setBounds(12,170,120,15);
        contentPane.add(lblSupplierBankAcc);
        
        txtName = new JTextField();
        txtName.setBounds(142,10,350,19);
        contentPane.add(txtName);
        txtName.setColumns(10);

        txtAddress = new JTextField();
        txtAddress.setBounds(142,35,350,19);
        contentPane.add(txtAddress);
        txtAddress.setColumns(10);
        
        txtZipCode = new JTextField();
        txtZipCode.addKeyListener(new KeyAdapter()
        {
            public void keyReleased(KeyEvent e)
            {
                if(txtZipCode.getText().length() > 0)
                    Helper.checkIfInt(txtZipCode);
            }
        });
        txtZipCode.setDocument(new JTextFieldLimit(4));
        txtZipCode.setBounds(142,60,50,19);
        contentPane.add(txtZipCode);
        txtZipCode.setColumns(10);
        
        txtCity = new JTextField();
        txtCity.setBounds(265,60,227,19);
        contentPane.add(txtCity);
        txtCity.setColumns(10);
        
        txtPhoneNo = new JTextField();
        txtPhoneNo.setBounds(142,85,75,19);
        txtPhoneNo.setEditable(false);
        txtPhoneNo.setDocument(new JTextFieldLimit(8));
        contentPane.add(txtPhoneNo);
        txtPhoneNo.setColumns(10);
        
        txtEmail = new JTextField();
        txtEmail.setBounds(305,85,187,19);
        contentPane.add(txtEmail);
        txtEmail.setColumns(10);
        
        txtCountry = new JTextField();
        txtCountry.setDocument(new JTextFieldLimit(2));
        txtCountry.setBounds(142,110,25,19);
        contentPane.add(txtCountry);
        txtCountry.setColumns(10);
        
        txtContactPerson = new JTextField();
        txtContactPerson.setBounds(142,143,350,19);
        contentPane.add(txtContactPerson);
        txtContactPerson.setColumns(10);
        
        txtBankAccount = new JTextField();
        txtBankAccount.setBounds(142,168,350,19);
        contentPane.add(txtBankAccount);
        txtBankAccount.setColumns(10);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(12, 135, 480, 1);
        contentPane.add(separator);
        
        
        JButton btnCancel = new JButton("Annuller");
        btnCancel.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                _instance = null;
                _frame.dispose();
            }
        });
        btnCancel.setBounds(375,218,117,25);
        contentPane.add(btnCancel);

        JButton btnCreate = new JButton("Opdater");
        btnCreate.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                updateSupplier();
            }
        });
        btnCreate.setBounds(246,218,117,25);
        contentPane.add(btnCreate);
        
        addData(_phoneNo);

        _frame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                _instance = null;
                _frame.dispose();
            }
        });
    }
    
    private void updateSupplier()
    {
    	try
    	{
    		String name = txtName.getText();
    		String address = txtAddress.getText();
    		long zipCode = Long.parseLong(txtZipCode.getText());
    		String city = txtCity.getText();
    		long phoneNo = _phoneNo;
    		String eMail = txtEmail.getText();
    		String country = txtCountry.getText();
    		String contactPerson = txtContactPerson.getText();
    		String bankAccount = txtBankAccount.getText();
    		Supplier supplier = new Supplier(name, address, zipCode, city, phoneNo, eMail, country, contactPerson, bankAccount);
    		
    		_suppCtrl.updateSupplier(supplier);
    		JOptionPane.showMessageDialog(null, "Leverandøren er nu opdateret", "INFORMATION!", JOptionPane.INFORMATION_MESSAGE);
    		_instance = null;
    		_frame.dispose();
    	}
    	catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, Logging.handleException(ex, 2), "Fejl", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void addData(long data)
    {
    	try {
    		Supplier supplier = _suppCtrl.getSupplierById(data, true);
    		if(supplier != null)
    		{
    			txtName.setText(supplier.getName());
    			txtAddress.setText(supplier.getAddress());
    			txtZipCode.setText(String.valueOf(supplier.getZipCode()));
    			txtCity.setText(supplier.getCity());
    			txtPhoneNo.setText(String.valueOf(supplier.getPhoneNo()));
    			txtEmail.setText(supplier.getEmail());
    			txtCountry.setText(supplier.getCountry());
    			txtContactPerson.setText(supplier.getContactPerson());
    			txtBankAccount.setText(supplier.getBankAccount());
    		}
    		else
    		{
    			JOptionPane.showMessageDialog(null, "Der skete en fejl i hentning af produkt information", "Fejl", JOptionPane.WARNING_MESSAGE);
    		}
    	}
    	catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, Logging.handleException(ex, 1), "Fejl", JOptionPane.WARNING_MESSAGE);
        }
    	
    }
}