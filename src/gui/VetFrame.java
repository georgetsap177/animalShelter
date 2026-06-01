package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logic.Shelter;
import model.Vet;

public class VetFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable jVetTable;
	private DefaultTableModel tableModel;
	
	private JTextField jUsernameText;
	private JTextField jPasswordText;
	private JTextField jFirstNameText;
	private JTextField jLastNameText;
	private JTextField jPhoneText;
	private JTextField jEmailText;
	
	private JPanel panel_1;
	private JButton btnNew;
	private JButton btnViewAll;
	private JButton btnUpdate;
	private JButton btnDelete;

	private Shelter shelter;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					Shelter mockShelter = Shelter.loadFromFile();
					Shelter mockShelter = null;
					if (mockShelter == null) {
						mockShelter = new Shelter("Animal Shelter", "Athens", "2101234567");
					}
					VetFrame frame = new VetFrame(mockShelter);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VetFrame(Shelter shelter) {
		this.shelter = shelter;
		
		setTitle("Vet Management");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 750, 400);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		String[] columnNames = {"Username", "First Name", "Last Name", "Phone", "Email"};
		tableModel = new DefaultTableModel(columnNames, 0) {
		
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		jVetTable = new JTable(tableModel);
		scrollPane.setViewportView(jVetTable);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.EAST);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{80, 140};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		// --- Username ---
		GridBagConstraints gbc_jUsernameLabel = new GridBagConstraints();
		gbc_jUsernameLabel.insets = new Insets(5, 5, 5, 5);
		gbc_jUsernameLabel.anchor = GridBagConstraints.EAST;
		gbc_jUsernameLabel.gridx = 0; gbc_jUsernameLabel.gridy = 0;
		panel.add(new JLabel("Username:"), gbc_jUsernameLabel);
		
		jUsernameText = new JTextField();
		jUsernameText.setColumns(15);
		GridBagConstraints gbc_jUsernameField = new GridBagConstraints();
		gbc_jUsernameField.insets = new Insets(5, 5, 5, 5);
		gbc_jUsernameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_jUsernameField.gridx = 1; gbc_jUsernameField.gridy = 0;
		panel.add(jUsernameText, gbc_jUsernameField);
		
		// --- Password ---
		GridBagConstraints gbc_jPasswordLabel = new GridBagConstraints();
		gbc_jPasswordLabel.insets = new Insets(5, 5, 5, 5);
		gbc_jPasswordLabel.anchor = GridBagConstraints.EAST;
		gbc_jPasswordLabel.gridx = 0; gbc_jPasswordLabel.gridy = 1;
		panel.add(new JLabel("Password:"), gbc_jPasswordLabel);
		
		jPasswordText = new JTextField();
		jPasswordText.setColumns(15);
		GridBagConstraints gbc_jPasswordField = new GridBagConstraints();
		gbc_jPasswordField.insets = new Insets(5, 5, 5, 5);
		gbc_jPasswordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_jPasswordField.gridx = 1; gbc_jPasswordField.gridy = 1;
		panel.add(jPasswordText, gbc_jPasswordField);
		
		// --- FirstName ---
		GridBagConstraints gbc_jFirstNameLabel = new GridBagConstraints();
		gbc_jFirstNameLabel.insets = new Insets(5, 5, 5, 5);
		gbc_jFirstNameLabel.anchor = GridBagConstraints.EAST;
		gbc_jFirstNameLabel.gridx = 0; gbc_jFirstNameLabel.gridy = 2;
		panel.add(new JLabel("FirstName:"), gbc_jFirstNameLabel);
		
		jFirstNameText = new JTextField();
		jFirstNameText.setColumns(15);
		GridBagConstraints gbc_jFirstNameField = new GridBagConstraints();
		gbc_jFirstNameField.insets = new Insets(5, 5, 5, 5);
		gbc_jFirstNameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_jFirstNameField.gridx = 1; gbc_jFirstNameField.gridy = 2;
		panel.add(jFirstNameText, gbc_jFirstNameField);
		
		// --- LastName ---
		GridBagConstraints gbc_jLastNameLabel = new GridBagConstraints();
		gbc_jLastNameLabel.insets = new Insets(5, 5, 5, 5);
		gbc_jLastNameLabel.anchor = GridBagConstraints.EAST;
		gbc_jLastNameLabel.gridx = 0; gbc_jLastNameLabel.gridy = 3;
		panel.add(new JLabel("LastName:"), gbc_jLastNameLabel);
		
		jLastNameText = new JTextField();
		jLastNameText.setColumns(15);
		GridBagConstraints gbc_jLastNameField = new GridBagConstraints();
		gbc_jLastNameField.insets = new Insets(5, 5, 5, 5);
		gbc_jLastNameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_jLastNameField.gridx = 1; gbc_jLastNameField.gridy = 3;
		panel.add(jLastNameText, gbc_jLastNameField);
		
		// --- Phone ---
		GridBagConstraints gbc_jPhoneLabel = new GridBagConstraints();
		gbc_jPhoneLabel.insets = new Insets(5, 5, 5, 5);
		gbc_jPhoneLabel.anchor = GridBagConstraints.EAST;
		gbc_jPhoneLabel.gridx = 0; gbc_jPhoneLabel.gridy = 4;
		panel.add(new JLabel("Phone:"), gbc_jPhoneLabel);
		
		jPhoneText = new JTextField();
		jPhoneText.setColumns(15);
		GridBagConstraints gbc_jPhoneField = new GridBagConstraints();
		gbc_jPhoneField.insets = new Insets(5, 5, 5, 5);
		gbc_jPhoneField.fill = GridBagConstraints.HORIZONTAL;
		gbc_jPhoneField.gridx = 1; gbc_jPhoneField.gridy = 4;
		panel.add(jPhoneText, gbc_jPhoneField);
		
		// --- Email ---
		GridBagConstraints gbc_jEmailLabel = new GridBagConstraints();
		gbc_jEmailLabel.insets = new Insets(5, 5, 5, 5);
		gbc_jEmailLabel.anchor = GridBagConstraints.EAST;
		gbc_jEmailLabel.gridx = 0; gbc_jEmailLabel.gridy = 5;
		panel.add(new JLabel("Email:"), gbc_jEmailLabel);
		
		jEmailText = new JTextField();
		jEmailText.setColumns(15);
		GridBagConstraints gbc_jEmailField = new GridBagConstraints();
		gbc_jEmailField.insets = new Insets(5, 5, 5, 5);
		gbc_jEmailField.fill = GridBagConstraints.HORIZONTAL;
		gbc_jEmailField.gridx = 1; gbc_jEmailField.gridy = 5;
		panel.add(jEmailText, gbc_jEmailField);

		panel_1 = new JPanel();
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		btnNew = new JButton("Create Vet");
		panel_1.add(btnNew);
		
		btnViewAll = new JButton("View All");
		panel_1.add(btnViewAll);
		
		btnUpdate = new JButton("Update");
		panel_1.add(btnUpdate);
		
		btnDelete = new JButton("Delete");
		panel_1.add(btnDelete);
		
		jVetTable.addMouseListener(new MouseAdapter() {
		
			public void mouseClicked(MouseEvent e) {
				int row = jVetTable.getSelectedRow();
				if (row != -1 && shelter != null) {
					String username = tableModel.getValueAt(row, 0).toString();
					for (Vet v : shelter.getVetList()) { 
						if (v.getUsername().equals(username)) {
							jUsernameText.setText(v.getUsername());
							jPasswordText.setText(v.getPassword());
							jFirstNameText.setText(v.getFirstName()); 
							jLastNameText.setText(v.getLastName());   
							jPhoneText.setText(v.getUserPhone());     
							jEmailText.setText(v.getEmail());         
							break;
						}
					}
					jUsernameText.setEditable(false);
				}
			}
		});
		
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (shelter == null) return;
				String user = jUsernameText.getText().trim();
				String pass = jPasswordText.getText().trim();
				String first = jFirstNameText.getText().trim();
				String last = jLastNameText.getText().trim();
				String phone = jPhoneText.getText().trim();
				String email = jEmailText.getText().trim();
				
				if (user.isEmpty() || pass.isEmpty() || first.isEmpty() || last.isEmpty()) {
					JOptionPane.showMessageDialog(VetFrame.this, "Please fill in all required fields!", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				try {
					Vet newVet = new Vet(user, pass, first, last, phone, email);
					shelter.addVet(newVet); 
					refreshTable();
					clearFields();
					JOptionPane.showMessageDialog(VetFrame.this, "Your vet profile has succesfully been created!");
				} catch (IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(VetFrame.this, ex.getMessage(), "Validation error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		btnViewAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshTable();
			}
		});
		
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = jVetTable.getSelectedRow();
				if (row == -1 || shelter == null) {
					JOptionPane.showMessageDialog(VetFrame.this, "Choose vet from the board!", "Warning", JOptionPane.WARNING_MESSAGE);
					return;
				}
				String targetUser = tableModel.getValueAt(row, 0).toString();
				try {
					for (Vet v : shelter.getVetList()) {
						if (v.getUsername().equals(targetUser)) {
							v.setPassword(jPasswordText.getText().trim());
							v.setFirstName(jFirstNameText.getText().trim());
							v.setLastName(jLastNameText.getText().trim());
							v.setUserPhone(jPhoneText.getText().trim()); 
							v.setEmail(jEmailText.getText().trim());     
							break;
						}
					}
					shelter.saveToFile();
					refreshTable();
					clearFields();
					JOptionPane.showMessageDialog(VetFrame.this, "Your details have been updated!");
				} catch (IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(VetFrame.this, ex.getMessage(), "Validation error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = jVetTable.getSelectedRow();
				if (row == -1 || shelter == null) {
					JOptionPane.showMessageDialog(VetFrame.this, "Choose vet!", "Warning", JOptionPane.WARNING_MESSAGE);
					return;
				}
				String targetUser = tableModel.getValueAt(row, 0).toString();
				Vet toRemove = null;
				for (Vet v : shelter.getVetList()) {
					if (v.getUsername().equals(targetUser)) {
						toRemove = v;
						break;
					}
				}
				if (toRemove != null) {
					shelter.getVetList().remove(toRemove);
					shelter.saveToFile();
					JOptionPane.showMessageDialog(VetFrame.this, "Vet deleted!");
				}
				refreshTable();
				clearFields();
			}
		});
		
		refreshTable();
	}
	
	private void clearFields() {
		jUsernameText.setText("");
		jPasswordText.setText("");
		jFirstNameText.setText("");
		jLastNameText.setText("");
		jPhoneText.setText("");
		jEmailText.setText("");
		jUsernameText.setEditable(true);
	}
	
	private void refreshTable() {
		tableModel.setRowCount(0);
		if (shelter == null || shelter.getVetList() == null) return;
		for (Vet v : shelter.getVetList()) {
			Object[] rowData = { v.getUsername(), v.getFirstName(), v.getLastName(), v.getUserPhone(), v.getEmail() };
			tableModel.addRow(rowData);
		}
	}
}