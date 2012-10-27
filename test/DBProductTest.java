import db.DBProduct;

import static org.junit.Assert.*;

import db.DataAccess;
import models.Product;
import models.ProductCategory;
import models.Supplier;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created: 25-10-2012
 * @version: 0.1
 * Filename: DBProductTest.java
 * Description:
 * @changes
 */

public class DBProductTest
{
    DBProduct _dbProduct;
    public DBProductTest()
    {
    }

    @Before
    public void setUp()
    {
        _dbProduct = new DBProduct();
    }

/*    @Test
    public void insertProduct() throws Exception
    {
        ProductCategory category = new ProductCategory(1, "Kategori1");
        Supplier supplier = new Supplier("Morten", "VejNavn 1222", 9000, "Aalborg", 92120312, "email@ok.dk", "Denmark", "Chris", "102358:123");
        Product product = new Product(10123L, "Test produkt", "2000.12", "4000.24", "1000.10", "DK", 10, category, supplier);

        int rowsAffected = _dbProduct.insertProduct(product);
        assertEquals(1, rowsAffected);
    }*/

    @Test
    public void getProduct() throws Exception
    {
        Product product = _dbProduct.getProductById(1, true);
        if(product != null)
        {
            System.out.println(product.getId() + "\n");
            System.out.println(product.getName() + "\n");
            if(product.getSupplier() != null)
            {
                System.out.println(product.getSupplier().getPhoneNo() + "\n");
                System.out.println(product.getSupplier().getName() + "\n");
            }
            if(product.getCategory() != null)
            {
                System.out.println(product.getCategory().getCategoryId() + "\n");
                System.out.println(product.getCategory().getCategoryName() + "\n");
            }
            System.out.println(product.getPurchasePrice() + "\n");
            System.out.println(product.getSalesPrice() + "\n");
            System.out.println(product.getRentPrice() + "\n");
            System.out.println(product.getCountryOfOrigin() + "\n");
            System.out.println(product.getMinimumStock() + "\n");
        }
        assertNotNull(product);
    }

    @Test
    public void getProductByName() throws Exception
    {
        Product product = _dbProduct.getProductByName("Test produkt", true);
        if(product != null)
        {
            System.out.println(product.getId() + "\n");
            System.out.println(product.getName() + "\n");
            if(product.getSupplier() != null)
            {
                System.out.println(product.getSupplier().getPhoneNo() + "\n");
                System.out.println(product.getSupplier().getName() + "\n");
            }
            if(product.getCategory() != null)
            {
                System.out.println(product.getCategory().getCategoryId() + "\n");
                System.out.println(product.getCategory().getCategoryName() + "\n");
            }
            System.out.println(product.getPurchasePrice() + "\n");
            System.out.println(product.getSalesPrice() + "\n");
            System.out.println(product.getRentPrice() + "\n");
            System.out.println(product.getCountryOfOrigin() + "\n");
            System.out.println(product.getMinimumStock() + "\n");
        }
        assertNotNull(product);
    }

    @Test
    public void getAllProducts() throws Exception
    {
        ArrayList<Product> products = _dbProduct.getAllProducts(true);
        assertNotNull(products);
    }

    @Test
    public void updateProduct() throws Exception
    {
        ProductCategory category = new ProductCategory(1, "Kategori1");
        Supplier supplier = new Supplier("Morten", "VejNavn 1222", 9000, "Aalborg", 92120312, "email@ok.dk", "Denmark", "Chris", "102358:123");
        Product product = new Product(1L, "Test produkt updated", "4000.12", "6000.24", "2000.10", "UK", 5, category, supplier);

        int rowsAffected = _dbProduct.updateProduct(product);
        assertEquals(1, rowsAffected);
    }

    /*@Test
    public void deleteProduct() throws Exception
    {
        Product product = new Product(3L, "Test produkt updated", "4000.12", "6000.24", "2000.10", "UK", 5);
        int rowsAffected = _dbProduct.deleteProduct(product);
        assertEquals(1, rowsAffected);
    }*/

    @Test
    public void getNextId() throws Exception
    {
        DataAccess _da = DataAccess.getInstance();
        long id = _da.getNextId("Products");
        System.out.println(id);
        assertEquals(4, id);
    }
}
