package JDBC_Sockets_Threads;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your port number: ");
        String portNumber = sc.nextLine();
        DataAccessor DA = new DataAccessor(portNumber, "testdb");
        /**
         * THIS ERROR WHEN A USER WITH USERID DOES NOT EXIST**/
        DA.insertData(5, "hello");
        /**
         * HOW TO HANDLE THE "tbluers" ?
         * **/
    }
}
