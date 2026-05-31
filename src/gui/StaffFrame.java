package gui;

import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import logic.Shelter;
import model.Animal;
import model.AnimalType;

public class StaffFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    
    private JTextField txtName, txtAddress, txtPhone;
    private JTable tableAnimals, tableApplications;
    private DefaultTableModel animalModel, appModel;
    private Shelter shelter;

    public StaffFrame(Shelter shelter) {
        this.shelter = shelter;
        setTitle("Staff Management - " + shelter.getShelterName());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(750, 500);
        setLocationRelativeTo(null);
        
        JTabbedPane tabbedPane = new JTabbedPane();
        getContentPane().add(tabbedPane, BorderLayout.CENTER);

        // --- 1. MANAGEMENT OF SHELTER ---
        JPanel panelShelter = new JPanel(null);
        tabbedPane.addTab("Management of Shelter", panelShelter);
        
        JLabel lblName = new JLabel("Shelter Name:"); lblName.setBounds(12, 40, 118, 20); panelShelter.add(lblName);
        txtName = new JTextField(shelter.getShelterName()); txtName.setBounds(150, 40, 200, 20); panelShelter.add(txtName);
        
        JLabel lblAddr = new JLabel("Address:"); lblAddr.setBounds(30, 80, 100, 20); panelShelter.add(lblAddr);
        txtAddress = new JTextField(shelter.getAddress()); txtAddress.setBounds(150, 80, 200, 20); panelShelter.add(txtAddress);
        
        JLabel lblPhone = new JLabel("Phone:"); lblPhone.setBounds(30, 120, 100, 20); panelShelter.add(lblPhone);
        txtPhone = new JTextField(shelter.getPhone()); txtPhone.setBounds(150, 120, 200, 20); panelShelter.add(txtPhone);

        JButton btnUpdate = new JButton("Update Info");
        btnUpdate.setBounds(150, 170, 150, 30);
        panelShelter.add(btnUpdate);
        btnUpdate.addActionListener(e -> {
            shelter.setShelterName(txtName.getText());
            shelter.setAddress(txtAddress.getText());
            shelter.setPhone(txtPhone.getText());
            JOptionPane.showMessageDialog(this, "Τα στοιχεία ενημερώθηκαν!");
        });

        // --- 2. MANAGE USERS & ANIMALS ---
        JPanel panelAnimals = new JPanel(null);
        tabbedPane.addTab("Manage Users & Animals", panelAnimals);
        
        // Ονομάζουμε τη στήλη "Breed" αντί για "Description"
        animalModel = new DefaultTableModel(new String[]{"ID", "Name", "Type", "Age", "Breed"}, 0);
        tableAnimals = new JTable(animalModel);
        JScrollPane scrollAnimals = new JScrollPane(tableAnimals);
        scrollAnimals.setBounds(20, 50, 450, 280);
        panelAnimals.add(scrollAnimals);

        JButton btnAdd = new JButton("Add Animal");
        btnAdd.setBounds(500, 50, 150, 30);
        panelAnimals.add(btnAdd);
        btnAdd.addActionListener(e -> {
            JTextField idField = new JTextField();
            JTextField nameField = new JTextField();
            JTextField ageField = new JTextField();
            JTextField breedField = new JTextField(); // Εδώ εισάγεται η ράτσα
            JComboBox<AnimalType> typeBox = new JComboBox<>(AnimalType.values());
            
            Object[] msg = {"ID:", idField, "Name:", nameField, "Age:", ageField, "Type:", typeBox, "Breed:", breedField};
            if (JOptionPane.showConfirmDialog(this, msg, "Add Animal", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                try {
                    shelter.addAnimal(new Animal(
                        idField.getText(), 
                        nameField.getText(), 
                        (AnimalType)typeBox.getSelectedItem(), 
                        Integer.parseInt(ageField.getText()), 
                        breedField.getText() // Αποθήκευση της ράτσας στο πεδίο description
                    ));
                    refreshAnimalTable();
                } catch (Exception ex) { JOptionPane.showMessageDialog(this, "Σφάλμα εισαγωγής!"); }
            }
        });

        JButton btnDelete = new JButton("Delete Animal");
        btnDelete.setBounds(500, 100, 150, 30);
        panelAnimals.add(btnDelete);
        btnDelete.addActionListener(e -> {
            int row = tableAnimals.getSelectedRow();
            if (row != -1) {
                shelter.getAnimalList().remove(row);
                refreshAnimalTable();
            } else { JOptionPane.showMessageDialog(this, "Επιλέξτε μια γραμμή!"); }
        });

        // --- 3. MANAGE APPLICATIONS ---
        JPanel panelApps = new JPanel(null);
        tabbedPane.addTab("Manage Applications", panelApps);
        
        appModel = new DefaultTableModel(new String[]{"Index", "Adopter", "Animal", "Status"}, 0);
        tableApplications = new JTable(appModel);
        JScrollPane scrollApps = new JScrollPane(tableApplications);
        scrollApps.setBounds(20, 50, 450, 230);
        panelApps.add(scrollApps);
        
        JButton btnApprove = new JButton("Approve");
        btnApprove.setBounds(500, 50, 120, 30);
        panelApps.add(btnApprove);
        
        JButton btnReject = new JButton("Reject");
        btnReject.setBounds(500, 100, 120, 30);
        panelApps.add(btnReject);

        refreshAnimalTable();
    }

    private void refreshAnimalTable() {
        animalModel.setRowCount(0);
        for (Object obj : shelter.getAnimalList()) {
            if (obj instanceof Animal) {
                Animal a = (Animal) obj;
                animalModel.addRow(new Object[]{
                    a.getId(), 
                    a.getAnimalName(), 
                    a.getType(), 
                    a.getAnimalAge(), 
                    a.getDescription() // Εδώ επιστρέφεται η ράτσα
                });
            }
        }
    }
}