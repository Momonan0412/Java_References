package JDBC_Login_Register;

/**
 * Return's User object and should be inserted in the database.
 **/
public class Register {
    User user;
    UserDataAccesor userDataAccesor;
    public Register(UserDataAccesor userDataAccesor){
        this.user = userDataAccesor.user;
        this.userDataAccesor = userDataAccesor;
    }
    public void registerChecker(){
        System.out.println("registerChecker Method");
        this.userDataAccesor.setUser(this.user);
        this.userDataAccesor.insertData();
    }
}
