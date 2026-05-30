package model;

// Preparation of Adopter menu
public class Adopter extends User{

    private String userAddress;
    private String profileDescription;
    private int age;


    public Adopter(String username, String password, String firstname, String lastname,String userPhone,String email,
        String userAddress,String profileDescription,int age) {
        super(username, password, firstname, lastname,userPhone,email);
        this.userAddress = userAddress;
        this.profileDescription = profileDescription;
        setAge(age);
    }

    public String getUserAddress(){
     return userAddress; 
    }
    public void setAddress(String userAddress) {
     this.userAddress = userAddress; 

    }

    public String getProfileDescription(){
     return profileDescription; 
    }
    public void setProfileDescription(String profileDescription){
     this.profileDescription = profileDescription; 
    }

    public int getAge(){ 
     return age; 
    }
    public void setAge(int age) {
        if (age >= 18 && age <= 100) {
            this.age = age;
        } 
        else {
            throw new IllegalArgumentException("Invalid age.");
        }
    }
    
    
}
