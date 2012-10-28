package views.salesorder;

import controllers.SalesOrderCtrl;
import models.SalesOrder;
import utils.ButtonColumn;
import utils.Helper;
import utils.JTextFieldLimit;
import utils.Logging;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created: 28-10-2012
 * @version: 0.1
 * Filename: OrderShowAllUI.java
 * Description:
 * @changes
 */

public class OrderShowAllUI
{
    private static JPanel _panel;

    //Controllers
    private SalesOrderCtrl _saCtrl;

    private JTextField txtOrderId;
    private JTextField txtCustomerId;

    //Grid
    private DefaultTableModel model;
    private JTable table;
    private String[] columnNames;

    public JPanel createWindow()
    {
        createElements();
        return _panel;
    }

    public OrderShowAllUI()
    {

    }

    private void createElements()
    {
        _saCtrl = new SalesOrderCtrl();
        _panel = new JPanel();
        _panel.setLayout(null);
        _panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        _panel.setVisible(true);
        _panel.setBounds(5, 5, 825, 705);

        //Top navigation
        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        navPanel.setBounds(5, 0, 825, 35);
        JButton btnCreateOrder = new JButton("Opret ordre");
        btnCreateOrder.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                OrderCreateUI.createWindow();
            }
        });
        btnCreateOrder.setBounds(10, 5, 61, 25);
        navPanel.add(btnCreateOrder);

        _panel.add(navPanel);

        //Search
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBounds(5, 40, 825, 35);

        JLabel lblOrderId = new JLabel("Order nummer: ");
        lblOrderId.setBounds(187, 10, 126, 15);
        searchPanel.add(lblOrderId);

        txtOrderId = new JTextField();
        txtOrderId.setBounds(318, 10, 110, 15);
        txtOrderId.setColumns(10);
        txtOrderId.addKeyListener(new KeyAdapter()
        {
            public void keyReleased(KeyEvent e) {
                if(txtOrderId.getText().length() > 0)
                {
                    Helper.checkIfLong(txtOrderId);
                }
            }
        });
        searchPanel.add(txtOrderId);

        JLabel lblCustomerId = new JLabel("Kunde nummer: ");
        lblCustomerId.setBounds(433, 10, 126, 15);
        searchPanel.add(lblCustomerId);

        txtCustomerId = new JTextField();
        txtCustomerId.setDocument(new JTextFieldLimit(8));
        txtCustomerId.addKeyListener(new KeyAdapter()
        {
            public void keyReleased(KeyEvent e) {
                if(txtCustomerId.getText().length() > 0)
                {
                    Helper.checkIfLong(txtCustomerId);
                }
            }
        });
        txtCustomerId.setBounds(541, 10, 110, 15);
        txtCustomerId.setColumns(10);
        searchPanel.add(txtCustomerId);

        JButton btnSearch = new JButton("Søg");
        btnSearch.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                if (txtOrderId.getText().length() > 0)
                {
                    long orderId = Long.parseLong(txtOrderId.getText());
                    addData(orderId);
                }
                if (txtCustomerId.getText().length() > 0 && txtOrderId.getText().length() <= 0)
                {
                    long customerId = Long.parseLong(txtCustomerId.getText());
                    addDataCustomer(customerId);
                }
                if (txtCustomerId.getText().length() <= 0 && txtOrderId.getText().length() <= 0)
                {
                    addData();
                }
            }
        });
        btnSearch.setBounds(656, 5, 61, 25);
        searchPanel.add(btnSearch);

        _panel.add(searchPanel);
        //Search end

        //Grid / table
        JPanel gridPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        _panel.add(gridPanel);

        columnNames = new String[]{"Order nummer", "Kunde", "Oprettet", "Status", "Leverings dato", " ", " "};

        table = new JTable()
        {
            public boolean isCellEditable(int data, int columns)
            {
                if(columns == 5 || columns == 6)
                    return true;
                return false;
            }
        };

        model = new DefaultTableModel();

        table.setModel(model);
        table.setFillsViewportHeight(true);
        addData();

        JScrollPane scrollPane = new JScrollPane(table);
        gridPanel.setBounds(new Rectangle(0, 75, 825, 700));
        table.setPreferredScrollableViewportSize(new Dimension(815, 700));
        scrollPane.setPreferredSize(new Dimension(815, 700));
        gridPanel.add(scrollPane);
        //Grid / Table end
    }

    private void addButton(final int columnIndex)
    {
        @SuppressWarnings("serial")
        Action show = new AbstractAction()
        {
            public void actionPerformed(ActionEvent e)
            {
                JTable table = (JTable) e.getSource();
                int row = Integer.valueOf(e.getActionCommand());
                long orderId = Long.parseLong(table.getValueAt(row, 0).toString());
                if(columnIndex == 5)
                {
                    //edit
                }
                else
                {
                    try
                    {
                        if(Helper.showConfirmDialog("ordre") == 1)
                        {
                            SalesOrder order = _saCtrl.getSalesOrderById(orderId, true);
                            _saCtrl.deleteSalesOrder(order);
                            addData();
                        }
                    }
                    catch (Exception ex)
                    {
                        JOptionPane.showMessageDialog(null, Logging.handleException(ex, 0), "Fejl", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        };
        ButtonColumn buttonColumn = new ButtonColumn(table, show, columnIndex);
        buttonColumn.setMnemonic(KeyEvent.VK_D);
    }

    private void addData()
    {
        try
        {
            ArrayList<SalesOrder> salesOrders = _saCtrl.getAllSalesOrders(true);
            Object[][] data = {};
            model.setDataVector(data, columnNames);
            for(SalesOrder order : salesOrders)
            {
                Object[] row = new Object[]{order.getOrderId(), order.getContact().getName(), order.getOrderDate(), order.getDeliveryStatus().getDeliveryState(), order.getDeliveryDate(), "Rediger", "Slet" };
                model.addRow(row);
            }
            addButton(5);
            addButton(6);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, Logging.handleException(e, 0), "Fejl", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void addData(long orderId)
    {
        try
        {
            SalesOrder order = _saCtrl.getSalesOrderById(orderId, true);

            if(order != null)
            {
                Object[][] row = new Object[][]
                {
                        {order.getOrderId(), order.getContact().getName(), order.getOrderDate(), order.getDeliveryStatus().getDeliveryState(), order.getDeliveryDate(), "Rediger", "Slet" }
                };
                model.setDataVector(row, columnNames);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Der blev ikke fundet nogen order med order nummeret: " + orderId, "Information", JOptionPane.WARNING_MESSAGE);
            }
            addButton(5);
            addButton(6);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, Logging.handleException(e, 0), "Fejl", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void addDataCustomer(long customerId)
    {
        try
        {
            ArrayList<SalesOrder> salesOrders = _saCtrl.getAllSalesOrdersByCustomer(customerId, true);
            if(salesOrders.size() > 0)
            {
                Object[][] data = {};
                model.setDataVector(data, columnNames);
                for(SalesOrder order : salesOrders)
                {
                    Object[] row = new Object[]{order.getOrderId(), order.getContact().getName(), order.getOrderDate(), order.getDeliveryStatus().getDeliveryState(), order.getDeliveryDate(), "Rediger", "Slet" };
                    model.addRow(row);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Der blev ikke fundet nogen ordre registreret til kunden: " + customerId, "Information", JOptionPane.WARNING_MESSAGE);
            }
            addButton(5);
            addButton(6);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, Logging.handleException(e, 0), "Fejl", JOptionPane.WARNING_MESSAGE);
        }
    }
}
