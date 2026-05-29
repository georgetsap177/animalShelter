package logic;

import java.io.*;

import java.util.ArrayList;

import model.*;

public class Shelter implements Serializable{
    private String shelterName;
    private String address;
    private String phone;
  
    private ArrayList<Vet> vetList;
    private ArrayList<Adopter> adopterList;
    private ArrayList<Animal> animalList;
    private ArrayList<ShelterStaff> staffList;


    public Shelter(String shelterName, String address, String phone){
        this.shelterName = shelterName;
        this.address = address;
        this.phone = phone;
        
        this.vetList = new ArrayList<>();
        this.adopterList = new ArrayList<>(); 
        this.animalList = new ArrayList<>();
        this.staffList = new ArrayList<>();
        
    }
    
    public void findUser(String usr, String pass){
    	int vIndex = findInVetList(usr, pass);
    	
    	if ( vIndex >= 0 ) {
    		System.out.println("User is vet");
    	}
    }
    
    public int findInVetList(String usr, String pass){
    	int i;
    	for (i=0; i<this.vetList.size(); i++) {
    		Vet v = this.vetList.get(i);
    		
    		System.out.println(v.getUsername());
    	}
    	
    }
    
    //add Staff to shelter list
    public void addShelterStaff(ShelterStaff s) {
    	staffList.add(s);
    }
    
    //add Vet to shelter list
    public void addVet(Vet v) {
    	vetList.add(v);
    }
    
    //add Adopter to shelter list
    public void addAdopter(Adopter a) {
    	adopterList.add(a);
    }
    
    //add Animal to shelter list    
    public void addAnimal(Animal a) {
    	animalList.add(a);
    }

    public String getShelterName(){
        return this.shelterName;
    }

    public void setShelterName(String name){
        this.shelterName = name;
    }

    public String getAddress(){
        return this.address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getPhone(){
        return this.phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public void saveToFile(){
        public void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("shelter_data.ser"))) {
            oos.writeObject(this);
            System.out.println("Data saved successfully!");
        } catch (IOException e) {
            System.err.println("Error saving: " + e.getMessage());
        }
    }

    public static Shelter loadFromFile() {
        java.io.File file = new java.io.File("shelter_data.ser");
        if (!file.exists()) {
            System.out.println("No file found.");
            return null;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (Shelter) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading: " + e.getMessage());
            return null;
        }
    }
    }
}

