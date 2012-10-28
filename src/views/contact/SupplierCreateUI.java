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

    private JTextField txtBankAccount;
    private JTextField txtSupplierNumber;
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
        _frame.setTitle("Opret produkt");
        _frame.setBounds(0, 0, 509, 528);
        _frame.setResizable(false);
        _frame.setVisible(true);
        _frame.setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        _frame.setContentPane(contentPane);

        JLabel lblSupplierName = new JLabel("Leverandør navn");
        lblSupplierName.setBounds(12, 12, 99, 15);
        contentPane.add(lblSupplierName);

        JLabel lblSupplierNumber = new JLabel("Telefonnummer");
        lblSupplierNumber.setBounds(12, 37, 99, 15);
        contentPane.add(lblSupplierNumber);

        txtBankAccount = new JTextField();
        txtBankAccount.setBounds(142, 10, 350, 19);
        contentPane.add(txtBankAccount);
        txtBankAccount.setColumns(10);

        txtSupplierNumber = new JTextField();
        txtSupplierNumber.setDocument(new JTextFieldLimit(2));
        txtSupplierNumber.setBounds(142, 35, 350, 19);
        contentPane.add(txtSupplierNumber);
        txtSupplierNumber.setColumns(10);
        
        
        JSeparator separator = new JSeparator();
        separator.setBounds(12, 226, 480, 1);
        contentPane.add(separator);


        tblData = new JTable()
        {
            public boolean isCellEditable(int data, int columns)
            {
                return columns == 3;
            }
        };
        tblData.setBounds(12, 353, 475, 100);
        model = new DefaultTableModel();

        tblData.setModel(model);
        tblData.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(tblData);
        scrollPane.setSize(481, 105);
        tblData.setPreferredScrollableViewportSize(new Dimension(475, 100));
        scrollPane.setPreferredSize(new Dimension(481, 100));
        scrollPane.setLocation(12, 353);
        contentPane.add(scrollPane);


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
            long phoneNo = da.getNextId("Suppliers");
            String bankAccount = txtBankAccount.getText();
            String contactPerson = txtContactPerson.getText();
            Contact contact = _supCtrl.getContact(phoneNo);
            Supplier supplier = new Supplier(contact.getName(), contact.getAddress(), contact.getZipCode(), contact.getCity(), contact.getPhoneNo(), contact.getEmail(), contact.getCountry(), contactPerson, bankAccount);

            _supCtrl.insertSupplier(supplier);

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
