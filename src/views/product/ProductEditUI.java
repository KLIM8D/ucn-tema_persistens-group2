package views.product;

import controllers.ProductCategoryCtrl;
import controllers.ProductCtrl;
import controllers.SupplierCtrl;
import models.Product;
import models.ProductCategory;
import models.ProductData;
import models.Supplier;
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
 * Created: 28-10-2012
 * @version: 0.1
 * Filename: ProductEditUI.java
 * Description:
 * @changes
 */

public class ProductEditUI
{
    private static JFrame _frame;
    private static ProductEditUI _instance;
    private JPanel contentPane;

    //Controllers
    private ProductCtrl _prodCtrl;
    private ProductCategoryCtrl _categoryCtrl;
    private SupplierCtrl _supplierCtrl;
    private long _productId;

    private JTextField txtProdName;
    private JTextField txtMinInStock;
    private JTextField txtCountryOfOrigin;
    private JTextField txtProdPrice;
    private JTextField txtPurchPrice;
    private JTextField txtRentPrice;
    private JTextField txtDataAttribute;
    private JTextField txtDataValue;
    private JComboBox<String> drpCategories;
    private JComboBox<String> drpSuppliers;
    private String[] columnNames;
    private DefaultTableModel model;
    private JTable tblData;
    private ArrayList<ProductData> _productDataCollection;

    public static JFrame createWindow(long productId)
    {
        if(_instance == null)
            _instance = new ProductEditUI(productId);

        return _frame;
    }

    private ProductEditUI(long productId)
    {
        _productId = productId;
        createElements();
    }

    private void createElements()
    {
        _prodCtrl = new ProductCtrl();
        _categoryCtrl = new ProductCategoryCtrl();
        _supplierCtrl = new SupplierCtrl();
        _productDataCollection = new ArrayList<ProductData>();

        _frame = new JFrame();
        _frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        _frame.setTitle("Rediger produkt");
        _frame.setBounds(0, 0, 509, 528);
        _frame.setResizable(false);
        _frame.setLocationRelativeTo(null);
        _frame.setVisible(true);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        _frame.setContentPane(contentPane);

        JLabel lblProdName = new JLabel("Produkt navn");
        lblProdName.setBounds(12, 12, 99, 15);
        contentPane.add(lblProdName);

        JLabel lblCountryOfOrigin = new JLabel("Produceret i");
        lblCountryOfOrigin.setBounds(12, 37, 99, 15);
        contentPane.add(lblCountryOfOrigin);

        txtProdName = new JTextField();
        txtProdName.setBounds(142, 10, 350, 19);
        contentPane.add(txtProdName);
        txtProdName.setColumns(10);

        txtCountryOfOrigin = new JTextField();
        txtCountryOfOrigin.setDocument(new JTextFieldLimit(2));
        txtCountryOfOrigin.setBounds(142, 35, 350, 19);
        contentPane.add(txtCountryOfOrigin);
        txtCountryOfOrigin.setColumns(10);

        JLabel lblCategory = new JLabel("Kategori");
        lblCategory.setBounds(12, 64, 99, 15);
        contentPane.add(lblCategory);

        JLabel lblSupplier = new JLabel("Leverandør");
        lblSupplier.setBounds(12, 91, 99, 15);
        contentPane.add(lblSupplier);

        JLabel lblMinInStock = new JLabel("Min. beholdning");
        lblMinInStock.setBounds(12, 118, 120, 15);
        contentPane.add(lblMinInStock);

        txtMinInStock = new JTextField();
        txtMinInStock.addKeyListener(new KeyAdapter()
        {
            public void keyReleased(KeyEvent e)
            {
                if(txtMinInStock.getText().length() > 0)
                    Helper.checkIfLong(txtMinInStock);
            }
        });
        txtMinInStock.setBounds(142, 116, 114, 19);
        contentPane.add(txtMinInStock);
        txtMinInStock.setColumns(10);

        JLabel lblPrice = new JLabel("Salgs pris");
        lblPrice.setBounds(12, 145, 70, 15);
        contentPane.add(lblPrice);

        txtProdPrice = new JTextField();
        txtProdPrice.addKeyListener(new KeyAdapter()
        {
            public void keyReleased(KeyEvent e)
            {
                if(txtProdPrice.getText().length() > 0)
                    Helper.checkIfDouble(txtProdPrice);
            }
        });
        txtProdPrice.setBounds(142, 143, 114, 19);
        contentPane.add(txtProdPrice);
        txtProdPrice.setColumns(10);

        JLabel lblCurrency = new JLabel("DKK");
        lblCurrency.setBounds(262, 145, 70, 15);
        contentPane.add(lblCurrency);

        JLabel lblPurchPrice = new JLabel("Købs pris");
        lblPurchPrice.setBounds(12, 172, 70, 15);
        contentPane.add(lblPurchPrice);

        txtPurchPrice = new JTextField();
        txtPurchPrice.addKeyListener(new KeyAdapter()
        {
            public void keyReleased(KeyEvent e)
            {
                if(txtPurchPrice.getText().length() > 0)
                    Helper.checkIfDouble(txtPurchPrice);
            }
        });
        txtPurchPrice.setBounds(142, 170, 114, 19);
        contentPane.add(txtPurchPrice);
        txtPurchPrice.setColumns(10);

        JLabel lblCurrency2 = new JLabel("DKK");
        lblCurrency2.setBounds(262, 172, 70, 15);
        contentPane.add(lblCurrency2);

        JLabel lblRentPrice = new JLabel("Leje pris");
        lblRentPrice.setBounds(12, 199, 70, 15);
        contentPane.add(lblRentPrice);

        txtRentPrice = new JTextField();
        txtRentPrice.addKeyListener(new KeyAdapter()
        {
            public void keyReleased(KeyEvent e)
            {
                if(txtRentPrice.getText().length() > 0)
                    Helper.checkIfDouble(txtRentPrice);
            }
        });
        txtRentPrice.setBounds(142, 197, 114, 19);
        contentPane.add(txtRentPrice);
        txtRentPrice.setColumns(10);

        JLabel lblCurrency3 = new JLabel("DKK");
        lblCurrency3.setBounds(262, 199, 70, 15);
        contentPane.add(lblCurrency3);


        JSeparator separator = new JSeparator();
        separator.setBounds(12, 226, 480, 1);
        contentPane.add(separator);


        JLabel lblProductData = new JLabel("Produkt data");
        lblProductData.setBounds(12, 242, 110, 15);
        contentPane.add(lblProductData);

        JLabel lblDataAttribute = new JLabel("Attribut");
        lblDataAttribute.setBounds(12, 268, 110, 15);
        contentPane.add(lblDataAttribute);

        txtDataAttribute = new JTextField();
        txtDataAttribute.setBounds(142, 266, 350, 19);
        contentPane.add(txtDataAttribute);
        txtDataAttribute.setColumns(10);

        JLabel lblDataValue = new JLabel("Værdi");
        lblDataValue.setBounds(12, 295, 110, 15);
        contentPane.add(lblDataValue);

        txtDataValue = new JTextField();
        txtDataValue.setBounds(142, 295, 350, 19);
        contentPane.add(txtDataValue);
        txtDataValue.setColumns(10);

        JButton btnProductData = new JButton("Tilføj data");
        btnProductData.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(txtDataAttribute.getText().length() > 0 && txtDataValue.getText().length() > 0)
                {
                    String dataAttribute = txtDataAttribute.getText();
                    String dataValue = txtDataValue.getText();
                    createProductData(dataAttribute, dataValue);
                    addProductData();
                    txtDataAttribute.setText("");
                    txtDataValue.setText("");
                }
            }
        });
        btnProductData.setBounds(375, 322, 117, 25);
        contentPane.add(btnProductData);

        columnNames = new String[]{"#", "Attribut", "Værdi", " "};

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

        JButton btnUpdate = new JButton("Opdater");
        btnUpdate.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                updateProduct();
            }
        });
        btnUpdate.setBounds(246, 470, 117, 25);
        contentPane.add(btnUpdate);

        addCategories();
        addSuppliers();

        addData(_productId);

        _frame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                _instance = null;
                _frame.dispose();
            }
        });
    }

    private void updateProduct()
    {
        try
        {
            long itemNumber = _productId;
            String itemName = txtProdName.getText();
            long minInStock = Long.parseLong(txtMinInStock.getText());
            String countryOfOrigin = txtCountryOfOrigin.getText();
            String salesPrice = txtProdPrice.getText();
            String purchasePrice = txtPurchPrice.getText();
            String rentPrice = txtRentPrice.getText();
            String categoryName = drpCategories.getSelectedItem().toString();
            String supplierName= drpSuppliers.getSelectedItem().toString();
            ProductCategory category = _categoryCtrl.getProductCategoryByName(categoryName);
            Supplier supplier = _supplierCtrl.getSupplierByName(supplierName, true);
            Product product = new Product(itemNumber, itemName, purchasePrice, salesPrice, rentPrice, countryOfOrigin, minInStock, category, supplier);

            product.setProductData(_productDataCollection);

            _prodCtrl.updateProduct(product);

            JOptionPane.showMessageDialog(null, "Produktet er nu opdateret", "INFORMATION!", JOptionPane.INFORMATION_MESSAGE);
            _instance = null;
            _frame.dispose();
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, Logging.handleException(e, 2), "Fejl", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void addData(long productId)
    {
        try
        {
            Product product = _prodCtrl.getProductById(productId, true);
            if(product != null)
            {
                txtProdName.setText(product.getName());
                txtCountryOfOrigin.setText(product.getCountryOfOrigin());
                int catIndex = Helper.selectDropdownIndex(drpCategories, product.getCategory().getCategoryName());
                drpCategories.setSelectedIndex(catIndex);
                int supIndex = Helper.selectDropdownIndex(drpSuppliers, product.getSupplier().getName());
                drpSuppliers.setSelectedIndex(supIndex);
                txtMinInStock.setText(product.getMinimumStock()  + "");
                txtProdPrice.setText(product.getSalesPrice().doubleValue() + "");
                txtPurchPrice.setText(product.getPurchasePrice().doubleValue() + "");
                txtRentPrice.setText(product.getRentPrice().doubleValue() + "");
                _productDataCollection = product.getProductData();
                addProductData();
            }
            else
                JOptionPane.showMessageDialog(null, "Der skete en fejl i hentning af produkt information", "Fejl", JOptionPane.WARNING_MESSAGE);
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, Logging.handleException(ex, 1), "Fejl", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void addCategories()
    {
        ArrayList<ProductCategory> categories;
        try
        {
            categories = _categoryCtrl.getAllProductCategories();
            String[] categoryNames = new String[categories.size()];
            for(int i = 0; i < categories.size(); i++)
                categoryNames[i] = categories.get(i).getCategoryName();

            drpCategories = new JComboBox<String>(categoryNames);
            drpCategories.setBounds(142, 62, 300, 20);
            contentPane.add(drpCategories);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, Logging.handleException(e, 0), "Fejl", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void addSuppliers()
    {
        ArrayList<Supplier> suppliers;
        try
        {
            suppliers = _supplierCtrl.getAllSuppliers(true);
            String[] categoryNames = new String[suppliers.size()];
            for(int i = 0; i < suppliers.size(); i++)
                categoryNames[i] = suppliers.get(i).getName();

            drpSuppliers = new JComboBox<String>(categoryNames);
            drpSuppliers.setBounds(142, 89, 300, 20);
            contentPane.add(drpSuppliers);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, Logging.handleException(e, 0), "Fejl", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void createProductData(String attribute, String value)
    {
        ProductData productData = new ProductData(attribute, value);
        _productDataCollection.add(productData);
    }

    private void addProductData()
    {
        Object[][] data = {};
        model.setDataVector(data, columnNames);
        int index = 1;
        for(ProductData productData : _productDataCollection)
        {
            Object[] row = new Object[]{ index, productData.getAttribute(), productData.getAttributeValue(), "Slet" };
            model.addRow(row);
            index++;
        }

        addButton(3);
    }

    private void addButton(int columnIndex)
    {
        @SuppressWarnings("serial")
        Action show = new AbstractAction()
        {
            public void actionPerformed(ActionEvent e)
            {
                JTable table = (JTable) e.getSource();
                int row = Integer.valueOf(e.getActionCommand());
                int index = Integer.parseInt(table.getValueAt(row, 0).toString()) - 1;
                _productDataCollection.remove(index);
                addProductData();
            }
        };
        ButtonColumn buttonColumn = new ButtonColumn(tblData, show, columnIndex);
        buttonColumn.setMnemonic(KeyEvent.VK_D);
    }
}
