package JDBC_Login_Register;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.*;

public abstract class AbstractDataAccessor {
    public PreparedStatement preparedStatement;
    private String URL;
    private String name;
    private String password;
    private String query;
    public Connection con;
    private int portNumber;

    public AbstractDataAccessor(int portNumber, String databaseName){
        String localIpAddress = getLocalIPv4Address();
        this.portNumber = portNumber;
// --------------------------------------------------------------------------------------------------------------------
        /** SHOW VARIABLES LIKE "port"; -> use to show the current mysql port. **/
        this.URL = "jdbc:mysql://localhost:" + this.portNumber + "/" + databaseName;
// --------------------------------------------------------------------------------------------------------------------
        /** Should be changed base in the device's name and password. **/
        this.name = "root";
        this.password = "Chua123";
    }
    public void getConnection(String query){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, name, password);
            preparedStatement = con.prepareStatement(query);
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }

// --------------------------------------------------------------------------------------------------------------------
    /** Used in getting the dvice's IPv4 address ..... unless? **/
    private static String getLocalIPv4Address() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return null;
        }
    }
// --------------------------------------------------------------------------------------------------------------------
    public abstract void insertData();

    public abstract void deleteData(); // To-do what should be the usage?

    public abstract void getData();
// --------------------------------------------------------------------------------------------------------------------

    public String getQuery() {
        return query;
    }
    public void setQuery(String query) {
        this.query = query;
    }
}
