package model;

import java.io.Serializable;

public abstract class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String userPhone;
    private String email;

    // Setting up elements of user
    public User(String username ,String password,String firstname,String lastname,String userPhone,String email){
        this.username = username;
        this.password = password;
        this.firstName = firstname;
        this.lastName = lastname;
        setUserPhone(userPhone);
        setEmail(email);
    }
    
    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getFirstName(){
        return firstName; 
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
        return lastName; 
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getUserPhone(){
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        
        if (userPhone == null) {
            throw new IllegalArgumentException("Phone number cannot be null.");
        }

        
        if (userPhone.length() != 10) {
            throw new IllegalArgumentException("Phone number must be exactly 10 digits.");
        }

       
        for (char c : userPhone.toCharArray()) {
            if (!Character.isDigit(c)) {
                throw new IllegalArgumentException("Phone number must contain only digits.");
            }
        }

        this.userPhone = userPhone;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.length() < 5) { 
            throw new IllegalArgumentException("Email cannot be empty or too small.");
        }

        int atIndex = email.indexOf('@');
        int lastAtIndex = email.lastIndexOf('@');
        int dotIndex = email.lastIndexOf('.');

        if (atIndex == -1 || atIndex != lastAtIndex || atIndex == 0 || 
            dotIndex == -1 || dotIndex < atIndex + 1 || dotIndex == email.length() - 1) {
        
            throw new IllegalArgumentException("Invalid email.");
        }
        this.email = email;
    }
}