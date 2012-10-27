package views.contact;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

public class ContactShowAllUI {
	
	private static JPanel _panel;
	
	public JPanel createWindow()
    {
        createElements();
        return _panel;
    }
	
	private void createElements()
	{
		_panel = new JPanel();
		_panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		_panel.setVisible(true);
		_panel.setBounds(0, 0, 829, 705);
		_panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		//Tabbed panel
		JTabbedPane tabContacts = new JTabbedPane(JTabbedPane.TOP);
		_panel.add(tabContacts);
		
		JPanel pnlCustomers = new JPanel();
		tabContacts.addTab("Kunder", null, pnlCustomers, null);
		
		JPanel pnlSuppliers = new JPanel();
		tabContacts.addTab("Leverandører", null, pnlSuppliers, null);
	}

}
