package views;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class AboutUI {

	private static JFrame _frame;
	private static AboutUI _instance;
	private JPanel pnlAbout;
	
	public static JFrame createWindow()
	{
		if(_instance == null)
			_instance = new AboutUI();
		
		return _frame;
	}

	private AboutUI() {
		_frame = new JFrame();
		_frame.setTitle("Om applikationen");
		_frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		_frame.setBounds(0, 0, 470, 415);
		_frame.setAlwaysOnTop(true);
		_frame.setResizable(false);
		_frame.setVisible(true);
		pnlAbout = new JPanel();
		pnlAbout.setBorder(new EmptyBorder(5, 5, 5, 5));
		pnlAbout.setLayout(new BorderLayout(0, 0));
		_frame.setContentPane(pnlAbout);
		
		JTabbedPane tabAbout = new JTabbedPane(JTabbedPane.TOP);
		pnlAbout.add(tabAbout, BorderLayout.CENTER);
		
		JPanel pnlSystem = new JPanel();
		tabAbout.addTab("System", null, pnlSystem, null);
		pnlSystem.setLayout(null);
		
		JLabel lblTitle = new JLabel("Western Style Ltd.");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Dialog", Font.BOLD, 18));
		lblTitle.setBounds(12, 12, 409, 22);
		pnlSystem.add(lblTitle);
		
		JLabel lblDescription = new JLabel(GlobalUI.systemInformation(01) + " - " + GlobalUI.systemInformation(02));
		lblDescription.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescription.setBounds(22, 54, 399, 15);
		pnlSystem.add(lblDescription);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 43, 409, 1);
		pnlSystem.add(separator);
		
		JLabel lblDevelopers = new JLabel("Udviklet af:");
		lblDevelopers.setBounds(22, 95, 126, 15);
		pnlSystem.add(lblDevelopers);
		
		JLabel lblDev01 = new JLabel("Rasmus Meyer Lillienskjold Pedersen (1016073@ucn.dk)");
		lblDev01.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblDev01.setBounds(22, 110, 399, 15);
		pnlSystem.add(lblDev01);
		
		JLabel lblDev02 = new JLabel("Chris Tindb" + "\u00E6" + "k (1016273@ucn.dk)");
		lblDev02.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblDev02.setBounds(22, 125, 384, 15);
		pnlSystem.add(lblDev02);
		
		JLabel lblDev03 = new JLabel("Morten Klim S" + "\u00F8" + "rensen (1016053@ucn.dk)");
		lblDev03.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblDev03.setBounds(22, 140, 384, 15);
		pnlSystem.add(lblDev03);
		
		JLabel lblDev04 = new JLabel("Mads Nielsen (1016051@ucn.dk)");
		lblDev04.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblDev04.setBounds(22, 155, 384, 15);
		pnlSystem.add(lblDev04);
		
		JLabel lblGitHubTitle = new JLabel("GitHub repository:");
		lblGitHubTitle.setBounds(22, 210, 460, 15);
		pnlSystem.add(lblGitHubTitle);
		
		JLabel lblGitHubURL = new JLabel("https://github.com/iammadsnielsen/ucn-tema_persistens-group2");
		lblGitHubURL.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblGitHubURL.setBounds(22, 225, 460, 15);
		pnlSystem.add(lblGitHubURL);
		
		JPanel pnlLicense = new JPanel();
		tabAbout.addTab("Licens", null, pnlLicense, null);
		pnlLicense.setLayout(null);
		
		JTextArea txtLicense = new JTextArea();
		txtLicense.setBackground(UIManager.getColor("ComboBox.background"));
		txtLicense.setWrapStyleWord(true);
		txtLicense.setLineWrap(true);
		txtLicense.setEditable(false);
		txtLicense.setText("Copyright (c) 2012 Rasmus Meyer Lillienskjold Pedersen, Chris Tindb" + "\u00E6" + "k, Morten Klim S" + "\u00F8" + "rensen, Mads Nielsen\n\nPermission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the \"Software\"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:\n\nThe above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.\n\nTHE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.");
		txtLicense.setBounds(5, 5, 435, 337);
		pnlLicense.add(txtLicense);
		
		_frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				_instance = null;
				_frame.dispose();
			}
		});
	}

}