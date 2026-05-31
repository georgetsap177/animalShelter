package model;

import java.io.Serializable;

public class Animal implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String animalName;
    private AnimalType type;
    private int animalAge;
    private String description;
    private MedicalRecord medicalRecord;

    // Typing elements of animals
    public Animal(String id,String animalName, AnimalType type, int animalAge , String description){
        this.id = id;
        this.animalName = animalName;
        this.type = type;
        this.setAnimalAge(animalAge);
        this.description = description;
        this.medicalRecord = new MedicalRecord();
    }
    
    public String getId(){
        return id;
    }
    public void setId (String id){
        this.id = id;
    }
    public String getAnimalName(){
        return animalName;
    }
    public void setAnimalName(String animalName){
        this.animalName = animalName;
    }
    public AnimalType getType(){
        return type;
    }
    public void setType(AnimalType type){
        this.type = type;
    }
    public int getAnimalAge(){
        return animalAge;
    }
    public void setAnimalAge(int animalAge){
        if(animalAge >= 0 ){
            this.animalAge = animalAge;
        }
        else{
            throw new IllegalArgumentException();
        }
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public MedicalRecord getMedicalRecord(){
        return medicalRecord;
    }
    public void setMedicalRecord(MedicalRecord medicalRecord){
        this.medicalRecord = medicalRecord;
    }
}