package model;
import java.util.Date;

public class Vaccination implements MedicalEntry {
    private Date date;
    private String vaccineName;
    private String vetName;

    public Vaccination(Date date, String vaccineName, String vetName) {
        this.date = date;
        this.vaccineName = vaccineName;
        this.vetName = vetName;
    }

   
    public Date getDate(){
         return date;
     }
    
    public String getDescription() { 
        return "Vaccination " + vaccineName;
     }
    
    public String getVet() { 
        return vetName; 
    }

}
