package views.contact;

import controllers.CustomerCtrl;
import db.DataAccess;
import models.Customer;
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
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created: 27-10-2012
 * @version: 0.1
 * Filename: CustomerCreateUI.java
 * Description:
 * @changes
 */

public class CustomerCreateUI
{
    private static JFrame _frame;
    private static CustomerCreateUI _instance;
    private JPanel contentPane;

    //Controllers
    private CustomerCtrl _cusCtrl;

    private JTextField txtName;
    private JTextField txtAddress;
    private JTextField txtZipCode;
    private JTextField txtCity;
    private JTextField txtPhoneNo;
    private JTextField txtEmail;
    private JTextField txtCountry;
    private JTextField txtDiscount;
    private Checkbox txtBusiness;
    private JComboBox<String> drpCustomers;
    private String[] columnNames;
    private DefaultTableModel model;
    private JTable tblData;

    public static JFrame createWindow()
    {
        if(_instance == null)
            _instance = new CustomerCreateUI();

        return _frame;
    }

    public CustomerCreateUI()
    {
        createElements();
    }

    private void createElements()
    {
        _cusCtrl = new CustomerCtrl();

        _frame = new JFrame();
        _frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        _frame.setTitle("Opret Kunde");
        _frame.setBounds(0, 0, 505, 300);
        _frame.setResizable(false);
        _frame.setVisible(true);
        _frame.setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        _frame.setContentPane(contentPane);

        JLabel lblCustomerName = new JLabel("Navn");
        lblCustomerName.setBounds(12, 12, 120, 15);
        contentPane.add(lblCustomerName);
        
        JLabel lblCustomerAddress = new JLabel("Adresse");
        lblCustomerAddress.setBounds(12, 37, 120, 15);
        contentPane.add(lblCustomerAddress);
        
        JLabel lblCustomerZipCode = new JLabel("Postnummer");
        lblCustomerZipCode.setBounds(12, 62, 120, 15);
        contentPane.add(lblCustomerZipCode);
        
        JLabel lblCustomerCity = new JLabel("By");
        lblCustomerCity.setBounds(12, 87, 120, 15);
        contentPane.add(lblCustomerCity);
        
        JLabel lblCustomerNumber = new JLabel("Telefonnummer");
        lblCustomerNumber.setBounds(12, 112, 120, 15);
        contentPane.add(lblCustomerNumber);
        
        JLabel lblCustomerEmail = new JLabel("Email");
        lblCustomerEmail.setBounds(12, 137, 120, 15);
        contentPane.add(lblCustomerEmail);
        
        JLabel lblCustomerCountry = new JLabel("Land");
        lblCustomerCountry.setBounds(12, 162, 99, 15);
        contentPane.add(lblCustomerCountry);
        
        JLabel lblCustomerContact = new JLabel("Rabat");
        lblCustomerContact.setBounds(12, 187, 120, 15);
        contentPane.add(lblCustomerContact);
        
        JLabel lblCustomerBankAcc = new JLabel("Erhvervskunde");
        lblCustomerBankAcc.setBounds(12, 212, 120, 15);
        contentPane.add(lblCustomerBankAcc);
        
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
        
        txtDiscount = new JTextField();
        txtDiscount.setBounds(142, 185, 350, 19);
        contentPane.add(txtDiscount);
        txtDiscount.setColumns(10);
        
        txtBusiness = new Checkbox();
        txtBusiness.setBounds(142, 210, 350, 19);
        contentPane.add(txtBusiness);
        

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
                createCustomer();
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

    private void createCustomer()
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
            BigDecimal discount = new BigDecimal(txtDiscount.getText());
            boolean isBusiness = txtBusiness.getState();
            Customer customer = new Customer(name, address, zipCode, city, phoneNo, email, country, discount, isBusiness);

            _cusCtrl.insertCustomer(customer);

            JOptionPane.showMessageDialog(null, "Leverandøren er nu oprettet", "INFORMATION!", JOptionPane.INFORMATION_MESSAGE);
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
