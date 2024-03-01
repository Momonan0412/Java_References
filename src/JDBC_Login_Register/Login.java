package JDBC_Login_Register;
/**
 * Uses of getData method in UserDataAccesor Class to handle the logic login.
 **/
public class Login {
    User user;
    UserDataAccesor userDataAccesor;
    public Login(User user, UserDataAccesor userDataAccesor){
        this.user = user;
        this.userDataAccesor = userDataAccesor;
    }
    public void loginChecker(){
        System.out.println("loginChecker Method");
        this.userDataAccesor.setUser(this.user);
        this.userDataAccesor.getData();
    }
}
