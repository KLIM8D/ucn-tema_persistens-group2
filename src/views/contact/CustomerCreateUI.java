package views.contact;

import controllers.CustomerCtrl;
import db.DataAccess;
import models.Customer;
import utils.Helper;
import utils.JTextFieldLimit;
import utils.Logging;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;

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
    private Checkbox chkIsBusiness;

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
        _frame.setTitle("Opret ny kunde");
        _frame.setBounds(0,0,509,270);
        _frame.setResizable(false);
        _frame.setVisible(true);
        _frame.setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        contentPane.setLayout(null);
        _frame.setContentPane(contentPane);

        JLabel lblSupplierName = new JLabel("Navn:");
        lblSupplierName.setBounds(12,12,120,15);
        contentPane.add(lblSupplierName);
        
        JLabel lblSupplierAddress = new JLabel("Adresse:");
        lblSupplierAddress.setBounds(12,37,120,15);
        contentPane.add(lblSupplierAddress);
        
        JLabel lblSupplierZipCode = new JLabel("Postnummer:");
        lblSupplierZipCode.setBounds(12,62,120,15);
        contentPane.add(lblSupplierZipCode);
        
        JLabel lblSupplierCity = new JLabel("By");
        lblSupplierCity.setBounds(222,62,120,15);
        contentPane.add(lblSupplierCity);
        
        JLabel lblSupplierNumber = new JLabel("Telefonnummer:");
        lblSupplierNumber.setBounds(12,87,120,15);
        contentPane.add(lblSupplierNumber);
        
        JLabel lblSupplierEmail = new JLabel("Email:");
        lblSupplierEmail.setBounds(244,87,120,15);
        contentPane.add(lblSupplierEmail);
        
        JLabel lblSupplierCountry = new JLabel("Landkode:");
        lblSupplierCountry.setBounds(12,112,120,15);
        contentPane.add(lblSupplierCountry);
        
        JLabel lblCustomerDiscount = new JLabel("Rabat:");
        lblCustomerDiscount.setBounds(12,145,120,15);
        contentPane.add(lblCustomerDiscount);
        
        JLabel lblCustomerDiscountSet = new JLabel("%");
        lblCustomerDiscountSet.setBounds(222,145,120,15);
        contentPane.add(lblCustomerDiscountSet);
        
        JLabel lblCustomerIsBusiness = new JLabel("Erhvervskunde:");
        lblCustomerIsBusiness.setBounds(280,145,120,15);
        contentPane.add(lblCustomerIsBusiness);
        
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
        txtPhoneNo.addKeyListener(new KeyAdapter()
        {
            public void keyReleased(KeyEvent e)
            {
                if(txtPhoneNo.getText().length() > 0)
                    Helper.checkIfLong(txtPhoneNo);
            }
        });
        txtPhoneNo.setBounds(142,85,75,19);
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
        
        txtDiscount = new JTextField();
        txtDiscount.setBounds(142,143,75,19);
        txtDiscount.setText("0.00");
        contentPane.add(txtDiscount);
        txtDiscount.setColumns(10);
        
        chkIsBusiness = new Checkbox();
        chkIsBusiness.setBounds(410,144,20,19);
        contentPane.add(chkIsBusiness);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(12,135,480,1);
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
        btnCancel.setBounds(375,195,117,25);
        contentPane.add(btnCancel);

        JButton btnCreate = new JButton("Opret");
        btnCreate.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                createCustomer();
            }
        });
        btnCreate.setBounds(246,195,117,25);
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
            @SuppressWarnings("unused")
			DataAccess da = DataAccess.getInstance();
            String name = txtName.getText();
            String address = txtAddress.getText();
            long zipCode = Long.parseLong(txtZipCode.getText());
            String city = txtCity.getText();
            long phoneNo = Long.parseLong(txtZipCode.getText());
            String email = txtEmail.getText();
            String country = txtCountry.getText();
            BigDecimal discount = new BigDecimal(txtDiscount.getText());
            boolean isBusiness = chkIsBusiness.getState();
            Customer customer = new Customer(name, address, zipCode, city, phoneNo, email, country, discount, isBusiness);

            _cusCtrl.insertCustomer(customer);

            JOptionPane.showMessageDialog(null, "Kunden er nu oprettet", "INFORMATION!", JOptionPane.INFORMATION_MESSAGE);
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
