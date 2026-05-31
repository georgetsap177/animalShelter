package model;

import java.io.Serializable; 
import java.util.Date;

public interface MedicalEntry extends Serializable {
    Date getDate();
    String getDescription();
    String getVet();
}