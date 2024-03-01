package JDBC_Login_Register;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        String currentDatabase = "testdb";
        User user = null;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your port number: ");
        String portNumber = sc.nextLine();
//----------------------------------------------------------------------------------------------------------------------
        System.out.println("Enter username: ");
        String username = sc.nextLine();
        System.out.println("Enter password: ");
        String password = sc.nextLine();
        user = new User(username, password);
//----------------------------------------------------------------------------------------------------------------------
        System.out.println("'L' for Login and 'R' for Register: ");
        switch (sc.nextLine().charAt(0)){
            case 'L':
                UserDataAccesor userDataAccesor = new UserDataAccesor(portNumber, "testdb", user);
                Login login = new Login(user, userDataAccesor);
                login.loginChecker();
                break;
            case 'R':
                Register register = new Register(new UserDataAccesor(portNumber,currentDatabase, user));
                register.registerChecker();
                break;
        }
    }
}
