package model;

import java.io.Serializable;
import java.util.ArrayList;

public class MedicalRecord implements Serializable {
    private static final long serialVersionUID = 1L;

    private ArrayList<MedicalEntry> entries;

    public MedicalRecord(){
        this.entries = new ArrayList<>();
    }
    public ArrayList<MedicalEntry> getEntries(){
        return entries;
    }
    public void addEntry(MedicalEntry entry){
        if(entry != null){
            this.entries.add(entry);
        }
    }
}