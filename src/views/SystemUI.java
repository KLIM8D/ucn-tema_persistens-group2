package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.JMenuBar;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class SystemUI {

	private JFrame frmSystemWindow;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SystemUI window = new SystemUI();
					window.frmSystemWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SystemUI() {
		initialize();
	}

	private void initialize() {
		frmSystemWindow = new JFrame();
		frmSystemWindow.setResizable(false);
		frmSystemWindow.setTitle("System Window");
		frmSystemWindow.setBounds(100, 100, 800, 600);
		frmSystemWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSystemWindow.getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		frmSystemWindow.setJMenuBar(menuBar);
		
		JPanel pnlQuickSelection = new JPanel();
		pnlQuickSelection.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		pnlQuickSelection.setBackground(Color.WHITE);
		pnlQuickSelection.setBounds(4, 4, 180, 210);
		frmSystemWindow.getContentPane().add(pnlQuickSelection);
		pnlQuickSelection.setLayout(null);
		
		JLabel lblOrder = new JLabel(new ImageIcon(SystemUI.class.getResource("/cart.png")));
		lblOrder.setHorizontalAlignment(SwingConstants.LEFT);
		lblOrder.setText("Order");
		lblOrder.setBounds(12, 12, 150, 50);
		pnlQuickSelection.add(lblOrder);
		
		JLabel lblContacts = new JLabel(new ImageIcon(SystemUI.class.getResource("/contacts.png")));
		lblContacts.setText("Kontakter");
		lblContacts.setHorizontalAlignment(SwingConstants.LEFT);
		lblContacts.setBounds(12, 74, 150, 50);
		pnlQuickSelection.add(lblContacts);
		
		JLabel lblProducts = new JLabel(new ImageIcon(SystemUI.class.getResource("/productbase.png")));
		lblProducts.setText("Produktbase");
		lblProducts.setHorizontalAlignment(SwingConstants.LEFT);
		lblProducts.setBounds(12, 136, 150, 50);
		pnlQuickSelection.add(lblProducts);
		
		JPanel pnlInfo = new JPanel();
		pnlInfo.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		pnlInfo.setBounds(4, 217, 180, 326);
		pnlInfo.setBackground(Color.WHITE);
		frmSystemWindow.getContentPane().add(pnlInfo);
	}
}