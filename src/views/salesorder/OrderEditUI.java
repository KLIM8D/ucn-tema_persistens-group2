package views.salesorder;

import controllers.CustomerCtrl;
import controllers.DeliveryStatusCtrl;
import controllers.ProductCtrl;
import controllers.SalesOrderCtrl;
import db.DataAccess;
import models.Customer;
import models.DeliveryStatus;
import models.OrderItems;
import models.Product;
import models.SalesOrder;
import utils.Helper;
import utils.Logging;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Date;

public class OrderEditUI
{

	private static JFrame _frame;
	private static OrderEditUI _instance;
	private JPanel contentPane;
	private JTextField txtPhoneNo;
	private JTextField txtName;
	private JTextField txtAddress;
	private JTextField txtZipCode;
	private JTextField txtCity;
    private JTextField txtDiscount;
    private JTextField txtOrderDate;
    private JTextField txtDeliveryDate;
	private JSeparator separator_1;
	private JLabel lblOrderStatus;
	private JComboBox<String> drpOrderStatus;
	private JTextField txtIsBusiness;
	private JLabel lblIsBusiness;
	private JTable tblOrder;
	private JButton btnPerform;
	private JTextField txtTotal;
	private JLabel lblCurrency_03;
	private JLabel lblCurrency_02;
	private JLabel lblCurrency_01;
	private JTextField txtTax;
	private JTextField txtCalcPrice;
	private JLabel lblCalcPrice;
	private JLabel lblTotal;
	private JLabel lblTax;
	private JTextField txtItemnumber;
	private JTextField txtQuantity;
	private JLabel lblItemnumber;
	private JLabel lblQuantity;
	private JButton btnAddorderline;
	private String[] columnNames;
	private JScrollPane scrollPane;
	private DefaultTableModel model;
	
	//Controllers
	private CustomerCtrl _customerCtrl;
	private SalesOrderCtrl _saCtrl;
    private ProductCtrl _productCtrl;
    private DeliveryStatusCtrl _statusCtrl;
	
	//orderLines
	private ArrayList<OrderItems> _orderLines;
	private double _totalPrice;
	public static JFrame createWindow(long orderId)
	{
		if(_instance == null)
			_instance = new OrderEditUI(orderId);
		
		return _frame;
	}
	
	@SuppressWarnings("serial")
	private OrderEditUI(long orderId)
	{
		long _orderId = orderId;
		_customerCtrl = new CustomerCtrl();
        _saCtrl = new SalesOrderCtrl();
        _productCtrl = new ProductCtrl();
        _statusCtrl = new DeliveryStatusCtrl();
		_orderLines = new ArrayList<OrderItems>();

		_frame = new JFrame();

		_frame.setTitle("Rediger ordre");
		_frame.setVisible(true);
		_frame.getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT));
		_frame.setSize(new Dimension(800, 600));
        _frame.setLocationRelativeTo(null);
		_frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        _frame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                _instance = null;
                _frame.dispose();
            }
        });
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		_frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPhoneNo = new JLabel("Telefon");
		lblPhoneNo.setBounds(12, 12, 70, 15);
		contentPane.add(lblPhoneNo);
		
		JLabel lblName = new JLabel("Navn");
		lblName.setBounds(12, 36, 70, 15);
		contentPane.add(lblName);
		
		JLabel lblAddress = new JLabel("Adresse");
		lblAddress.setBounds(12, 60, 70, 15);
		contentPane.add(lblAddress);
		
		JLabel lblCity = new JLabel("By");
		lblCity.setBounds(12, 84, 70, 15);
		contentPane.add(lblCity);
		
		txtPhoneNo = new JTextField();
		txtPhoneNo.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(txtPhoneNo.getText().length() > 0)
				{				
					Helper.checkIfLong(txtPhoneNo);
				}
			}
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					long data = Long.parseLong(txtPhoneNo.getText());
					getCustomerInfo(data);
				}
			}
		});
		txtPhoneNo.setBounds(78, 10, 198, 17);
		txtPhoneNo.setEnabled(false);
		txtPhoneNo.setEditable(false);
		contentPane.add(txtPhoneNo);
		txtPhoneNo.setColumns(10);

        JLabel lblDiscount = new JLabel("Rabat sats");
        lblDiscount.setBounds(305, 12, 100, 15);
        contentPane.add(lblDiscount);

        txtDiscount = new JTextField();
        txtDiscount.setEnabled(false);
        txtDiscount.setColumns(10);
        txtDiscount.setBounds(420, 10, 198, 17);
        txtDiscount.setEditable(false);
        contentPane.add(txtDiscount);

        JLabel lblOrderDate = new JLabel("Order dato");
        lblOrderDate.setBounds(305, 35, 100, 15);
        contentPane.add(lblOrderDate);

        txtOrderDate = new JTextField();
        txtOrderDate.setColumns(10);
        txtOrderDate.setBounds(420, 35, 198, 17);
        txtOrderDate.setEditable(false);
        contentPane.add(txtOrderDate);
		
		txtName = new JTextField();
		txtName.setEnabled(false);
		txtName.setColumns(10);
		txtName.setBounds(78, 35, 198, 17);
		txtName.setEditable(false);
		contentPane.add(txtName);

        JLabel lblDeliveryDate = new JLabel("Leveringsdato");
        lblDeliveryDate.setBounds(305, 60, 120, 15);
        contentPane.add(lblDeliveryDate);

        txtDeliveryDate = new JTextField();
        txtDeliveryDate.setColumns(10);
        txtDeliveryDate.setBounds(420, 60, 198, 17);
        contentPane.add(txtDeliveryDate);
		
		txtAddress = new JTextField();
		txtAddress.setEnabled(false);
		txtAddress.setColumns(10);
		txtAddress.setBounds(78, 60, 198, 17);
		txtAddress.setEditable(false);
		contentPane.add(txtAddress);
		
		txtZipCode = new JTextField();
		txtZipCode.setEnabled(false);
		txtZipCode.setColumns(10);
		txtZipCode.setBounds(78, 85, 40, 19);
		txtZipCode.setEditable(false);
		contentPane.add(txtZipCode);
		
		txtCity = new JTextField();
		txtCity.setEnabled(false);
		txtCity.setColumns(10);
		txtCity.setBounds(131, 85, 145, 19);
		txtCity.setEditable(false);
		contentPane.add(txtCity);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 115, 281, 1);
		contentPane.add(separator);
		
		separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(293, 12, 1, 158);
		contentPane.add(separator_1);
		
		lblOrderStatus = new JLabel("OrderStatus");
        lblOrderStatus.setBounds(12, 130, 93, 15);
		contentPane.add(lblOrderStatus);

        addOrderStatuses();
		
		txtIsBusiness = new JTextField();
		txtIsBusiness.setEnabled(false);
		txtIsBusiness.setColumns(10);
		txtIsBusiness.setBounds(117, 153, 159, 17);
		txtIsBusiness.setEditable(false);
		contentPane.add(txtIsBusiness);
		
		lblIsBusiness = new JLabel("Er erhverv");
		lblIsBusiness.setBounds(12, 154, 106, 15);
		contentPane.add(lblIsBusiness);
	
		
		JButton btnCancel = new JButton("Annuller");
		btnCancel.addActionListener(new ActionListener()
        {
			public void actionPerformed(ActionEvent e)
            {
				_instance = null;
				_frame.dispose();
			}
		});
		btnCancel.setBounds(661, 531, 117, 25);
		contentPane.add(btnCancel);
		
		btnPerform = new JButton("Udfør");
		btnPerform.addActionListener(new ActionListener()
        {
			public void actionPerformed(ActionEvent e)
            {
				createOrder();
			}
		});
		btnPerform.setBounds(532, 531, 117, 25);
		contentPane.add(btnPerform);
		
		txtTotal = new JTextField();
		txtTotal.setEnabled(false);
		txtTotal.setColumns(10);
		txtTotal.setBounds(561, 479, 159, 17);
		contentPane.add(txtTotal);
		
		lblCurrency_03 = new JLabel("DKK");
		lblCurrency_03.setBounds(738, 479, 40, 15);
		contentPane.add(lblCurrency_03);
		
		lblCurrency_02 = new JLabel("DKK");
		lblCurrency_02.setBounds(738, 455, 40, 15);
		contentPane.add(lblCurrency_02);
		
		lblCurrency_01 = new JLabel("DKK");
		lblCurrency_01.setBounds(738, 431, 40, 15);
		contentPane.add(lblCurrency_01);
		
		txtTax = new JTextField();
		txtTax.setEnabled(false);
		txtTax.setColumns(10);
		txtTax.setBounds(561, 454, 159, 17);
		contentPane.add(txtTax);
		
		txtCalcPrice = new JTextField();
		txtCalcPrice.setEnabled(false);
		txtCalcPrice.setColumns(10);
		txtCalcPrice.setBounds(561, 429, 159, 17);
		contentPane.add(txtCalcPrice);
		
		lblCalcPrice = new JLabel("Samlet pris");
		lblCalcPrice.setBounds(449, 431, 106, 15);
		contentPane.add(lblCalcPrice);
		
		lblTotal = new JLabel("I alt");
		lblTotal.setBounds(449, 480, 106, 15);
		contentPane.add(lblTotal);
		
		lblTax = new JLabel("Heraf moms");
		lblTax.setBounds(449, 455, 94, 15);
		contentPane.add(lblTax);
		
		txtItemnumber = new JTextField();
		txtItemnumber.addKeyListener(new KeyAdapter()
        {
			public void keyReleased(KeyEvent e)
            {
				if(txtItemnumber.getText().length() > 0)
				{				
					Helper.checkIfLong(txtItemnumber);
				}
			}
		});
		txtItemnumber.setBounds(175, 431, 223, 19);
		contentPane.add(txtItemnumber);
		txtItemnumber.setColumns(10);
		
		txtQuantity = new JTextField();
		txtQuantity.addKeyListener(new KeyAdapter()
        {
			public void keyReleased(KeyEvent e)
            {
				if(txtQuantity.getText().length() > 0)
				{				
					Helper.checkIfInt(txtQuantity);
				}
			}
		});
		txtQuantity.setColumns(10);
		txtQuantity.setBounds(175, 464, 223, 19);
		contentPane.add(txtQuantity);
		
		lblItemnumber = new JLabel("Produkt nummer");
		lblItemnumber.setBounds(12, 435, 145, 15);
		contentPane.add(lblItemnumber);
		
		lblQuantity = new JLabel("Antal");
		lblQuantity.setBounds(12, 466, 70, 15);
		contentPane.add(lblQuantity);
		
		btnAddorderline = new JButton("Tilføj");
		btnAddorderline.addActionListener(new ActionListener()
        {
			public void actionPerformed(ActionEvent e)
            {
				if(txtItemnumber.getText().length() > 0 && txtQuantity.getText().length() > 0)
				{
					long itemNumber = Long.parseLong(txtItemnumber.getText());
					int quantity = Integer.parseInt(txtQuantity.getText());
					createOrderLine(itemNumber, quantity);
					addOrderLineData();
					calcTotalPrice();
					txtItemnumber.setText("");
					txtQuantity.setText("");
				}
			}
		});
		btnAddorderline.setBounds(280, 495, 117, 25);
		contentPane.add(btnAddorderline);
		
		tblOrder = new JTable();
		
		columnNames = new String[]{"Produkt nummer", "Produkt navn", "Antal", "Enheds pris", "Moms", "Samlet pris"};
		
		tblOrder = new JTable()
		{
			public boolean isCellEditable(int data, int columns)
			{
				return false;
			}
		};
		tblOrder.setBounds(12, 182, 766, 237);
		model = new DefaultTableModel();
		
		tblOrder.setModel(model);
		tblOrder.setFillsViewportHeight(true);
		
		scrollPane = new JScrollPane(tblOrder);
		scrollPane.setSize(766, 237);
		tblOrder.setPreferredScrollableViewportSize(new Dimension(766, 237));
    	scrollPane.setPreferredSize(new Dimension(766, 237));
    	scrollPane.setLocation(12, 182);
		contentPane.add(scrollPane);

        	getCustomerInfo(_orderId);
	}

    private void addOrderStatuses()
    {
        try
        {
            ArrayList<DeliveryStatus> statuses = _statusCtrl.getAllDeliveryStatus();
            String[] statusNames = new String[statuses.size()];
            for(int i = 0; i < statuses.size(); i++)
                statusNames[i] = statuses.get(i).getDeliveryState();

            drpOrderStatus = new JComboBox<String>(statusNames);
            drpOrderStatus.setBounds(117, 128, 159, 17);
            contentPane.add(drpOrderStatus);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, Logging.handleException(e, 0), "Fejl", JOptionPane.WARNING_MESSAGE);
        }
    }
	
	private void getCustomerInfo(long orderId)
	{
        try
        {
        	SalesOrder order = _saCtrl.getSalesOrderById(orderId, true);
            Customer cust = order.getCustomer();
            //info with orderlines needed
            if(cust != null)
            {
            	txtPhoneNo.setText(String.valueOf(cust.getPhoneNo()));
                txtName.setText(cust.getName());
                txtAddress.setText(cust.getAddress());
                txtZipCode.setText(cust.getZipCode() + "");
                txtCity.setText(cust.getCity());
                if(cust.getIsBusiness())
                    txtIsBusiness.setText("Ja");
                else
                    txtIsBusiness.setText("Nej");

                txtDiscount.setText(cust.getDiscount().doubleValue() + "%");

                DataAccess da = DataAccess.getInstance();
                Date orderDate = new Date();
                String fOrderDate = da.dateToSqlDate(orderDate);
                txtOrderDate.setText(fOrderDate);
                Date deliveryDate = Helper.addDays(orderDate, 1);
                String fDeliveryDate = da.dateToSqlDate(deliveryDate);
                txtDeliveryDate.setText(fDeliveryDate);
            }
            else
                JOptionPane.showMessageDialog(null, "Der blev ikke fundet nogen kunde med det telefon nummer", "INFORMATION!", JOptionPane.INFORMATION_MESSAGE);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, Logging.handleException(e, 0), "Fejl", JOptionPane.WARNING_MESSAGE);
        }

	}
	
	private void createOrderLine(long itemNumber, int quantity)
	{

        try
        {
            Product product = _productCtrl.getProductById(itemNumber, true);
            if(product != null)
            {
                OrderItems line = new OrderItems(quantity, product.getSalesPrice(), product);
                _totalPrice += Double.parseDouble(line.getProduct().getSalesPrice().toString()) * line.getQuantity();
                _orderLines.add(line);
            }
            else
                JOptionPane.showMessageDialog(null, "Der blev ikke fundet et produkt med dette produkt nummer", "INFORMATION!", JOptionPane.INFORMATION_MESSAGE);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, Logging.handleException(e, 99), "Fejl", JOptionPane.WARNING_MESSAGE);
        }
	}
	
	private void addOrderLineData()
	{
		Object[][] data = {};
		model.setDataVector(data, columnNames);
		for(OrderItems line : _orderLines)
		{
			Object[] row = new Object[]{ line.getProduct().getId(), line.getProduct().getName(), line.getQuantity(), calcMoms(line.getProduct().getSalesPrice().toString()), getMoms(line.getProduct().getSalesPrice().toString()), line.getUnitPrice().doubleValue() * line.getQuantity() };
			model.addRow(row);
		}
	}
	
	private double calcMoms(String price)
	{
		double currentPrice = Double.parseDouble(price);
		double tax = currentPrice*0.20;
		return currentPrice-tax;
	}
	
	private double getMoms(String price)
	{
		double currentPrice = Double.parseDouble(price);
        return currentPrice*0.20;
	}
	
	private void calcTotalPrice()
	{
		txtTotal.setText(_totalPrice + "");
		txtTax.setText(getMoms(_totalPrice + "") + "");
		txtCalcPrice.setText(calcMoms(_totalPrice + "") + "");
	}
	
	private void createOrder()
	{
		try
		{
            DataAccess da = DataAccess.getInstance();
			long orderId = da.getNextId("SalesOrder");
            long customerId = Long.parseLong(txtPhoneNo.getText());
            Customer customer = _customerCtrl.getCustomerById(customerId);
            DeliveryStatus status = _statusCtrl.getDeliveryStatusById(drpOrderStatus.getSelectedIndex() + 1);

			if(orderId != 0)
			{
				for(OrderItems line : _orderLines)
					//insert orderline
				
				JOptionPane.showMessageDialog(null, "Ordren er nu oprettet!", "INFORMATION!", JOptionPane.INFORMATION_MESSAGE);
				_instance = null;
				_frame.dispose();
			}
			else
				JOptionPane.showMessageDialog(null, "Der skete en fejl under oprettelsen af ordren", "INFORMATION!", JOptionPane.INFORMATION_MESSAGE);
		}
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, Logging.handleException(e, 0), "FEJL!", JOptionPane.WARNING_MESSAGE);
		}
	}
}