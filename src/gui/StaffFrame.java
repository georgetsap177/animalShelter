package gui;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import logic.Shelter;
import model.Animal;
import model.AnimalType;
import model.AdoptionApplication;
import model.ApplicationStatus;

public class StaffFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    
    // Καρτέλα 1: Management of Shelter - Πεδία
    private JTextField txtName;
    private JTextField txtAddress;
    private JTextField txtPhone;
    private JButton btnUpdate;
    
    // Καρτέλα 2: Manage Users & Animals - Πεδία
    private JTable tableAnimals;
    private DefaultTableModel animalModel;
    private JButton btnAddAnimal;
    private JButton btnDeleteAnimal;
    
    // Καρτέλα 3: Manage Applications - Πεδία
    private JTable tableApplications;
    private DefaultTableModel appModel;
    private JButton btnApprove;
    private JButton btnReject;
    
    // Το αντικείμενο του καταφυγίου που διαχειριζόμαστε
    private Shelter shelter;

    public StaffFrame(Shelter shelter) {
        this.shelter = shelter;

        setTitle("Staff Management Platform - " + shelter.getShelterName());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 650, 450); 
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        // Το κεντρικό TabbedPane για τις 3 καρτέλες
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        contentPane.add(tabbedPane, BorderLayout.CENTER);

        // ==========================================
        // ΚΑΡΤΕΛΑ 1: Management of Shelter
        // ==========================================
        JPanel panelShelter = new JPanel();
        tabbedPane.addTab("Management of Shelter", null, panelShelter, null);
        panelShelter.setLayout(null); 

        JLabel lblShelterName = new JLabel("Shelter Name:");
        lblShelterName.setBounds(30, 40, 100, 20);
        panelShelter.add(lblShelterName);

        txtName = new JTextField();
        txtName.setBounds(140, 40, 200, 20);
        txtName.setText(shelter.getShelterName()); 
        panelShelter.add(txtName);
        txtName.setColumns(10);

        JLabel lblAddress = new JLabel("Address:");
        lblAddress.setBounds(30, 80, 100, 20);
        panelShelter.add(lblAddress);

        txtAddress = new JTextField();
        txtAddress.setBounds(140, 80, 200, 20);
        txtAddress.setText(shelter.getAddress()); 
        panelShelter.add(txtAddress);
        txtAddress.setColumns(10);

        JLabel lblPhone = new JLabel("Phone:");
        lblPhone.setBounds(30, 120, 100, 20);
        panelShelter.add(lblPhone);

        txtPhone = new JTextField();
        txtPhone.setBounds(140, 120, 200, 20);
        txtPhone.setText(shelter.getPhone()); 
        panelShelter.add(txtPhone);
        txtPhone.setColumns(10);

        btnUpdate = new JButton("Update Info");
        btnUpdate.setBounds(140, 170, 120, 30);
        panelShelter.add(btnUpdate);
        
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StaffFrame.this.shelter.setShelterName(txtName.getText().trim());
                StaffFrame.this.shelter.setAddress(txtAddress.getText().trim());
                StaffFrame.this.shelter.setPhone(txtPhone.getText().trim());
                
                StaffFrame.this.shelter.saveToFile("shelter_data.dat");
                
                setTitle("Staff Management Platform - " + StaffFrame.this.shelter.getShelterName());
                JOptionPane.showMessageDialog(StaffFrame.this, 
                        "Τα στοιχεία του καταφυγίου ενημερώθηκαν και αποθηκεύτηκαν επιτυχώς!", 
                        "Επιτυχία", 
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // ==========================================
        // ΚΑΡΤΕΛΑ 2: Manage Users & Animals
        // ==========================================
        JPanel panelAnimals = new JPanel();
        tabbedPane.addTab("Manage Users & Animals", null, panelAnimals, null);
        panelAnimals.setLayout(null);

        JLabel lblAnimalsList = new JLabel("Animals List:");
        lblAnimalsList.setBounds(20, 20, 100, 20);
        panelAnimals.add(lblAnimalsList);

        JScrollPane scrollPaneAnimals = new JScrollPane();
        scrollPaneAnimals.setBounds(20, 50, 420, 280);
        panelAnimals.add(scrollPaneAnimals);

        String[] animalColumns = {"ID", "Name", "Type", "Age", "Breed/Desc"};
        animalModel = new DefaultTableModel(animalColumns, 0) {
            private static final long serialVersionUID = 1L;
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        
        tableAnimals = new JTable(animalModel);
        scrollPaneAnimals.setViewportView(tableAnimals); 

        refreshAnimalTable();

        btnAddAnimal = new JButton("Add Animal");
        btnAddAnimal.setBounds(460, 50, 130, 30);
        panelAnimals.add(btnAddAnimal);
        
        btnAddAnimal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = JOptionPane.showInputDialog(StaffFrame.this, "Δώστε ID Ζώου:");
                if (id == null || id.trim().isEmpty()) return;
                
                for (int i = 0; i < StaffFrame.this.shelter.getAnimalList().size(); i++) {
                    Object obj = StaffFrame.this.shelter.getAnimalList().get(i);
                    if (((model.Animal) obj).getId().equals(id.trim())) {
                        JOptionPane.showMessageDialog(StaffFrame.this, "Το ID αυτό υπάρχει ήδη!", "Σφάλμα", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                
                String name = JOptionPane.showInputDialog(StaffFrame.this, "Δώστε Όνομα Ζώου:");
                if (name == null || name.trim().isEmpty()) return;
                
                AnimalType[] types = AnimalType.values();
                AnimalType type = (AnimalType) JOptionPane.showInputDialog(StaffFrame.this, "Επιλέξτε Τύπο:", 
                        "Τύπος Ζώου", JOptionPane.QUESTION_MESSAGE, null, types, types[0]);
                if (type == null) return;
                
                String ageStr = JOptionPane.showInputDialog(StaffFrame.this, "Δώστε Ηλικία Ζώου:");
                int age = 0;
                try {
                    age = Integer.parseInt(ageStr);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(StaffFrame.this, "Μη έγκυρη ηλικία, τέθηκε αυτόματα 0.", "Προειδοποίηση", JOptionPane.WARNING_MESSAGE);
                }
                
                String desc = JOptionPane.showInputDialog(StaffFrame.this, "Δώστε Ράτσα / Περιγραφή:");
                if (desc == null) desc = "";

                model.Animal newAnimal = new model.Animal(id.trim(), name.trim(), type, age, desc.trim());
                StaffFrame.this.shelter.addAnimal(newAnimal);
                
                refreshAnimalTable(); 
                JOptionPane.showMessageDialog(StaffFrame.this, "Το ζώο προστέθηκε επιτυχώς!");
            }
        });

        btnDeleteAnimal = new JButton("Delete Animal");
        btnDeleteAnimal.setBounds(460, 100, 130, 30);
        panelAnimals.add(btnDeleteAnimal);
        
        btnDeleteAnimal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tableAnimals.getSelectedRow();
                if (selectedRow != -1) {
                    String idToDelete = tableAnimals.getValueAt(selectedRow, 0).toString();
                    
                    int confirm = JOptionPane.showConfirmDialog(StaffFrame.this, 
                            "Είστε σίγουροι ότι θέλετε να διαγράψετε το ζώο με ID: " + idToDelete + ";", 
                            "Επιβεβαίωση Διαγραφής", JOptionPane.YES_NO_OPTION);
                    
                    if (confirm == JOptionPane.YES_OPTION) {
                        StaffFrame.this.shelter.getAnimalList().removeIf(animal -> ((model.Animal) animal).getId().equals(idToDelete));
                        StaffFrame.this.shelter.saveToFile("shelter_data.dat"); 
                        
                        refreshAnimalTable(); 
                        JOptionPane.showMessageDialog(StaffFrame.this, "Το ζώο διαγράφηκε επιτυχώς.");
                    }
                } else {
                    JOptionPane.showMessageDialog(StaffFrame.this, "Παρακαλώ επιλέξτε ένα ζώο από τον πίνακα.", "Προειδοποίηση", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        // ==========================================
        // ΚΑΡΤΕΛΑ 3: Manage Applications
        // ==========================================
        JPanel panelApplications = new JPanel();
        tabbedPane.addTab("Manage Applications", null, panelApplications, null);
        panelApplications.setLayout(null);

        JLabel lblApplications = new JLabel("Adoption Applications:");
        lblApplications.setBounds(20, 20, 150, 20);
        panelApplications.add(lblApplications);

        JScrollPane scrollPaneApps = new JScrollPane();
        scrollPaneApps.setBounds(20, 50, 570, 230);
        panelApplications.add(scrollPaneApps);

        String[] appColumns = {"Index", "Adopter Username", "Animal Name (ID)", "Status"};
        appModel = new DefaultTableModel(appColumns, 0) {
            private static final long serialVersionUID = 1L;
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tableApplications = new JTable(appModel);
        scrollPaneApps.setViewportView(tableApplications);
        
        // Φόρτωση των αιτήσεων στον πίνακα
        refreshApplicationTable();

        btnApprove = new JButton("Approve Application");
        btnApprove.setBounds(20, 300, 170, 30);
        panelApplications.add(btnApprove);
        
        btnApprove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateApplicationStatus(ApplicationStatus.APPROVED);
            }
        });

        btnReject = new JButton("Reject Application");
        btnReject.setBounds(210, 300, 170, 30);
        panelApplications.add(btnReject);
        
        btnReject.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateApplicationStatus(ApplicationStatus.REJECTED);
            }
        });
    }

    private void refreshAnimalTable() {
        animalModel.setRowCount(0); 
        if (shelter.getAnimalList() != null) {
            for (int i = 0; i < shelter.getAnimalList().size(); i++) {
                Object obj = shelter.getAnimalList().get(i);
                model.Animal textAnimal = (model.Animal) obj;
                
                // ΔΙΟΡΘΩΣΗ: Κλήση με getAnimalName() και getAnimalAge()
                Object[] row = { 
                    textAnimal.getId(), 
                    textAnimal.getAnimalName(), 
                    textAnimal.getType(), 
                    textAnimal.getAnimalAge(), 
                    textAnimal.getDescription() 
                };
                animalModel.addRow(row);
            }
        }
    }

    private void refreshApplicationTable() {
        appModel.setRowCount(0);
        if (shelter.getApplicationList() != null) {
            int index = 1;
            for (int i = 0; i < shelter.getApplicationList().size(); i++) {
                Object obj = shelter.getApplicationList().get(i);
                model.AdoptionApplication currentApp = (model.AdoptionApplication) obj;
                
                // ΔΙΟΡΘΩΣΗ: Κλήση με getAnimalName()
                Object[] row = { 
                    index++, 
                    currentApp.getAdopter().getUsername(), 
                    currentApp.getAnimal().getAnimalName() + " (" + currentApp.getAnimal().getId() + ")", 
                    currentApp.getStatus() 
                };
                appModel.addRow(row);
            }
        }
    }

    private void updateApplicationStatus(ApplicationStatus newStatus) {
        int selectedRow = tableApplications.getSelectedRow();
        if (selectedRow != -1) {
            int listIndex = Integer.parseInt(tableApplications.getValueAt(selectedRow, 0).toString()) - 1;
            
            Object obj = shelter.getApplicationList().get(listIndex);
            AdoptionApplication app = (AdoptionApplication) obj;
            app.setStatus(newStatus); 
            
            shelter.saveToFile("shelter_data.dat"); 
            refreshApplicationTable(); 
            
            JOptionPane.showMessageDialog(this, "Η αίτηση ενημερώθηκε σε: " + newStatus);
        } else {
            JOptionPane.showMessageDialog(this, "Παρακαλώ επιλέξτε μια αίτηση από τον πίνακα.", "Προειδοποίηση", JOptionPane.WARNING_MESSAGE);
        }
    }
}