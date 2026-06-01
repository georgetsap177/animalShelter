package gui;


import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logic.Shelter;
import model.Adopter;
import model.AdoptionApplication;
import model.Animal;
import model.ApplicationStatus;

public class AdopterFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JComboBox<String> comboBox;
	private JLabel lblNewLabel_1; 

	private Shelter shelter;
	private Adopter currentAdopter;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdopterFrame frame = new AdopterFrame(null, null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AdopterFrame(Shelter shelter, Adopter adopter) {
		this.shelter = shelter;
		this.currentAdopter = adopter;

		setTitle("Adopter Panel");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 650, 450);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		FlowLayout fl_panel = (FlowLayout) panel.getLayout();
		fl_panel.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("Animal Type");
		panel.add(lblNewLabel);

		comboBox = new JComboBox<String>();
		panel.add(comboBox);

		JButton btnNewButton = new JButton("Filter");
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Sort");
		panel.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Application");
		panel.add(btnNewButton_2);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		scrollPane.setViewportView(table);

		lblNewLabel_1 = new JLabel("Application Status");
		contentPane.add(lblNewLabel_1, BorderLayout.SOUTH);

		String[] columns = {"ID", "Name", "Type", "Age", "Description", "Applications"};
		DefaultTableModel tableModel = new DefaultTableModel(columns, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(tableModel);

		comboBox.addItem("ALL");
		comboBox.addItem("DOG");
		comboBox.addItem("CAT");
		comboBox.addItem("BIRD");

		if (shelter != null && currentAdopter != null) {
			refreshTable();
			updateStatusLabel();
		}

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (shelter == null) return;
				String selected = comboBox.getSelectedItem().toString();
				tableModel.setRowCount(0);

				for (Animal a : shelter.getAnimalList()) {
					if (selected.equals("Όλα") || a.getType().toString().equalsIgnoreCase(selected)) {
						int appsCount = countApplications(a);
						tableModel.addRow(new Object[]{a.getId(), a.getAnimalName(), a.getType(), a.getAnimalAge(), a.getDescription(), appsCount});
					}
				}
			}
		});

		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (shelter == null) return;
				ArrayList<Animal> sortedList = new ArrayList<>(shelter.getAnimalList());
				
				Collections.sort(sortedList, (a1, a2) -> Integer.compare(countApplications(a2), countApplications(a1)));

				tableModel.setRowCount(0);
				for (Animal a : sortedList) {
					tableModel.addRow(new Object[]{a.getId(), a.getAnimalName(), a.getType(), a.getAnimalAge(), a.getDescription(), countApplications(a)});
				}
			}
		});

		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (shelter == null || currentAdopter == null) return;
				
				int selectedRow = table.getSelectedRow();
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(AdopterFrame.this, "Please select an animal from the table!", "Warning", JOptionPane.WARNING_MESSAGE);
					return;
				}

				for (AdoptionApplication app : shelter.getAdoptionApplicationList()) {
					if (app.getAdopter().getUsername().equals(currentAdopter.getUsername()) && app.getStatus() == ApplicationStatus.PENDING) {
						JOptionPane.showMessageDialog(AdopterFrame.this, "You already have a pending application!", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
				}

				String animalId = table.getValueAt(selectedRow, 0).toString();
				for (Animal a : shelter.getAnimalList()) {
					if (a.getId().equals(animalId)) {
						AdoptionApplication newApp = new AdoptionApplication(currentAdopter, a);
						shelter.addApplication(newApp);
						shelter.saveToFile(); 
						
						JOptionPane.showMessageDialog(AdopterFrame.this, "The application for " + a.getAnimalName() + " has been submitted!");
						refreshTable();
						updateStatusLabel();
						break;
					}
				}
			}
		});
	}

	private void refreshTable() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		for (Animal a : shelter.getAnimalList()) {
			model.addRow(new Object[]{a.getId(), a.getAnimalName(), a.getType(), a.getAnimalAge(), a.getDescription(), countApplications(a)});
		}
	}
	
	private int countApplications(Animal animal) {
		int count = 0;
		for (AdoptionApplication app : shelter.getAdoptionApplicationList()) {
			if (app.getAnimal().getId().equals(animal.getId())) {
				count++;
			}
		}
		return count;
	}

	private void updateStatusLabel() {
		String statusText = "Application Status: No application has been sumbitted yet.";
		for (AdoptionApplication app : shelter.getAdoptionApplicationList()) {
			if (app.getAdopter().getUsername().equals(currentAdopter.getUsername())) {
				statusText = "Application Status: Last application for [" + app.getAnimal().getAnimalName() + "] -> " + app.getStatus();
			}
		}
		lblNewLabel_1.setText(statusText);
	}
}
