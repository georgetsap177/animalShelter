package model;

public class Vet extends User {

    private static final long serialVersionUID = 1L;

    public Vet(String username, String password, String firstname, String lastname, String userPhone, String email) {
        super(username, password, firstname, lastname, userPhone, email);
    }
}