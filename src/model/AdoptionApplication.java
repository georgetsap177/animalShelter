package model;

import java.io.Serializable;
import java.util.Date;

public class AdoptionApplication implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private Adopter adopter;
    private Animal animal;
    private Date submissionDate;
    private ApplicationStatus status;

    // Constructor για τη δημιουργία νέας αίτησης υιοθεσίας
    public AdoptionApplication(Adopter adopter, Animal animal) {
        this.adopter = adopter;
        this.animal = animal;
        this.submissionDate = new Date(); // Καταγράφει αυτόματα την τρέχουσα ημερομηνία/ώρα
        this.status = ApplicationStatus.PENDING; // Κάθε νέα αίτηση ξεκινάει ως PENDING
    }

    // --- GETTERS & SETTERS ---

    public Adopter getAdopter() {
        return adopter;
    }

    public void setAdopter(Adopter adopter) {
        this.adopter = adopter;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Date getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Date submissionDate) {
        this.submissionDate = submissionDate;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }
}