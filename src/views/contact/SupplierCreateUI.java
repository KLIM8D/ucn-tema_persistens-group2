package views.contact;

import controllers.SupplierCtrl;
import db.DataAccess;
import models.Supplier;
import models.Contact;
import utils.ButtonColumn;
import utils.Helper;
import utils.JTextFieldLimit;
import utils.Logging;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Created: 27-10-2012
 * @version: 0.1
 * Filename: SupplierCreateUI.java
 * Description:
 * @changes
 */

public class SupplierCreateUI
{
    private static JFrame _frame;
    private static SupplierCreateUI _instance;
    private JPanel contentPane;

    //Controllers
    private SupplierCtrl _supCtrl;

    private JTextField txtName;
    private JTextField txtAddress;
    private JTextField txtZipCode;
    private JTextField txtCity;
    private JTextField txtPhoneNo;
    private JTextField txtEmail;
    private JTextField txtCountry;
    private JTextField txtBankAccount;
    private JTextField txtContactPerson;
    private JComboBox<String> drpSuppliers;
    private String[] columnNames;
    private DefaultTableModel model;
    private JTable tblData;

    public static JFrame createWindow()
    {
        if(_instance == null)
            _instance = new SupplierCreateUI();

        return _frame;
    }

    public SupplierCreateUI()
    {
        createElements();
    }

    private void createElements()
    {
        _supCtrl = new SupplierCtrl();

        _frame = new JFrame();
        _frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        _frame.setTitle("Opret Leverandør");
        _frame.setBounds(0, 0, 505, 300);
        _frame.setResizable(false);
        _frame.setVisible(true);
        _frame.setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        _frame.setContentPane(contentPane);

        JLabel lblSupplierName = new JLabel("Navn");
        lblSupplierName.setBounds(12, 12, 120, 15);
        contentPane.add(lblSupplierName);
        
        JLabel lblSupplierAddress = new JLabel("Adresse");
        lblSupplierAddress.setBounds(12, 37, 120, 15);
        contentPane.add(lblSupplierAddress);
        
        JLabel lblSupplierZipCode = new JLabel("Postnummer");
        lblSupplierZipCode.setBounds(12, 62, 120, 15);
        contentPane.add(lblSupplierZipCode);
        
        JLabel lblSupplierCity = new JLabel("By");
        lblSupplierCity.setBounds(12, 87, 120, 15);
        contentPane.add(lblSupplierCity);
        
        JLabel lblSupplierNumber = new JLabel("Telefonnummer");
        lblSupplierNumber.setBounds(12, 112, 120, 15);
        contentPane.add(lblSupplierNumber);
        
        JLabel lblSupplierEmail = new JLabel("Email");
        lblSupplierEmail.setBounds(12, 137, 120, 15);
        contentPane.add(lblSupplierEmail);
        
        JLabel lblSupplierCountry = new JLabel("Land");
        lblSupplierCountry.setBounds(12, 162, 120, 15);
        contentPane.add(lblSupplierCountry);
        
        JLabel lblSupplierContact = new JLabel("Kontakt Person");
        lblSupplierContact.setBounds(12, 187, 120, 15);
        contentPane.add(lblSupplierContact);
        
        JLabel lblSupplierBankAcc = new JLabel("Bankkonto");
        lblSupplierBankAcc.setBounds(12, 212, 120, 15);
        contentPane.add(lblSupplierBankAcc);
        
        txtName = new JTextField();
        txtName.setBounds(142, 10, 350, 19);
        contentPane.add(txtName);
        txtName.setColumns(10);

        txtAddress = new JTextField();
        txtAddress.setBounds(142, 35, 350, 19);
        contentPane.add(txtAddress);
        txtAddress.setColumns(10);
        
        txtZipCode = new JTextField();
        txtZipCode.setBounds(142, 60, 350, 19);
        contentPane.add(txtZipCode);
        txtZipCode.setColumns(10);
        
        txtCity = new JTextField();
        txtCity.setBounds(142, 85, 350, 19);
        contentPane.add(txtCity);
        txtCity.setColumns(10);
        
        txtPhoneNo = new JTextField();
        txtPhoneNo.setBounds(142, 110, 350, 19);
        contentPane.add(txtPhoneNo);
        txtPhoneNo.setColumns(10);
        
        txtEmail = new JTextField();
        txtEmail.setBounds(142, 135, 350, 19);
        contentPane.add(txtEmail);
        txtEmail.setColumns(10);
        
        txtCountry = new JTextField();
        txtCountry.setDocument(new JTextFieldLimit(2));
        txtCountry.setBounds(142, 160, 350, 19);
        contentPane.add(txtCountry);
        txtCountry.setColumns(10);
        
        txtBankAccount = new JTextField();
        txtBankAccount.setBounds(142, 185, 350, 19);
        contentPane.add(txtBankAccount);
        txtBankAccount.setColumns(10);
        
        txtContactPerson = new JTextField();
        txtContactPerson.setBounds(142, 210, 350, 19);
        contentPane.add(txtContactPerson);
        txtContactPerson.setColumns(10);
        
        
        JButton btnCancel = new JButton("Annuller");
        btnCancel.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                _instance = null;
                _frame.dispose();
            }
        });
        btnCancel.setBounds(375, 470, 117, 25);
        contentPane.add(btnCancel);

        JButton btnCreate = new JButton("Opret");
        btnCreate.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                createSupplier();
            }
        });
        btnCreate.setBounds(246, 470, 117, 25);
        contentPane.add(btnCreate);

        _frame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                _instance = null;
                _frame.dispose();
            }
        });
    }

    private void createSupplier()
    {
        try
        {
            DataAccess da = DataAccess.getInstance();
            String name = txtName.getText();
            String address = txtAddress.getText();
            long zipCode = Long.parseLong(txtZipCode.getText());
            String city = txtCity.getText();
            long phoneNo = Long.parseLong(txtZipCode.getText());
            String email = txtEmail.getText();
            String country = txtCountry.getText();
            String contactPerson = txtContactPerson.getText();
            String bankAccount = txtBankAccount.getText();
            Supplier supplier = new Supplier(name, address, zipCode, city, phoneNo, email, country, contactPerson, bankAccount);

            _supCtrl.insertSupplier(supplier);

            JOptionPane.showMessageDialog(null, "LeverandÃ¸ren er nu oprettet", "INFORMATION!", JOptionPane.INFORMATION_MESSAGE);
            _instance = null;
            _frame.dispose();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, Logging.handleException(e, 1), "Fejl", JOptionPane.WARNING_MESSAGE);
        }
    }
}
