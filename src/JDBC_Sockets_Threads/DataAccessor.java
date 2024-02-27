package JDBC_Sockets_Threads;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.*;

public class DataAccessor {
    private PreparedStatement preparedStatement;
    private String URL;
    private String name;
    private String password;
    private String query;
    private Connection con;
    private String databaseName;

    public DataAccessor(Object port, String databaseName){
        this.databaseName = databaseName;
        String localIpAddress = getLocalIPv4Address();
// --------------------------------------------------------------------------------------------------------------------
        /** """ SHOW VARIABLES LIKE "port"; """ -> use to show the current mysql port. **/
//        this.URL = "jdbc:mysql://" + localIpAddress + ":" + port + "/" + databaseName;
        this.URL = "jdbc:mysql://localhost:3306/testdb";
        System.out.println(port + " " + localIpAddress);
// --------------------------------------------------------------------------------------------------------------------
        /** Should be changed base in the device's name and password. **/
        this.name = "root";
        this.password = "Chua123";
    }
    private void getConnection(String query){
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

    public void insertData(int userID, String message){
// --------------------------------------------------------------------------------------------------------------------
        /**"message" refers to the columns name of the database.**/
        this.query ="INSERT INTO " + databaseName + ".tblmessages (userID, message) " + " VALUE (?, ?)";
// --------------------------------------------------------------------------------------------------------------------
        try{
            this.getConnection(query);
            /** the "1" is base in the first "?" and the "2" is base in the next "?" **/
            preparedStatement.setInt(1, userID);
            preparedStatement.setString(2, message);
            int flag = preparedStatement.executeUpdate();
            if(flag > 0){ // if the "flag" here is greater than zero than a "row" was inserted in database
                System.out.println("Data inserted successfully!");
            } else {
                System.out.println("Failed to insert data.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteData(int msgID, int userID){
        this.query = "DELETE FROM " + databaseName + " WHERE msgID = ? AND userID = ?";
        try{
            this.getConnection(this.query);
// --------------------------------------------------------------------------------------------------------------------
            preparedStatement.setInt(1, msgID);
            preparedStatement.setInt(2, userID);
// --------------------------------------------------------------------------------------------------------------------
            int flag = preparedStatement.executeUpdate();
            if (flag > 0) {
                System.out.println("Data deleted successfully!");
            } else {
                System.out.println("Failed to delete data.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getData() {
        this.query = "SELECT tblmessages.message, tblusers.userName " +
                "FROM " + databaseName + ".tblmessages " +
                "JOIN " + databaseName + ".tblusers ON tblmessages.userID = tblusers.userID";

        getConnection(this.query);

        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                // Retrieve data from the result set
                String messageValue = resultSet.getString("message");
                String userName = resultSet.getString("userName");

                // Process or print the retrieved data
                System.out.println("Message: " + messageValue + ", UserName: " + userName);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
