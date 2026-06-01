package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logic.Shelter;
import model.Animal;
import model.AnimalType;

import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.GridLayout;    
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import model.*;

public class MainJframe extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField usrTextField;
    private JPasswordField passwordField;
    private Shelter s1; 

    /**
     * Launch the application.
     */
    public static void main(String[] args) {    
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // 1. Έλεγχος αν υπάρχει ήδη το αρχείο δεδομένων
                    java.io.File file = new java.io.File("shelter_data.dat");
                    boolean fileExists = file.exists();
                    
                    // 2. Φόρτωση του καταφυγίου από το αρχείο
                    Shelter shelter = Shelter.loadFromFile("shelter_data.dat", "Arktouros", "L. Petrou Ralli", "2104879587");
                    
                    // 3. Αρχικοποίηση δεδομένων ΜΟΝΟ αν το αρχείο δεν υπήρχε (πρώτη εκκίνηση της εφαρμογής)
                    if (!fileExists) {
                        System.out.println("Πρώτη εκκίνηση: Δημιουργία αρχικών δεδομένων...");
                        
                        // Δημιουργία και Προσθήκη Χρηστών
                        ShelterStaff ss1 = new ShelterStaff("staff1", "123", "First staff1", "Last staff1", "6984568741", "staff1@shelter.gr");
                        ShelterStaff ss2 = new ShelterStaff("staff2", "123", "First staff2", "Last staff2", "6958764542", "staff2@shelter.gr");
                        shelter.addShelterStaff(ss1);
                        shelter.addShelterStaff(ss2);

                        Vet v1 = new Vet("vet1", "123", "First vet1", "Last vet1", "6987456258", "vet1@gmail.com");        
                        Vet v2 = new Vet("vet2", "456", "First vet2", "Last vet2", "6987480697", "vet2@gmail.com");        
                        shelter.addVet(v1);
                        shelter.addVet(v2);

                        Adopter ad1 = new Adopter("ad1", "123", "First ad1", "Last ad1", "6914782587", "ad1@yahoo.com", "Address adopter 1", "First adopter in shelter", 48);
                        Adopter ad2 = new Adopter("ad2", "789", "First ad2", "Last ad2", "6914789007", "ad2@yahoo.com", "Address adopter 2", "Second adopter in shelter", 45);
                        shelter.addAdopter(ad1);
                        shelter.addAdopter(ad2);
                        
                        // Δημιουργία και Προσθήκη Κατοικιδίων
                        shelter.addAnimal(new Animal("1", "Ben", AnimalType.DOG, 5, "Golden retriever"));        
                        shelter.addAnimal(new Animal("2", "Max", AnimalType.CAT, 7, "Siamezikh"));        
                        shelter.addAnimal(new Animal("3", "Luna", AnimalType.CAT, 2, "Friendly black cat"));        
                        shelter.addAnimal(new Animal("4", "Charlie", AnimalType.DOG, 3, "Playful German Shepherd"));        
                        shelter.addAnimal(new Animal("5", "Bella", AnimalType.DOG, 1, "Cute puppy"));        
                        shelter.addAnimal(new Animal("6", "Rocky", AnimalType.BIRD, 1, "Green parrot"));        
                        shelter.addAnimal(new Animal("7", "Coco", AnimalType.CAT, 4, "Calm white cat"));        
                        shelter.addAnimal(new Animal("8", "Milo", AnimalType.DOG, 6, "Energetic husky"));        
                        shelter.addAnimal(new Animal("9", "Daisy", AnimalType.CAT, 3, "Very independent cat"));        
                        shelter.addAnimal(new Animal("10", "Simba", AnimalType.BIRD, 2, "Canary that sings nicely"));        
                        
                        // Πρώτο σώσιμο στον δίσκο
                        shelter.saveToFile("shelter_data.dat");
                    } else {
                        System.out.println("Το αρχείο βρέθηκε! Όνομα καταφυγίου στη μνήμη: " + shelter.getShelterName());
                    }
                    
                    // Ανοίγουμε το παράθυρο περνώντας το σωστό αντικείμενο
                    MainJframe frame = new MainJframe(shelter);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public MainJframe(Shelter loadedShelter){
    	setRootPaneCheckingEnabled(true);          
        // Κλειδώνουμε το instance που φορτώθηκε από το αρχείο
        this.s1 = loadedShelter; 
        
        setTitle("Animal shelter - " + s1.getShelterName()); // Δυναμικός τίτλος για να βλέπεις άμεσα αν άλλαξε!
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 358, 208);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        
        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.CENTER);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
        gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
        gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        panel.setLayout(gbl_panel);
        
        JLabel lblUsername = new JLabel("Username");
        GridBagConstraints gbc_lblUsername = new GridBagConstraints();
        gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
        gbc_lblUsername.anchor = GridBagConstraints.EAST;
        gbc_lblUsername.gridx = 3;
        gbc_lblUsername.gridy = 2;
        panel.add(lblUsername, gbc_lblUsername);
        
        usrTextField = new JTextField();
        GridBagConstraints gbc_usrTextField = new GridBagConstraints();
        gbc_usrTextField.anchor = GridBagConstraints.WEST;
        gbc_usrTextField.insets = new Insets(0, 0, 5, 0);
        gbc_usrTextField.gridx = 4;
        gbc_usrTextField.gridy = 2;
        panel.add(usrTextField, gbc_usrTextField);
        usrTextField.setColumns(15);
        
        JLabel lblPassword = new JLabel("Password");
        GridBagConstraints gbc_lblPassword = new GridBagConstraints();
        gbc_lblPassword.anchor = GridBagConstraints.EAST;
        gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
        gbc_lblPassword.gridx = 3;
        gbc_lblPassword.gridy = 3;
        panel.add(lblPassword, gbc_lblPassword);
        
        passwordField = new JPasswordField();
        passwordField.setColumns(15);
        GridBagConstraints gbc_passwordField = new GridBagConstraints();
        gbc_passwordField.insets = new Insets(0, 0, 5, 0);
        gbc_passwordField.anchor = GridBagConstraints.WEST;
        gbc_passwordField.gridx = 4;
        gbc_passwordField.gridy = 3;
        panel.add(passwordField, gbc_passwordField);
        
        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usernameInput = usrTextField.getText().trim();
                String passwordInput = new String(passwordField.getPassword()).trim();
                
                String res = s1.findUser(usernameInput, passwordInput);
                
                if ( res.equals("STAFF") ){
                    MainJframe.this.dispose(); 
                    StaffFrame sf = new StaffFrame(s1); 
                    sf.setVisible(true);
                }
                else if ( res.equals("VET") ){
                    MainJframe.this.dispose();
                    VetFrame vf = new VetFrame(s1);
                    vf.setVisible(true);
                }
                else if ( res.equals("ADOPTER") ){
                    MainJframe.this.dispose();
                    AdopterFrame af = new AdopterFrame(s1, null);
                    af.setVisible(true);
                }
                else{
                    System.out.println("Wrong username or password");
                }
            }
        });
        GridBagConstraints gbc_btnLogin = new GridBagConstraints();
        gbc_btnLogin.anchor = GridBagConstraints.WEST;
        gbc_btnLogin.gridx = 4;
        gbc_btnLogin.gridy = 4;
        panel.add(btnLogin, gbc_btnLogin);
        
        // Setting default button the Login button in order to get pressed when user hits enter key 
    	getRootPane().setDefaultButton(btnLogin);     
    }
}