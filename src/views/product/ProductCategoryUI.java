package views.product;

import controllers.ProductCategoryCtrl;
import models.ProductCategory;
import utils.Logging;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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

    //Elements
    private JComboBox<String> drpCategories;

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
        _frame.setBounds(0, 0, 509, 528);
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


        JLabel lblProdName = new JLabel("Produkt kategorier");
        lblProdName.setBounds(12, 12, 99, 15);
        contentPane.add(lblProdName);
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
            drpCategories.setBounds(142, 10, 300, 20);
            contentPane.add(drpCategories);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, Logging.handleException(e, 0), "Fejl", JOptionPane.WARNING_MESSAGE);
        }
    }
}
