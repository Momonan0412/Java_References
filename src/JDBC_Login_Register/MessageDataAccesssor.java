package JDBC_Login_Register;
/**
 * WHAT TO DO?
 * User's input -> Database -> display?
 * User's input -> display -> Database
 * */
public class MessageDataAccesssor extends AbstractDataAccessor{

    private final String  tableMessages = "tblmessages";
    public MessageDataAccesssor(int port, String databaseName) {
        super(port, databaseName);
    }
    @Override
    public void insertData() {
    }
    @Override
    public void deleteData() {
    }
    @Override
    public void getData() {
    }
}
