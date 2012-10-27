package views.product;

import controllers.ProductCtrl;
import controllers.ProductCategoryCtrl;
import models.Product;
import utils.ButtonColumn;
import utils.Helper;
import utils.Logging;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Created: 27-10-2012
 * @version: 0.1
 * Filename: ShowAllUI.java
 * Description:
 * @changes
 */

public class ShowAllUI
{
    private static JPanel _panel;

    //Controllers
    private ProductCtrl _prodCtrl;
    private ProductCategoryCtrl _categoryCtrl;

    private JTextField txtProductId;
    private JTextField txtProductName;

    //Grid
    private DefaultTableModel model;
    private JTable table;
    private String[] columnNames;

    public JPanel createWindow()
    {
        createElements();
        return _panel;
    }

    public ShowAllUI()
    {

    }

    private void createElements()
    {
        _prodCtrl = new ProductCtrl();
        _categoryCtrl = new ProductCategoryCtrl();
        _panel = new JPanel();
        _panel.setVisible(true);

        //Search
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBounds(5, 5, 904, 35);

        JLabel lblProductId = new JLabel("Produkt nummer: ");
        lblProductId.setBounds(187, 10, 126, 15);
        searchPanel.add(lblProductId);

        txtProductId = new JTextField();
        txtProductId.setBounds(318, 10, 110, 15);
        txtProductId.setColumns(10);
        txtProductId.addKeyListener(new KeyAdapter()
        {
            public void keyReleased(KeyEvent e) {
                if(txtProductId.getText().length() > 0)
                {
                    Helper.checkIfLong(txtProductId);
                }
            }
        });
        searchPanel.add(txtProductId);

        JLabel lblProductName = new JLabel("Produkt navn: ");
        lblProductName.setBounds(433, 10, 103, 15);
        searchPanel.add(lblProductName);

        txtProductName = new JTextField();
        txtProductName.setBounds(541, 10, 110, 15);
        txtProductName.setColumns(10);
        searchPanel.add(txtProductName);

        JButton btnSearch = new JButton("Søg");
        btnSearch.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                if (txtProductId.getText().length() > 0)
                {
                    long itemNumber = Long.parseLong(txtProductId.getText());
                    addData(itemNumber);
                }
                if (txtProductName.getText().length() > 0 && txtProductId.getText().length() <= 0)
                {
                    addData(txtProductName.getText());
                }
                if (txtProductName.getText().length() <= 0 && txtProductId.getText().length() <= 0)
                {
                    addData();
                }
            }
        });
        _panel.setLayout(null);
        btnSearch.setBounds(656, 5, 61, 25);
        searchPanel.add(btnSearch);

        _panel.add(searchPanel);
        //Search end

        //Grid / table
        JPanel gridPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        _panel.add(gridPanel);

        columnNames = new String[]{"Produkt nummer", "Produkt navn", "Minimums beholdning", "Kategori", "Leverandør", "Produceret i", "Pris", "Købs pris", "Pris for leje",  " "};

        table = new JTable()
        {
            public boolean isCellEditable(int data, int columns)
            {
                /*if(columns != 9)
                    return false;

                return true;*/
                return columns == 9;
            }
        };

        model = new DefaultTableModel();

        table.setModel(model);
        table.setFillsViewportHeight(true);
        addData();

        JScrollPane scrollPane = new JScrollPane(table);
        gridPanel.add(scrollPane);
        //Grid / Table end
    }

    private void addShowButton()
    {
        @SuppressWarnings("serial")
        Action show = new AbstractAction()
        {
            public void actionPerformed(ActionEvent e)
            {
                JTable table = (JTable) e.getSource();
                int row = Integer.valueOf(e.getActionCommand());
                long itemNumber = Long.parseLong(table.getValueAt(row, 0).toString());
                txtProductId.setText(itemNumber + "");
            }
        };
        ButtonColumn buttonColumn = new ButtonColumn(table, show, 6);
        buttonColumn.setMnemonic(KeyEvent.VK_D);
    }

    private void addData()
    {
        try
        {
            ArrayList<Product> products = _prodCtrl.getAllProducts(true);
            Object[][] data = {};
            model.setDataVector(data, columnNames);
            for(Product prod : products)
            {
                Object[] row = new Object[]{prod.getId(), prod.getName(), prod.getMinimumStock(), "Kategori", "Leverandør", prod.getCountryOfOrigin(), prod.getSalesPrice(), prod.getPurchasePrice(), prod.getRentPrice(), "Rediger" };
                model.addRow(row);
            }
            addShowButton();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, Logging.handleException(e, 0), "Fejl", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void addData(long itemNumber)
    {
        try
        {
            Product prod = _prodCtrl.getProductById(itemNumber, true);

            if(prod != null)
            {
                Object[][] row = new Object[][]
                {
                        {prod.getId(), prod.getName(), prod.getMinimumStock(), prod.getCategory().getCategoryName(), prod.getSupplier().getName(), prod.getCountryOfOrigin(), prod.getSalesPrice(), prod.getPurchasePrice(), prod.getRentPrice(), "Rediger" }
                };
                model.setDataVector(row, columnNames);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Der blev ikke fundet noget produkt, med produkt nummeret: " + itemNumber, "Information", JOptionPane.WARNING_MESSAGE);
            }
            addShowButton();
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, Logging.handleException(e, 0), "Fejl", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void addData(String itemName)
    {
        try
        {
            Product prod = _prodCtrl.getProductByName(itemName, true);

            if(prod != null)
            {
                Object[][] row = new Object[][]
                {
                        {prod.getId(), prod.getName(), prod.getMinimumStock(), prod.getCategory().getCategoryName(), prod.getSupplier().getName(), prod.getCountryOfOrigin(), prod.getSalesPrice(), prod.getPurchasePrice(), prod.getRentPrice(), "Rediger" }
                };
                model.setDataVector(row, columnNames);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Der blev ikke fundet noget produkt med navnet: " + itemName, "Information", JOptionPane.WARNING_MESSAGE);
            }
            addShowButton();
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, Logging.handleException(e, 0), "Fejl", JOptionPane.WARNING_MESSAGE);
        }
    }
}
