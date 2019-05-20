package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnector {
    private static final String DbURL = "jdbc:mysql://localhost:3306/mate_academy?useSSL=false";
    private static final String NAME = "root";
    private static final String PASSWORD = "4815162342";

    public static Connection connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(DbURL, NAME, PASSWORD);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}