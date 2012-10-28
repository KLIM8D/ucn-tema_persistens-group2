package views.product;

import controllers.ProductCategoryCtrl;
import db.DBProductCategory;
import models.ProductCategory;
import utils.Logging;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * Created: 28-10-2012
 * @version: 0.1
 * Filename: ProductCategoryUI.java
 * Description:
 * @changes
 */

public class ProductCategoryUI
{
    private static JFrame _frame;
    private static ProductCategoryUI _instance;
    private JPanel contentPane;

    //Controllers
    private ProductCategoryCtrl _categoryCtrl;

    //Edit object
    private ProductCategory _category;

    //Elements
    private JComboBox<String> drpCategories;
    private DefaultComboBoxModel<String> model;
    private JTextField txtEditCategory;
    private JTextField txtCreateCategory;

    public ProductCategoryUI()
    {
        createElements();
    }

    public static JFrame createWindow()
    {
        if(_instance == null)
            _instance = new ProductCategoryUI();

        return _frame;
    }

    private void createElements()
    {
        _categoryCtrl = new ProductCategoryCtrl();

        _frame = new JFrame();
        _frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        _frame.setTitle("Administrer produkt kategorier");
        _frame.setBounds(0, 0, 509, 275);
        _frame.setResizable(false);
        _frame.setLocationRelativeTo(null);
        _frame.setVisible(true);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        _frame.setContentPane(contentPane);

        _frame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                _instance = null;
                _frame.dispose();
            }
        });

        JLabel lblEditCategoryHeader = new JLabel("Opdater produkt kategorier");
        lblEditCategoryHeader.setBounds(12, 12, 250, 20);
        lblEditCategoryHeader.setFont(new Font("Dialog", Font.BOLD, 16));
        contentPane.add(lblEditCategoryHeader);

        JLabel lblProdName = new JLabel("Produkt kategorier");
        lblProdName.setBounds(12, 47, 180, 15);
        contentPane.add(lblProdName);

        drpCategories = new JComboBox<String>();
        drpCategories.setBounds(200, 45, 300, 20);
        contentPane.add(drpCategories);
        model = new DefaultComboBoxModel<String>(addCategories());
        drpCategories.setModel(model);

        JButton btnEdit = new JButton("Rediger");
        btnEdit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
               editCategory();
            }
        });
        btnEdit.setBounds(400, 73, 100, 25);
        contentPane.add(btnEdit);

        addCategories();

        JLabel lblEditCategory = new JLabel("Kategori navn");
        lblEditCategory.setBounds(12, 123, 100, 20);
        contentPane.add(lblEditCategory);

        txtEditCategory = new JTextField();
        txtEditCategory.setBounds(132, 125, 230, 19);
        contentPane.add(txtEditCategory);
        txtEditCategory.setColumns(10);

        JButton btnUpdate = new JButton("Opdater");
        btnUpdate.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                if(txtEditCategory.getText().length() > 0)
                    updateCategory();
                else
                    JOptionPane.showMessageDialog(null, "Feltet kan ikke være tomt", "Information", JOptionPane.WARNING_MESSAGE);
            }
        });
        btnUpdate.setBounds(400, 121, 100, 25);
        contentPane.add(btnUpdate);

        JSeparator separator = new JSeparator();
        separator.setBounds(12, 177, 480, 1);
        contentPane.add(separator);

        JLabel lblAddCategoryHeader = new JLabel("Opret produkt kategori");
        lblAddCategoryHeader.setBounds(12, 195, 250, 20);
        lblAddCategoryHeader.setFont(new Font("Dialog", Font.BOLD, 16));
        contentPane.add(lblAddCategoryHeader);

        JLabel lblAddCategory = new JLabel("Kategori navn");
        lblAddCategory.setBounds(12, 227, 100, 20);
        contentPane.add(lblAddCategory);

        txtCreateCategory = new JTextField();
        txtCreateCategory.setBounds(132, 229, 230, 19);
        contentPane.add(txtCreateCategory);
        txtCreateCategory.setColumns(10);

        JButton btnCreate = new JButton("Opret");
        btnCreate.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                if(txtCreateCategory.getText().length() > 0)
                    createCategory();
                else
                    JOptionPane.showMessageDialog(null, "Feltet kan ikke være tomt", "Information", JOptionPane.WARNING_MESSAGE);
            }
        });
        btnCreate.setBounds(400, 225, 100, 25);
        contentPane.add(btnCreate);
    }

    private String[] addCategories()
    {
        ArrayList<ProductCategory> categories;
        try
        {
            categories = _categoryCtrl.getAllProductCategories();
            String[] categoryNames = new String[categories.size()];
            for(int i = 0; i < categories.size(); i++)
                categoryNames[i] = categories.get(i).getCategoryName();

            return categoryNames;
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, Logging.handleException(e, 0), "Fejl", JOptionPane.WARNING_MESSAGE);
        }

        return null;
    }

    private void editCategory()
    {
        try
        {
            String categoryName = drpCategories.getSelectedItem().toString();
            _category = _categoryCtrl.getProductCategoryByName(categoryName);
            txtEditCategory.setText(_category.getCategoryName());
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, Logging.handleException(e, 0), "Fejl", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void updateCategory()
    {
        try
        {
            DBProductCategory dbProductCategory = new DBProductCategory();
            _category.setCategoryName(txtEditCategory.getText());
            dbProductCategory.updateProductCategory(_category);
            JOptionPane.showMessageDialog(null, "Produkt kategorien er nu opdateret", "INFORMATION!", JOptionPane.INFORMATION_MESSAGE);
            txtEditCategory.setText("");
            rebindDropdown();
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, Logging.handleException(e, 4), "Fejl", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void createCategory()
    {
        try
        {
            ProductCategory category = new ProductCategory(1, txtCreateCategory.getText());
            DBProductCategory dbProductCategory = new DBProductCategory();
            dbProductCategory.insertProductCategory(category);
            JOptionPane.showMessageDialog(null, "Produkt kategorien er nu oprettet", "INFORMATION!", JOptionPane.INFORMATION_MESSAGE);
            txtCreateCategory.setText("");
            rebindDropdown();
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, Logging.handleException(e, 3), "Fejl", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void rebindDropdown()
    {
        drpCategories.removeAllItems();
        model = new DefaultComboBoxModel<String>(addCategories());
        drpCategories.setModel(model);
    }
}
