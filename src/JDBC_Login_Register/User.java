package JDBC_Login_Register;

public class User {
    private String Username;
    private String Password;
    int identificationNumber;
    public User(String username, String password) {
        Username = username;
        Password = password;
    }
    public String getUsername() {
        return Username;
    }
    public String getPassword() {
        return Password;
    }

}
