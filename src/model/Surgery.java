package model;
import java.util.Date;

public class Surgery {
    private Date date;
    private String surgeryType;
    private String vetName;

    public Surgery(Date date, String surgeryType, String vetName) {
        this.date = date;
        this.surgeryType = surgeryType;
        this.vetName = vetName;
    }

   
    public Date getDate() { 
        return date; 
    }
    
    public String getDescription() {
        return "Surgery: " + surgeryType;
     }
 
    public String getVet() { 
        return vetName; 
    }
}


