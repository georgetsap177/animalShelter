package model;
import java.util.Date;

public class AdoptionApplication {
    private Adopter adopter;
    private Animal animal;
    private Date submissionDate;
    private ApplicationStatus status;

    public AdoptionApplication(Adopter adopter, Animal animal) {
        this.adopter = adopter;
        this.animal = animal;
        this.submissionDate = new Date(); 
        this.status = ApplicationStatus.PENDING;
    }
    public Adopter getAdopter(){
        return adopter;
    }
    public void setAdopter(Adopter adopter){
        this.adopter = adopter;
    }
    public Animal getAnimal(){
        return animal;
    }
    public void setAnimal(Animal animal){
        this.animal = animal;
    }
    public Date getSubmissionDate(){
        return submissionDate;
    }
    public void setSubmissionDate(Date submissionDate){
        this.submissionDate = submissionDate;
    }
    public ApplicationStatus getStatus(){
        return status;
    }
    public void setStatus(ApplicationStatus status){
        this.status = status;
    }
}
