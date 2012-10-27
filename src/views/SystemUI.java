package views;

import utils.Logging;
import views.product.ProductShowAllUI;
import views.contact.ContactShowAllUI;

import java.awt.Cursor;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.JMenuBar;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SystemUI
{

	protected static final int EXIT_ON_CLOSE = 0;
	public JFrame frmSystemWindow;
	private JPanel pnlMain;

	public SystemUI()
	{
		frmSystemWindow = new JFrame(GlobalUI.systemInformation(01) + " - " + GlobalUI.systemInformation(02) + " (build" + GlobalUI.systemInformation(03) + ")");
		frmSystemWindow.setResizable(false);
		frmSystemWindow.setBounds(100, 100, 1024, 768);
		frmSystemWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSystemWindow.getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		frmSystemWindow.setJMenuBar(menuBar);
		
		JMenu mnFiles = new JMenu("Filer");
		menuBar.add(mnFiles);
		
		JMenu mnNew = new JMenu("Ny(t)");
		mnFiles.add(mnNew);
		
		JMenuItem mntmNewOrder = new JMenuItem("Ordre");
		mntmNewOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newOrder();
			}
		});
		mnNew.add(mntmNewOrder);
		
		JMenuItem mntmNewContact = new JMenuItem("Kontakt");
		mntmNewContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newContact();
			}
		});
		mnNew.add(mntmNewContact);
		
		JMenuItem mntmNewProduct = new JMenuItem("Produkt");
		mntmNewProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newProduct();
			}
		});
		mnNew.add(mntmNewProduct);
		
		JMenu mnOpen = new JMenu("Åben");
		mnFiles.add(mnOpen);
		
		JMenuItem mntmOpenOrder = new JMenuItem("Ordre");
		mntmOpenOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectOrder();
			}
		});
		mnOpen.add(mntmOpenOrder);
		
		JMenuItem mntmOpenContacts = new JMenuItem("Kontakter");
		mntmOpenContacts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectContacts();
			}
		});
		mnOpen.add(mntmOpenContacts);
		
		JMenuItem mntmOpenProductBase = new JMenuItem("Produktbase");
		mntmOpenProductBase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectProductBase();
			}
		});
		mnOpen.add(mntmOpenProductBase);
		
		JSeparator separator = new JSeparator();
		mnFiles.add(separator);
		
		JMenuItem mntmExit = new JMenuItem("Afslut");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(EXIT_ON_CLOSE);
			}
		});
		mnFiles.add(mntmExit);
		
		JMenu mnAbout = new JMenu("Om");
		menuBar.add(mnAbout);
		
		JMenuItem mntmAboutApplication = new JMenuItem("Applikationen");
		mntmAboutApplication.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame aboutFrame = views.AboutUI.createWindow();
				aboutFrame.setLocationRelativeTo(frmSystemWindow);
			}
		});
		mnAbout.add(mntmAboutApplication);
		
		JPanel pnlQuickSelection = new JPanel();
		pnlQuickSelection.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		pnlQuickSelection.setBackground(Color.WHITE);
		pnlQuickSelection.setBounds(4, 4, 180, 210);
		frmSystemWindow.getContentPane().add(pnlQuickSelection);
		pnlQuickSelection.setLayout(null);
		
		JLabel lblOrder = new JLabel(new ImageIcon(SystemUI.class.getResource("/cart.png")));
		lblOrder.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				selectOrder();
			}
		});
		lblOrder.setHorizontalAlignment(SwingConstants.LEFT);
		lblOrder.setText("Ordre");
		lblOrder.setBounds(12, 12, 150, 50);
		lblOrder.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pnlQuickSelection.add(lblOrder);
		
		JLabel lblContacts = new JLabel(new ImageIcon(SystemUI.class.getResource("/contacts.png")));
		lblContacts.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				selectContacts();
			}
		});
		lblContacts.setText("Kontakter");
		lblContacts.setHorizontalAlignment(SwingConstants.LEFT);
		lblContacts.setBounds(12, 74, 150, 50);
		lblContacts.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pnlQuickSelection.add(lblContacts);
		
		JLabel lblProducts = new JLabel(new ImageIcon(SystemUI.class.getResource("/productbase.png")));
		lblProducts.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				selectProductBase();
			}
		});
		lblProducts.setText("Produktbase");
		lblProducts.setHorizontalAlignment(SwingConstants.LEFT);
		lblProducts.setBounds(12, 136, 150, 50);
		lblProducts.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pnlQuickSelection.add(lblProducts);
		
		JPanel pnlInfo = new JPanel();
		pnlInfo.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		pnlInfo.setBounds(4, 217, 180, 494);
		pnlInfo.setBackground(Color.WHITE);
		frmSystemWindow.getContentPane().add(pnlInfo);
		
		pnlMain = new JPanel();
        pnlMain.setVisible(true);
		pnlMain.setBounds(187, 4, 829, 705);
		frmSystemWindow.getContentPane().add(pnlMain);
		pnlMain.setLayout(null);
	}
	
	private void newOrder()
	{
		
	}
	
	private void newContact()
	{
		
	}
	
	private void newProduct()
	{
		
	}
	
	private void selectOrder()
	{
		
	}
	
	private void selectContacts()
	{
		pnlMain.removeAll();
		ContactShowAllUI contactShowAllUI = new ContactShowAllUI();
		JPanel panel = contactShowAllUI.createWindow();
		pnlMain.add(panel);
		pnlMain.revalidate();
		pnlMain.repaint();
	}
	
	private void selectProductBase()
	{	
		DataNotificationUI info = new views.DataNotificationUI();
		info.setVisible(true);
		
		try
		{
			pnlMain.removeAll();
			ProductShowAllUI productShowAllUI = new ProductShowAllUI();
			JPanel panel = productShowAllUI.createWindow();
			pnlMain.add(panel);
			pnlMain.revalidate();
			pnlMain.repaint();
			
			info.dispose();
		}
		catch(Exception err)
		{
			JOptionPane.showMessageDialog(null, Logging.handleException(err, 0), "Fejl", JOptionPane.WARNING_MESSAGE);
		}
	}
}