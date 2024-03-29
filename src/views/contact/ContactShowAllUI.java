package views.contact;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
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

import utils.ButtonColumn;
import utils.Helper;
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
		tabContacts.addTab("Leverand" + "\u00F8" + "rer", null, pnlSuppliers, null);
		pnlSuppliers.setLayout(null);
		
		//Customer top field
		JLabel lblCustPhoneNo = new JLabel("Telefon nummer:");
		lblCustPhoneNo.setBounds(12,14,125,15);
		pnlCustomers.add(lblCustPhoneNo);
		
		final JTextField txtCustPhoneNo = new JTextField();
		txtCustPhoneNo.setBounds(138,12,115,19);
		pnlCustomers.add(txtCustPhoneNo);
		
		JLabel lblCustName = new JLabel("Navn:");
		lblCustName.setBounds(265,14,40,15);
		pnlCustomers.add(lblCustName);
		
		final JTextField txtCustName = new JTextField();
		txtCustName.setBounds(313,12,115,19);
		pnlCustomers.add(txtCustName);
		
		JButton btnSearchCustomer = new JButton("S" + "\u00F8" + "g");
		btnSearchCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtCustPhoneNo.getText().length() > 0)
				{
					long itemNumber = Long.parseLong(txtCustPhoneNo.getText());
					addCustomerData(itemNumber);
				}
				if(txtCustName.getText().length() > 0 && txtCustPhoneNo.getText().length() <= 0)
				{
					addCustomerData(txtCustName.getText());
				}
				if(txtCustName.getText().length() <= 0 && txtCustPhoneNo.getText().length() <= 0)
				{
					addCustomerData();
				}
			}
		});
		btnSearchCustomer.setBounds(439,8,117,25);
		pnlCustomers.add(btnSearchCustomer);
		
		JButton btnAddCustomer = new JButton("Tilf" + "\u00F8" + "j ny kunde");
		btnAddCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerCreateUI.createWindow();
			}
		});
		btnAddCustomer.setBounds(647,8,170,25);
		pnlCustomers.add(btnAddCustomer);
		
		//Supplier top field
		JLabel lblSuppPhoneNo = new JLabel("Telefon nummer:");
		lblSuppPhoneNo.setBounds(12,14,125,15);
		pnlSuppliers.add(lblSuppPhoneNo);
				
		final JTextField txtSuppPhoneNo = new JTextField();
		txtSuppPhoneNo.setBounds(138,12,115,19);
		pnlSuppliers.add(txtSuppPhoneNo);
				
		JLabel lblSuppName = new JLabel("Navn:");
		lblSuppName.setBounds(265,14,40,15);
		pnlSuppliers.add(lblSuppName);
				
		final JTextField txtSuppName = new JTextField();
		txtSuppName.setBounds(313,12,115,19);
		pnlSuppliers.add(txtSuppName);
				
		JButton btnSearchSupplier = new JButton("S" + "\u00F8" + "g");
		btnSearchSupplier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtSuppPhoneNo.getText().length() > 0)
				{
					long itemNumber = Long.parseLong(txtSuppPhoneNo.getText());
					addSupplierData(itemNumber);
				}
				if(txtSuppName.getText().length() > 0 && txtSuppPhoneNo.getText().length() <= 0)
				{
					addSupplierData(txtSuppName.getText());
				}
				if(txtSuppName.getText().length() <= 0 && txtSuppPhoneNo.getText().length() <= 0)
				{
					addSupplierData();
				}
			}
		});
		btnSearchSupplier.setBounds(439,8,117,25);
		pnlSuppliers.add(btnSearchSupplier);
				
		JButton btnAddSupplier = new JButton("Tilf" + "\u00F8" + "j ny leverand" + "\u00F8" + "r");
		btnAddSupplier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SupplierCreateUI.createWindow();
			}
		});
		btnAddSupplier.setBounds(647,8,170,25);
		pnlSuppliers.add(btnAddSupplier);

        // Column names is global
        columnNames = new String[]{"Telefon", "Navn", "Addresse", "By", "E-Mail", "Land", " ", " "};

		//Customer retrieval table
		JPanel custGP = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlCustomers.add(custGP);
	
		custTable = new JTable()
		{
			public boolean isCellEditable(int data, int columns)
			{
				if(columns == 6 || columns == 7)
					return true;
				return false;
			}
		};
		
		custModel = new DefaultTableModel();
		
		custTable.setModel(custModel);
		custTable.setFillsViewportHeight(true);

		JScrollPane custSP = new JScrollPane(custTable);
		custGP.setBounds(new Rectangle(0,40,825,632));
		custTable.setPreferredScrollableViewportSize(new Dimension(815,732));
		custSP.setPreferredSize(new Dimension(815,732));
		custGP.add(custSP);
        addCustomerData();

		//Supplier retrieval table
		JPanel suppGP = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pnlSuppliers.add(suppGP);
		
		suppTable = new JTable()
		{
			public boolean isCellEditable(int data, int columns)
			{
				if(columns == 6 || columns == 7)
					return true;
				return false;
			}
		};
		
		suppModel = new DefaultTableModel();
		
		suppTable.setModel(suppModel);
		suppTable.setFillsViewportHeight(true);
		
		JScrollPane suppSP = new JScrollPane(suppTable);
		suppGP.setBounds(new Rectangle(0,40,825,632));
		suppTable.setPreferredScrollableViewportSize(new Dimension(815,732));
		suppSP.setPreferredSize(new Dimension(815,732));
		suppGP.add(suppSP);
        addSupplierData();
		

	}
	
	private void addButtonsToCustomer(final int columnIndex)
	{
		@SuppressWarnings("serial")
		Action show = new AbstractAction()
		{
			public void actionPerformed(ActionEvent e)
			{
				JTable custTable = (JTable) e.getSource();
				int row = Integer.valueOf(e.getActionCommand());
				long itemNumber = Long.parseLong(custTable.getValueAt(row, 0).toString());
				if(columnIndex == 6)
				{
					CustomerEditUI.createWindow(itemNumber);
				}
				else
				{
					try
					{
						if(Helper.showConfirmDialog("kunde") == 1)
						{
							Customer customer = _custCtrl.getCustomerById(itemNumber);
							_custCtrl.deleteCustomer(customer);
							_custCtrl.deleteContact(itemNumber);
							addCustomerData();
						}
					}
					catch(Exception err)
					{
						JOptionPane.showMessageDialog(null, Logging.handleException(err, 0), "Fejl", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		};
		ButtonColumn buttonColumn = new ButtonColumn(custTable, show, columnIndex);
		buttonColumn.setMnemonic(KeyEvent.VK_D);
	}
	
	private void addButtonsToSupplier(final int columnIndex)
	{
		@SuppressWarnings("serial")
		Action show = new AbstractAction()
		{
			public void actionPerformed(ActionEvent e)
			{
				JTable suppTable = (JTable) e.getSource();
				int row = Integer.valueOf(e.getActionCommand());
				long itemNumber = Long.parseLong(suppTable.getValueAt(row, 0).toString());
				if(columnIndex == 6)
				{
					SupplierEditUI.createWindow(itemNumber);
				}
				else
				{
					try
					{
						if(Helper.showConfirmDialog("leverand" + "\u00F8" + "r") == 1)
						{
							Supplier supplier = _suppCtrl.getSupplierById(itemNumber);
							_suppCtrl.deleteSupplier(supplier);
							_suppCtrl.deleteContact(itemNumber);
							addSupplierData();
						}
					}
					catch(Exception err)
					{
						JOptionPane.showMessageDialog(null, Logging.handleException(err, 0), "Fejl", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		};
		ButtonColumn buttonColumn = new ButtonColumn(suppTable, show, columnIndex);
		buttonColumn.setMnemonic(KeyEvent.VK_D);
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
				Object[] row = new Object[]{cust.getPhoneNo(), cust.getName(), cust.getAddress(), cust.getCity(), cust.getEmail(), cust.getCountry(), "Rediger", "Slet"};
				custModel.addRow(row);
			}
			addButtonsToCustomer(6);
			addButtonsToCustomer(7);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, Logging.handleException(e, 0), "Fejl", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void addCustomerData(long itemNumber)
	{
		try
		{
			Customer cust = _custCtrl.getCustomerById(itemNumber);
			
			if(cust != null)
			{
				Object[][] row = new Object[][]
				{
						{cust.getPhoneNo(), cust.getName(), cust.getAddress(), cust.getCity(), cust.getEmail(), cust.getCountry(), "Rediger", "Slet"}
				};
				custModel.setDataVector(row, columnNames);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Der blev ikke fundet noget kunde, med telefon nummeret: " + itemNumber, "Information", JOptionPane.WARNING_MESSAGE);
			}
			addButtonsToCustomer(6);
			addButtonsToCustomer(7);
		}
		catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, Logging.handleException(e, 0), "Fejl", JOptionPane.WARNING_MESSAGE);
        }
	}
	
	private void addCustomerData(String itemName)
	{
		try
		{
			Customer cust = _custCtrl.getCustomerByName(itemName);
			
			if(cust != null)
			{
				Object[][] row = new Object[][]
				{
						{cust.getPhoneNo(), cust.getName(), cust.getAddress(), cust.getCity(), cust.getEmail(), cust.getCountry(), "Rediger", "Slet"}
				};
				custModel.setDataVector(row, columnNames);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Der blev ikke fundet noget kunde, med navnet: " + itemName, "Information", JOptionPane.WARNING_MESSAGE);
			}
			addButtonsToCustomer(6);
			addButtonsToCustomer(7);
		}
		catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, Logging.handleException(e, 0), "Fejl", JOptionPane.WARNING_MESSAGE);
        }
	}
	
	private void addSupplierData()
	{
		try
		{
			ArrayList<Supplier> suppliers = _suppCtrl.getAllSuppliers();
			Object[][] data = {};
			suppModel.setDataVector(data, columnNames);
			for(Supplier supp : suppliers)
			{
				Object[] row = new Object[]{supp.getPhoneNo(), supp.getName(), supp.getAddress(), supp.getCity(), supp.getEmail(), supp.getCountry(), "Rediger", "Slet"};
				suppModel.addRow(row);
			}
			addButtonsToSupplier(6);
			addButtonsToSupplier(7);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, Logging.handleException(e, 0), "Fejl", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void addSupplierData(long itemNumber)
	{
		try
		{
			Supplier supp = _suppCtrl.getSupplierById(itemNumber);
			
			if(supp != null)
			{
				Object[][] row = new Object[][]
				{
						{supp.getPhoneNo(), supp.getName(), supp.getAddress(), supp.getCity(), supp.getEmail(), supp.getCountry(), "Rediger", "Slet"}
				};
				suppModel.setDataVector(row, columnNames);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Der blev ikke fundet noget leverand" + "\u00F8" + "r, med telefon nummeret: " + itemNumber, "Information", JOptionPane.WARNING_MESSAGE);
			}
			addButtonsToSupplier(6);
			addButtonsToSupplier(7);
		}
		catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, Logging.handleException(e, 0), "Fejl", JOptionPane.WARNING_MESSAGE);
        }
	}
	
	private void addSupplierData(String itemName)
	{
		try
		{
			Supplier supp = _suppCtrl.getSupplierByName(itemName);
			
			if(supp != null)
			{
				Object[][] row = new Object[][]
				{
						{supp.getPhoneNo(), supp.getName(), supp.getAddress(), supp.getCity(), supp.getEmail(), supp.getCountry(), "Rediger", "Slet"}
				};
				suppModel.setDataVector(row, columnNames);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Der blev ikke fundet noget leverand" + "\u00F8" + "r, med navnet: " + itemName, "Information", JOptionPane.WARNING_MESSAGE);
			}
			addButtonsToSupplier(6);
			addButtonsToSupplier(7);
		}
		catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, Logging.handleException(e, 0), "Fejl", JOptionPane.WARNING_MESSAGE);
        }
	}
}