package logic;

import java.io.*;
import java.util.ArrayList;
import models.*;

public class Shelter implements Serializable{
    private String shelterName;
    private String address;
    private String phone;


    public Shelter(String shelterName, String address, String phone){
        this.shelterName = shelterName;
        this.address = address;
        this.phone = phone;
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

