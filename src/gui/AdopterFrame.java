package gui;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JOptionPane;

import logic.Shelter;
import model.Animal;
import model.Adopter;
import model.AdoptionApplication;

// Adopter gui preparation
public class AdopterFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable tableAnimals;
    private DefaultTableModel animalModel;
    private JButton btnApplyAdoption;
    
    private Shelter shelter;

    /**
     * Create the frame.
     */
    public AdopterFrame(Shelter shelter) {
        this.shelter = shelter;

        setTitle("Adopter Portal - Find your perfect pet at " + shelter.getShelterName());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 420);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null); 
        
        JLabel lblAdopterframe = new JLabel("Adopter Dashboard");
        lblAdopterframe.setFont(new Font("Dialog", Font.BOLD, 20));
        lblAdopterframe.setBounds(20, 11, 300, 30);
        contentPane.add(lblAdopterframe);
        
        JLabel lblAvailable = new JLabel("Available Animals for Adoption:");
        lblAvailable.setBounds(20, 60, 250, 20);
        contentPane.add(lblAvailable);

        // ScrollPane και Πίνακας για τα Ζώα
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 90, 400, 260);
        contentPane.add(scrollPane);

        String[] columns = {"ID", "Name", "Type", "Age", "Description"};
        animalModel = new DefaultTableModel(columns, 0) {
            private static final long serialVersionUID = 1L;
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Κλειδωμένα κελιά για προστασία δεδομένων
            }
        };
        tableAnimals = new JTable(animalModel);
        scrollPane.setViewportView(tableAnimals);

        // Φόρτωση των ζώων στον πίνακα
        refreshTable();

        // Κουμπί Αίτησης Υιοθεσίας
        btnApplyAdoption = new JButton("Apply for Adoption");
        btnApplyAdoption.setBounds(435, 90, 145, 40);
        contentPane.add(btnApplyAdoption);
        
        btnApplyAdoption.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = tableAnimals.getSelectedRow();
                if (row != -1) {
                    String animalId = tableAnimals.getValueAt(row, 0).toString();
                    Animal targetAnimal = findAnimalById(animalId);
                    
                    if (targetAnimal != null) {
                    	
                        // Έλεγχος αν υπάρχουν εγγεγραμμένοι Adopters στη βάση
                        if (AdopterFrame.this.shelter.getAdopterList().isEmpty()) {
                            JOptionPane.showMessageDialog(AdopterFrame.this, "No adopters found in system database.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        
                        // Για την απλότητα του GUI, συνδέουμε την αίτηση με τον πρώτο adopter της λίστας
                        Adopter currentAdopter = AdopterFrame.this.shelter.getAdopterList().get(0);
                        
                        // Δημιουργία και προσθήκη της νέας αίτησης
                        AdoptionApplication newApp = new AdoptionApplication(currentAdopter, targetAnimal);
                        AdopterFrame.this.shelter.addApplication(newApp);
                        
                        // Αποθήκευση της νέας κατάστασης στο αρχείο
                        AdopterFrame.this.shelter.saveToFile("shelter_data.dat");
                        
                        JOptionPane.showMessageDialog(AdopterFrame.this, 
                                "Your adoption application for " + targetAnimal.getAnimalName() + " has been submitted successfully!", 
                                "Success", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(AdopterFrame.this, "Please select an animal from the list.", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    private void refreshTable() {
        animalModel.setRowCount(0);
        if (shelter.getAnimalList() != null) {
            for (int i = 0; i < shelter.getAnimalList().size(); i++) {
                Animal a = shelter.getAnimalList().get(i);
                
                // Χρήση των δικών σου μεθόδων getAnimalName() και getAnimalAge()
                Object[] row = { a.getId(), a.getAnimalName(), a.getType(), a.getAnimalAge(), a.getDescription() };
                animalModel.addRow(row);
            }
        }
    }

    private Animal findAnimalById(String id) {
        for (int i = 0; i < shelter.getAnimalList().size(); i++) {
            Animal a = shelter.getAnimalList().get(i);
            if (a.getId().equals(id)) return a;
        }
        return null;
    }
}