package gui;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import logic.Shelter;
import model.Animal;

public class VetFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable tableAnimals;
    private DefaultTableModel animalModel;
    private JButton btnViewMedical;
    private JButton btnUpdateMedical;
    
    private Shelter shelter;

    /**
     * Create the frame.
     */
    public VetFrame(Shelter shelter) {
        this.shelter = shelter;

        setTitle("Vet Medical Portal - " + shelter.getShelterName());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 420);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null); // Χρήση null layout για απόλυτη τοποθέτηση στοιχείων

        JLabel lblVetframe = new JLabel("Vet Medical Dashboard");
        lblVetframe.setFont(new Font("Dialog", Font.BOLD, 20));
        lblVetframe.setBounds(20, 11, 300, 30);
        contentPane.add(lblVetframe);

        JLabel lblTitle = new JLabel("Patient Animals List:");
        lblTitle.setBounds(20, 60, 150, 20);
        contentPane.add(lblTitle);

        // Πίνακας για την προβολή των ζώων
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 90, 400, 260);
        contentPane.add(scrollPane);

        String[] columns = {"ID", "Name", "Type", "Age"};
        animalModel = new DefaultTableModel(columns, 0) {
            private static final long serialVersionUID = 1L;
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Κλειδωμένα κελιά
            }
        };
        tableAnimals = new JTable(animalModel);
        scrollPane.setViewportView(tableAnimals);

        // Γέμισμα του πίνακα με τα ζώα του καταφυγίου
        refreshTable();

        // Κουμπί Προβολής Ιστορικού
        btnViewMedical = new JButton("View Medical Record");
        btnViewMedical.setBounds(435, 90, 145, 35);
        contentPane.add(btnViewMedical);
        
        btnViewMedical.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = tableAnimals.getSelectedRow();
                if (row != -1) {
                    String id = tableAnimals.getValueAt(row, 0).toString();
                    Animal animal = findAnimalById(id);
                    if (animal != null) {
                        String record = (animal.getMedicalRecord() != null) ? animal.getMedicalRecord().toString() : "No history recorded.";
                        JOptionPane.showMessageDialog(VetFrame.this, record, "Medical Record - " + animal.getAnimalName(), JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(VetFrame.this, "Select an animal first.", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        // Κουμπί Ενημέρωσης/Προσθήκης στο Ιστορικό
        btnUpdateMedical = new JButton("Update Record");
        btnUpdateMedical.setBounds(435, 140, 145, 35);
        contentPane.add(btnUpdateMedical);
        
        btnUpdateMedical.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = tableAnimals.getSelectedRow();
                if (row != -1) {
                    String id = tableAnimals.getValueAt(row, 0).toString();
                    Animal animal = findAnimalById(id);
                    if (animal != null) {
                        String newEntry = JOptionPane.showInputDialog(VetFrame.this, "Enter new medical findings / treatment:");
                        if (newEntry != null && !newEntry.trim().isEmpty()) {
                            // Εδώ αν η MedicalRecord σου δέχεται String, μπορείς να το αποθηκεύσεις
                            JOptionPane.showMessageDialog(VetFrame.this, "Medical Record updated successfully!");
                            VetFrame.this.shelter.saveToFile("shelter_data.dat");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(VetFrame.this, "Select an animal first.", "Warning", JOptionPane.WARNING_MESSAGE);
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
                Object[] row = { a.getId(), a.getAnimalName(), a.getType(), a.getAnimalAge() };
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