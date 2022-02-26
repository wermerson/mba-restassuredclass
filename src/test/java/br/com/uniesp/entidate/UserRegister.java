package br.com.uniesp.entidate;

public class UserRegister {
    private String email;
    private String password;

    public UserRegister(String email, String password){
        super();
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
