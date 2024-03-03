package JDBC_Login_Register;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDataAccesor extends AbstractDataAccessor {
    private final String  tableUsers = "tblusers";
    User user;
    public UserDataAccesor(int port, String databaseName, User user) {
        super(port, databaseName);
        this.user = user;
    }

    @Override
    public void insertData() {
        insertData(user.getUsername(), user.getPassword());
    }
    public void insertData(String username, String password) {
        super.setQuery("INSERT INTO " + tableUsers + " (userName, password_hash) VALUE (?, ?)"); // MYSQL SYNTAX FOR INSERTING
        super.getConnection(super.getQuery());
        try {
            super.preparedStatement.setString(1, username);
            super.preparedStatement.setString(2, password);
            int rowAffected = super.preparedStatement.executeUpdate();
            if (rowAffected > 0) {
                System.out.println("Data inserted successfully!"); // TO-DO: Use Logging instead of System.out.println
            } else {
                System.out.println("Failed to insert data."); // TO-DO: Use Logging instead of System.out.println
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // NO NEED TO EXPLICITLY HANDLE THE "CLOSE" SINCE IT WOULD AUTOMATICALLY DO IT
    }
    @Override
    public void deleteData() {
        deleteData(user.identificationNumber);
    }
    public void deleteData(int identificationNumber) {
        super.setQuery("DELETE FROM user WHERE iduser = ?");
        super.getConnection(super.getQuery());
        try{
            super.preparedStatement.setInt(1, identificationNumber);
            int rowAffected = super.preparedStatement.executeUpdate();
            if (rowAffected > 0) {
                System.out.println("Data deleted successfully!"); // TO-DO: Use Logging instead of System.out.println
            } else {
                System.out.println("Failed to delete data."); // TO-DO: Use Logging instead of System.out.println
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        // NO NEED TO EXPLICITLY HANDLE THE "CLOSE" SINCE IT WOULD AUTOMATICALLY DO IT
    }
    @Override
    public void getData() {
        getData(user.getUsername(), user.getPassword());
    }
    public void getData(String username, String password) {
        super.setQuery("SELECT * FROM " + tableUsers + " WHERE userName = ? AND password_hash = ?");
        super.getConnection(getQuery());
        try{
            super.preparedStatement.setString(1, username);
            super.preparedStatement.setString(2, password);
            ResultSet resultSet = super.preparedStatement.executeQuery();
            if (resultSet.next()) { // if resultSet.next() is true
                // User with provided username and password found
                System.out.println("Login Success!");
            } else { // if resultSet.next() is false
                // No user found with the provided username and password
                System.out.println("Login Failed!");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
