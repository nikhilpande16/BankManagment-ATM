
import java.sql.*;

public class DBConnection {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/BankManagmentSystem";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "June@1606";

//    public DBConnection(){
//        System.out.println("Attempting to connect");
//
//        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
//            System.out.println("Connection Successfull");
//        }catch (SQLException e){
//            System.err.println("Database connection or operation failed!");
//            System.err.println("SQL State: " + e.getSQLState());
//            System.err.println("Error Code: " + e.getErrorCode());
//            System.err.println("Message: " + e.getMessage());
//            // Optionally print stack trace for detailed debugging
//            e.printStackTrace();
//        }
//    }

//    public static void main(String[] args) {
//
//    }

    public Connection getConnection() {
        try {
            Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Connection Successful");
            return con;
        } catch (SQLException e) {
            System.err.println("Connection Failed!");
            e.printStackTrace();
            return null;
        }
    }

}
