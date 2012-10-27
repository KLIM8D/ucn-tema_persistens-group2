package views.product;

import controllers.ProductCategoryCtrl;
import controllers.ProductCtrl;
import controllers.SupplierCtrl;
import db.DataAccess;
import models.Product;
import models.ProductCategory;
import models.Supplier;
import utils.Helper;
import utils.JTextFieldLimit;
import utils.Logging;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Created: 27-10-2012
 * @version: 0.1
 * Filename: CreateUI.java
 * Description:
 * @changes
 */

public class CreateUI
{
    private static JFrame _frame;
    private static CreateUI _instance;
    private JPanel contentPane;

    //Controllers
    private ProductCtrl _prodCtrl;
    private ProductCategoryCtrl _categoryCtrl;
    private SupplierCtrl _supplierCtrl;

    private JTextField txtProdId;
    private JTextField txtProdName;
    private JTextField txtProdMin;
    private JTextField txtCountryOfOrigin;
    private JTextField txtProdPrice;
    private JComboBox<String> drpCategories;
    private JComboBox<String> drpSuppliers;

    public static JFrame createWindow()
    {
        if(_instance == null)
            _instance = new CreateUI();

        return _frame;
    }

    private CreateUI()
    {
        createElements();
    }

    private void createElements()
    {
        _prodCtrl = new ProductCtrl();
        _categoryCtrl = new ProductCategoryCtrl();
        _supplierCtrl = new SupplierCtrl();

        _frame = new JFrame();
        _frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        _frame.setTitle("Opret produkt");
        _frame.setBounds(0, 0, 509, 269);
        _frame.setResizable(false);
        _frame.setVisible(true);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        _frame.setContentPane(contentPane);

        JLabel lblProdId = new JLabel("Produkt nr");
        lblProdId.setBounds(12, 12, 99, 15);
        contentPane.add(lblProdId);

        JLabel lblProdName = new JLabel("Produkt navn");
        lblProdName.setBounds(12, 37, 99, 15);
        contentPane.add(lblProdName);

        txtProdId = new JTextField();
        txtProdId.addKeyListener(new KeyAdapter()
        {
            public void keyReleased(KeyEvent e)
            {
                Helper.checkIfLong(txtProdId);
            }
        });
        txtProdId.setBounds(121, 10, 371, 19);
        contentPane.add(txtProdId);
        txtProdId.setColumns(10);

        txtProdName = new JTextField();
        txtProdName.setBounds(121, 35, 371, 19);
        contentPane.add(txtProdName);
        txtProdName.setColumns(10);

        JLabel lblCatID = new JLabel("Kategori");
        lblCatID.setBounds(12, 64, 99, 15);
        contentPane.add(lblCatID);

        JSeparator separator = new JSeparator();
        separator.setBounds(12, 133, 480, 1);
        contentPane.add(separator);

        JLabel lblProdMin = new JLabel("Min. beholding");
        lblProdMin.setBounds(12, 147, 124, 15);
        contentPane.add(lblProdMin);

        txtProdMin = new JTextField();
        txtProdMin.addKeyListener(new KeyAdapter()
        {
            public void keyReleased(KeyEvent e)
            {
                Helper.checkIfInt(txtProdMin);
            }
        });
        txtProdMin.setBounds(121, 147, 114, 19);
        contentPane.add(txtProdMin);
        txtProdMin.setColumns(10);

        JLabel lblCountryOfOrigin = new JLabel("Produceret i");
        lblCountryOfOrigin.setBounds(253, 147, 124, 15);
        contentPane.add(lblCountryOfOrigin);

        txtCountryOfOrigin = new JTextField();
        txtCountryOfOrigin.setDocument(new JTextFieldLimit(2));
        txtCountryOfOrigin.setBounds(378, 145, 114, 19);
        contentPane.add(txtCountryOfOrigin);
        txtCountryOfOrigin.setColumns(10);

        JLabel lblProdPrice = new JLabel("Pris");
        lblProdPrice.setBounds(12, 105, 70, 15);
        contentPane.add(lblProdPrice);

        txtProdPrice = new JTextField();
        txtProdPrice.setBounds(121, 103, 114, 19);
        contentPane.add(txtProdPrice);
        txtProdPrice.setColumns(10);

        JLabel lblCurrency = new JLabel("DKK");
        lblCurrency.setBounds(240, 105, 70, 15);
        contentPane.add(lblCurrency);

        JButton btnCancel = new JButton("Annuller");
        btnCancel.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                _instance = null;
                _frame.dispose();
            }
        });
        btnCancel.setBounds(375, 199, 117, 25);
        contentPane.add(btnCancel);

        JButton btnCreate = new JButton("Opret");
        btnCreate.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                createProduct();
            }
        });
        btnCreate.setBounds(246, 199, 117, 25);
        contentPane.add(btnCreate);

        addCategories();

        _frame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                _instance = null;
                _frame.dispose();
            }
        });
    }

    private void createProduct()
    {
        try
        {
            DataAccess da = DataAccess.getInstance();
            long itemNumber = da.getNextId("Products");
            String itemName = txtProdName.getText();
            int minInStock = Integer.parseInt(txtProdMin.getText());
            String countryOfOrigin = txtCountryOfOrigin.getText();
            String salesPrice = txtProdPrice.getText();
            String purchasePrice = "2000";
            String rentPrice = "200";
            int categoryId = drpCategories.getSelectedIndex() + 1;
            int supplierId = drpSuppliers.getSelectedIndex() + 1;
            ProductCategory category = _categoryCtrl.getProductCategoryById(categoryId);
            Supplier supplier = _supplierCtrl.getSupplierById(supplierId, true);
            Product product = new Product(itemNumber, itemName, purchasePrice, salesPrice, rentPrice, countryOfOrigin, minInStock, category, supplier);

            _prodCtrl.insertProduct(product);

            JOptionPane.showMessageDialog(null, "Produktet er nu oprettet", "INFORMATION!", JOptionPane.INFORMATION_MESSAGE);
            _instance = null;
            _frame.dispose();
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, Logging.handleException(e, 0), "Fejl", JOptionPane.WARNING_MESSAGE);
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
            drpCategories.setBounds(121, 62, 300, 20);
            contentPane.add(drpCategories);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, Logging.handleException(e, 0), "Fejl", JOptionPane.WARNING_MESSAGE);
        }
    }
}
