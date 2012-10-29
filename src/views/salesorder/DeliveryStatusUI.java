package views.salesorder;

import controllers.DeliveryStatusCtrl;
import db.DBDeliveryStatus;
import models.DeliveryStatus;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import utils.Logging;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;


public class DeliveryStatusUI
{

	private static JFrame _frame;
	private static DeliveryStatusUI _instance;
	private JPanel contentPane;
	
	// Controllers
	private DeliveryStatusCtrl _deliveryStatusCtrl;
		
	// Edit Object
	private DeliveryStatus _deliveryStatus;

	// Elements
	private JTextField txtAddStatus;
	private JTextField txtEditState;
	private JComboBox<String> drpDeliveryStatus;
	private DefaultComboBoxModel<String> model;

    public static JFrame createWindow()
    {
        if(_instance == null)
            _instance = new DeliveryStatusUI();

        return _frame;
    }

    private DeliveryStatusUI()
    {
        createElements();
    }
	
	public void createElements()
	{
		_deliveryStatusCtrl = new DeliveryStatusCtrl();
		
		_frame = new JFrame();
		_frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		_frame.setTitle("Administrer Leveringsstatus");
		_frame.setBounds(0, 0, 509, 275);
		_frame.setResizable(false);
		_frame.setLocationRelativeTo(null);
		_frame.setVisible(true);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
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
		
		// Update
		JLabel lblEditStatusHeader = new JLabel("Opdater Leveringsstatus");
		lblEditStatusHeader.setBounds(12, 12, 250, 20);
		lblEditStatusHeader.setFont(new Font("Dialog", Font.BOLD, 16));
		contentPane.add(lblEditStatusHeader);
		
		JLabel lblDeliveryStatus = new JLabel("Leverings Status");
		lblDeliveryStatus.setBounds(12, 47, 180, 15);
		contentPane.add(lblDeliveryStatus);
		
		drpDeliveryStatus = new JComboBox<String>();
		drpDeliveryStatus.setBounds(200, 45, 300, 20);
        contentPane.add(drpDeliveryStatus);
        model = new DefaultComboBoxModel<String>(addDeliveryStatus());
        drpDeliveryStatus.setModel(model);
		
		JLabel lblStateId = new JLabel("Status Navn");
		lblStateId.setBounds(12, 133, 140, 15);
		contentPane.add(lblStateId);
		
		txtEditState = new JTextField();
		txtEditState.setBounds(200, 133, 180, 20);
		contentPane.add(txtEditState);
		txtEditState.setColumns(10);
		
		JButton btnUpdate = new JButton("Opdater");
        btnUpdate.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                if(txtEditState.getText().length() > 0)
                	UpdateDeliveryStatus();
                else
                   JOptionPane.showMessageDialog(null, "Feltet kan ikke være tomt", "Information", JOptionPane.WARNING_MESSAGE);
            }
        });
        btnUpdate.setBounds(400, 130, 100, 25);
        contentPane.add(btnUpdate);
		
        JButton btnEdit = new JButton("Rediger");
        btnEdit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
            	editDeliveryStatus();
            }
        });
        btnEdit.setBounds(400, 84, 100, 25);
        contentPane.add(btnEdit);
        
		JSeparator separator = new JSeparator();
        separator.setBounds(12, 177, 480, 1);
        contentPane.add(separator);
        
		// Create
		JLabel lblAddStatusHeader = new JLabel("Opret Leveringsstatus");
		lblAddStatusHeader.setBounds(12, 195, 250, 20);
		lblAddStatusHeader.setFont(new Font("Dialog", Font.BOLD, 16));
		contentPane.add(lblAddStatusHeader);
		
		txtAddStatus = new JTextField();
		txtAddStatus.setBounds(200, 229, 180, 19);
		contentPane.add(txtAddStatus);
		txtAddStatus.setColumns(10);
		
		JButton btnCreate = new JButton("Opret");
        btnCreate.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
            	if(txtAddStatus.getText().length() > 0)
            		createDeliveryStatus();
            	else
            		JOptionPane.showMessageDialog(null, "Feltet kan ikke v�re tomt", "IInformation", JOptionPane.WARNING_MESSAGE);
            }
        });
        btnCreate.setBounds(400, 225, 100, 25);
        contentPane.add(btnCreate);
	}
	
	private String[] addDeliveryStatus()
    {
    	ArrayList<DeliveryStatus> deliveryStatusses;
    	try
    	{
    		deliveryStatusses = _deliveryStatusCtrl.getAllDeliveryStatus();
    		String[] deliveryIds = new String[deliveryStatusses.size()];
    		for(int i = 0; i < deliveryStatusses.size(); i++)
    			deliveryIds[i] = deliveryStatusses.get(i).getDeliveryState();  
    		
    		return deliveryIds;
    	}
    	catch (Exception e)
    	{
    		JOptionPane.showMessageDialog(null, Logging.handleException(e, 0), "Fejl", JOptionPane.WARNING_MESSAGE);
    	}
    	
    	return null;
    }
    
    
	private void createDeliveryStatus()
	{
		try
		{
			DeliveryStatus deliveryStatus = new DeliveryStatus(1, txtAddStatus.getText());
			DBDeliveryStatus dbDeliveryStatus = new DBDeliveryStatus();
			dbDeliveryStatus.insertDeliveryStatus(deliveryStatus);
			JOptionPane.showMessageDialog(null, "Leverings Status er nu oprettet", "INFORMATION!", JOptionPane.INFORMATION_MESSAGE);
			txtAddStatus.setText("");
			rebindDropdown();
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, Logging.handleException(e, 3), "Fejl", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	
	private void UpdateDeliveryStatus()
	{
		try
		{
			// statements
			DBDeliveryStatus dbDeliveryStatus = new DBDeliveryStatus();
			_deliveryStatus.setDeliveryState(txtEditState.getText());
			dbDeliveryStatus.updateDeliveryStatus(_deliveryStatus);
			JOptionPane.showMessageDialog(null, "Leverings status er nu Opdateret", "INFORMATION!", JOptionPane.INFORMATION_MESSAGE);
			txtEditState.setText("");
			rebindDropdown();	
		}
		catch (Exception e)
		{
			// handle
			JOptionPane.showMessageDialog(null, Logging.handleException(e, 4), "Fejl", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	
	private void editDeliveryStatus()
	{
		try
		{
			int deliveryId = drpDeliveryStatus.getSelectedIndex() + 1;
			_deliveryStatus = _deliveryStatusCtrl.getDeliveryStatusById(deliveryId);
			txtEditState.setText(_deliveryStatus.getDeliveryState());
		}
		catch (Exception e)
		{
			// handle
			JOptionPane.showMessageDialog(null, Logging.handleException(e, 0), "Fejl", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void rebindDropdown()
    {
        drpDeliveryStatus.removeAllItems();
        model = new DefaultComboBoxModel<String>(addDeliveryStatus());
        drpDeliveryStatus.setModel(model);
    }
}
