package views.product;

import controllers.ProductCtrl;
import models.Product;
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
 * Filename: ProductShowAllUI.java
 * Description:
 * @changes
 */

public class ProductShowAllUI
{
    private static JPanel _panel;

    //Controllers
    private ProductCtrl _prodCtrl;

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

    public ProductShowAllUI()
    {

    }

    private void createElements()
    {
        _prodCtrl = new ProductCtrl();
        _panel = new JPanel();
        _panel.setLayout(null);
        _panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        _panel.setVisible(true);
        _panel.setBounds(5, 5, 825, 705);

        //Top navigation
        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        navPanel.setBounds(5, 0, 825, 35);
        JButton btnCreateProduct = new JButton("Tilføj produkt");
        btnCreateProduct.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                ProductCreateUI.createWindow();
            }
        });
        btnCreateProduct.setBounds(10, 5, 61, 25);
        navPanel.add(btnCreateProduct);

        JButton btnCreateCategory = new JButton("Adm. produkt kategorier");
        btnCreateCategory.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                ProductCategoryUI.createWindow();
            }
        });
        btnCreateCategory.setBounds(86, 5, 61, 25);
        navPanel.add(btnCreateCategory);

        _panel.add(navPanel);

        //Search
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBounds(5, 40, 825, 35);

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
        btnSearch.setBounds(656, 5, 61, 25);
        searchPanel.add(btnSearch);

        _panel.add(searchPanel);
        //Search end

        //Grid / table
        JPanel gridPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        _panel.add(gridPanel);

        columnNames = new String[]{"Produkt nummer", "Produkt navn", "Min. beholdning", "Kategori", "Pris", " ", " "};

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
                long itemNumber = Long.parseLong(table.getValueAt(row, 0).toString());
                if(columnIndex == 5)
                {
                    ProductEditUI.createWindow(itemNumber);
                }
                else
                {
                    try
                    {
                        if(Helper.showConfirmDialog("produkt") == 1)
                        {
                            Product product = _prodCtrl.getProductById(itemNumber, true);
                            _prodCtrl.deleteProduct(product);
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
            ArrayList<Product> products = _prodCtrl.getAllProducts(true);
            Object[][] data = {};
            model.setDataVector(data, columnNames);
            for(Product prod : products)
            {
                Object[] row = new Object[]{prod.getId(), prod.getName(), prod.getMinimumStock(), prod.getCategory().getCategoryName(), prod.getSalesPrice().doubleValue() + " kr", "Rediger", "Slet" };
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

    private void addData(long itemNumber)
    {
        try
        {
            Product prod = _prodCtrl.getProductById(itemNumber, true);

            if(prod != null)
            {
                Object[][] row = new Object[][]
                {
                        {prod.getId(), prod.getName(), prod.getMinimumStock(), prod.getCategory().getCategoryName(), prod.getSalesPrice().doubleValue() + " kr", "Rediger", "Slet" }
                };
                model.setDataVector(row, columnNames);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Der blev ikke fundet noget produkt, med produkt nummeret: " + itemNumber, "Information", JOptionPane.WARNING_MESSAGE);
            }
            addButton(5);
            addButton(6);
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
                        {prod.getId(), prod.getName(), prod.getMinimumStock(), prod.getCategory().getCategoryName(), prod.getSalesPrice().doubleValue() + " kr", "Rediger", "Slet" }
                };
                model.setDataVector(row, columnNames);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Der blev ikke fundet noget produkt med navnet: " + itemName, "Information", JOptionPane.WARNING_MESSAGE);
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
