package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;

@SuppressWarnings("serial")
public class DataNotificationUI extends JFrame
{

	public DataNotificationUI()
    {
		setAlwaysOnTop(true);
		setResizable(false);
		setTitle("Vent venligst....");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 334, 85);
		setLocationRelativeTo(null);
        JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

		JLabel lblLoadingImg = new JLabel("");
        lblLoadingImg.setIcon(new ImageIcon(DataNotificationUI.class.getResource("/javax/swing/plaf/metal/icons/Inform.gif")));
        lblLoadingImg.setBounds(12, 12, 35, 35);
		contentPane.add(lblLoadingImg);

		JLabel lblLoadingText = new JLabel("Der kommunikeres med databasen!");
        lblLoadingText.setFont(new Font("Dialog", Font.BOLD, 12));
        lblLoadingText.setBounds(59, 20, 294, 15);
        contentPane.add(lblLoadingText);
	}
}
