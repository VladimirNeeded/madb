package dao;

import model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class UserDao {

    public void addUser(User user) {
        Connection connection = DbConnector.connect();
        try {
            Statement statement = connection.createStatement();
            String sqlAdd = "INSERT INTO `mate_academy`.`users` (`login`, `password`) VALUES ('" + user.getLogin() + "', '" + user.getPassword() + "');";
            System.out.println(sqlAdd);
            statement.execute(sqlAdd);
        } catch (SQLException e) {
            System.out.println("SQLException//////////////////////////////////");
        }
    }
}