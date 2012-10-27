package views.contact;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import utils.Logging;

import models.Customer;
import models.Supplier;

import controllers.CustomerCtrl;
import controllers.SupplierCtrl;

public class ContactShowAllUI {
	
	private static JPanel _panel;
	
	//Controllers
	private CustomerCtrl _custCtrl;
	private SupplierCtrl _suppCtrl;
	
	//Grid
    private DefaultTableModel custModel;
    private JTable custTable;
    private DefaultTableModel suppModel;
    private JTable suppTable;
    private String[] columnNames;
	
	public JPanel createWindow()
    {
        createElements();
        return _panel;
    }
	
	@SuppressWarnings("serial")
	private void createElements()
	{
		_custCtrl = new CustomerCtrl();
		_suppCtrl = new SupplierCtrl();
		_panel = new JPanel();
		_panel.setBorder(new EmptyBorder(0,0,0,0));
		_panel.setVisible(true);
		_panel.setBounds(0,0,829,705);
		_panel.setLayout(new GridLayout(1,0,0,0));
		
		//Tabbed panel
		JTabbedPane tabContacts = new JTabbedPane(JTabbedPane.TOP);
		_panel.add(tabContacts);
		
		JPanel pnlCustomers = new JPanel();
		tabContacts.addTab("Kunder", null, pnlCustomers, null);
		pnlCustomers.setLayout(null);
		
		JPanel pnlSuppliers = new JPanel();
		tabContacts.addTab("Leverandører", null, pnlSuppliers, null);
		pnlSuppliers.setLayout(null);
		
		//Customer top field
		JLabel lblCustPhoneNo = new JLabel("Telefon nummer:");
		lblCustPhoneNo.setBounds(12,14,125,15);
		pnlCustomers.add(lblCustPhoneNo);
		
		JTextField txtCustPhoneNo = new JTextField();
		txtCustPhoneNo.setBounds(138,12,115,19);
		pnlCustomers.add(txtCustPhoneNo);
		
		JLabel lblCustName = new JLabel("Navn:");
		lblCustName.setBounds(265,14,40,15);
		pnlCustomers.add(lblCustName);
		
		JTextField txtCustName = new JTextField();
		txtCustName.setBounds(313,12,115,19);
		pnlCustomers.add(txtCustName);
		
		JButton btnSearchCustomer = new JButton("Søg");
		btnSearchCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSearchCustomer.setBounds(439,8,117,25);
		pnlCustomers.add(btnSearchCustomer);
		
		JButton btnAddCustomer = new JButton("Tilføj ny kunde");
		btnAddCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAddCustomer.setBounds(647,8,170,25);
		pnlCustomers.add(btnAddCustomer);
		
		//Supplier top field
		JLabel lblSuppPhoneNo = new JLabel("Telefon nummer:");
		lblSuppPhoneNo.setBounds(12,14,125,15);
		pnlSuppliers.add(lblSuppPhoneNo);
				
		JTextField txtSuppPhoneNo = new JTextField();
		txtSuppPhoneNo.setBounds(138,12,115,19);
		pnlSuppliers.add(txtSuppPhoneNo);
				
		JLabel lblSuppName = new JLabel("Navn:");
		lblSuppName.setBounds(265,14,40,15);
		pnlSuppliers.add(lblSuppName);
				
		JTextField txtSuppName = new JTextField();
		txtSuppName.setBounds(313,12,115,19);
		pnlSuppliers.add(txtSuppName);
				
		JButton btnSearchSupplier = new JButton("Søg");
		btnSearchSupplier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSearchSupplier.setBounds(439,8,117,25);
		pnlSuppliers.add(btnSearchSupplier);
				
		JButton btnAddSupplier = new JButton("Tilføj ny leverandør");
		btnAddSupplier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAddSupplier.setBounds(647,8,170,25);
		pnlSuppliers.add(btnAddSupplier);
		
		//Customer retrieval table
		JPanel custGP = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlCustomers.add(custGP);
	
		custTable = new JTable()
		{
			public boolean isCellEditable(int data, int columns)
			{
				return columns == 6;
			}
		};
		
		custModel = new DefaultTableModel();
		
		custTable.setModel(custModel);
		custTable.setFillsViewportHeight(true);
		addCustomerData();
		
		JScrollPane custSP = new JScrollPane(custTable);
		custGP.setBounds(new Rectangle(0,40,825,632));
		custTable.setPreferredScrollableViewportSize(new Dimension(815,732));
		custSP.setPreferredSize(new Dimension(815,732));
		custGP.add(custSP);
		
		//Supplier retrieval table
		JPanel suppGP = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlSuppliers.add(suppGP);
		
		suppTable = new JTable()
		{
			public boolean isCellEditable(int data, int columns)
			{
				return columns == 6;
			}
		};
		
		suppModel = new DefaultTableModel();
		
		suppTable.setModel(suppModel);
		suppTable.setFillsViewportHeight(true);
		addSupplierData();
		
		JScrollPane suppSP = new JScrollPane(suppTable);
		suppGP.setBounds(new Rectangle(0,40,825,632));
		suppTable.setPreferredScrollableViewportSize(new Dimension(815,732));
		suppSP.setPreferredSize(new Dimension(815,732));
		suppGP.add(suppSP);
		
		// Column names is global
		columnNames = new String[]{"Telefon", "Navn", "Addresse", "By", "E-Mail", "Land"};
	}
	
	private void addCustomerData()
	{
		try
		{
			ArrayList<Customer> customers = _custCtrl.getAllCustomers();
			Object[][] data = {};
			custModel.setDataVector(data, columnNames);
			for(Customer cust : customers)
			{
				Object[] row = new Object[]{cust.getPhoneNo(), cust.getName(), cust.getAddress(), cust.getCity(), cust.getEmail(), cust.getCountry()};
				custModel.addRow(row);
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, Logging.handleException(e, 0), "Fejl", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void addSupplierData()
	{
		try
		{
			ArrayList<Supplier> suppliers = _suppCtrl.getAllSuppliers(true);
			Object[][] data = {};
			suppModel.setDataVector(data, columnNames);
			for(Supplier supp : suppliers)
			{
				Object[] row = new Object[]{supp.getPhoneNo(), supp.getName(), supp.getAddress(), supp.getCity(), supp.getEmail(), supp.getCountry()};
				suppModel.addRow(row);
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, Logging.handleException(e, 0), "Fejl", JOptionPane.WARNING_MESSAGE);
		}
	}
}