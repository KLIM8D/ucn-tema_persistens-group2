package views.supplier;

import controllers.SupplierCtrl;
import models.Supplier;
import utils.ButtonColumn;
import utils.Helper;
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
 * Filename: SupplierShowAllUI.java
 * Description:
 * @changes
 */

public class SupplierShowAllUI
{
    private static JPanel _panel;

    //Controllers
    private SupplierCtrl _supCtrl;

    private JTextField txtSupplierId;
    private JTextField txtSupplierName;

    //Grid
    private DefaultTableModel model;
    private JTable table;
    private String[] columnNames;

    public JPanel createWindow()
    {
        createElements();
        return _panel;
    }

    public SupplierShowAllUI()
    {

    }

    private void createElements()
    {
        _supCtrl = new SupplierCtrl();
        _panel = new JPanel();
        _panel.setLayout(null);
        _panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        _panel.setVisible(true);
        _panel.setBounds(5, 5, 825, 705);

        //Top navigation
        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        navPanel.setBounds(5, 0, 825, 35);
        JButton btnCreateSupplier = new JButton("TilfÃ¸j leverandÃ¸r");
        btnCreateSupplier.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                //SupplierCreateUI.createWindow();
            }
        });
        btnCreateSupplier.setBounds(10, 5, 61, 25);
        navPanel.add(btnCreateSupplier);

        
        _panel.add(navPanel);

        //Search
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBounds(5, 40, 825, 35);

        JLabel lblSupplierId = new JLabel("leverandÃ¸r telefonnummer: ");
        lblSupplierId.setBounds(187, 10, 126, 15);
        searchPanel.add(lblSupplierId);

        txtSupplierId = new JTextField();
        txtSupplierId.setBounds(318, 10, 110, 15);
        txtSupplierId.setColumns(10);
        txtSupplierId.addKeyListener(new KeyAdapter()
        {
            public void keyReleased(KeyEvent e) {
                if(txtSupplierId.getText().length() > 0)
                {
                    Helper.checkIfLong(txtSupplierId);
                }
            }
        });
        searchPanel.add(txtSupplierId);

        JLabel lblSupplierName = new JLabel("leverandÃ¸r navn: ");
        lblSupplierName.setBounds(433, 10, 103, 15);
        searchPanel.add(lblSupplierName);

        txtSupplierName = new JTextField();
        txtSupplierName.setBounds(541, 10, 110, 15);
        txtSupplierName.setColumns(10);
        searchPanel.add(txtSupplierName);

        JButton btnSearch = new JButton("SÃ¸g");
        btnSearch.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                if (txtSupplierId.getText().length() > 0)
                {
                    long supplierId = Long.parseLong(txtSupplierId.getText());
                    addData(supplierId);
                }
                if (txtSupplierName.getText().length() > 0 && txtSupplierId.getText().length() <= 0)
                {
                    addData(txtSupplierName.getText());
                }
                if (txtSupplierName.getText().length() <= 0 && txtSupplierId.getText().length() <= 0)
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

        columnNames = new String[]{"Telefonnummer", "kontaktperson", "Bankkonto", " ", " ", " ", " "};

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
                long supplierId = Long.parseLong(table.getValueAt(row, 0).toString());
                if(columnIndex == 5)
                    txtSupplierId.setText(supplierId + "");
                else
                {
                    try
                    {
                        if(Helper.showConfirmDialog("Leverandør") == 1)
                        {
                            //Supplier supplier = _supCtrl.getSupplierById(supplierId, true);
                            //_supCtrl.deleteSupplier(supplier);
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
            ArrayList<Supplier> suppliers = _supCtrl.getAllSuppliers(true);
            Object[][] data = {};
            model.setDataVector(data, columnNames);
            for(Supplier sup : suppliers)
            {
                Object[] row = new Object[]{sup.getPhoneNo(), sup.getContactPerson(), sup.getBankAccount(), "", "" + "", "Rediger", "Slet" };
                model.addRow(row);
            }
            addButton(5);
            addButton(6);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, Logging.handleException(e, 0), "Fejl", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void addData(long supplierId)
    {
        try
        {
            Supplier sup = _supCtrl.getSupplierById(supplierId, true);

            if(sup != null)
            {
                Object[][] row = new Object[][]
                {
                        {sup.getPhoneNo(), sup.getContactPerson(), sup.getBankAccount(), "", "" + "", "Rediger", "Slet" }
                };
                model.setDataVector(row, columnNames);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Der blev ikke fundet nogen leverandør, med telefonnummeret: " + supplierId, "Information", JOptionPane.WARNING_MESSAGE);
            }
            addButton(5);
            addButton(6);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, Logging.handleException(e, 0), "Fejl", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void addData(String name)
    {
        try
        {
            Supplier sup = _supCtrl.getSupplierByName(name, true);

            if(sup != null)
            {
                Object[][] row = new Object[][]
                {
                        {sup.getPhoneNo(), sup.getContactPerson(), sup.getBankAccount(), "", "" + "", "Rediger", "Slet" }
                };
                model.setDataVector(row, columnNames);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Der blev ikke fundet nogen leverandør med navnet: " + name, "Information", JOptionPane.WARNING_MESSAGE);
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