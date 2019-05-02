package dao;

import model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDao {

    private final static Connection connection = DbConnector.connect();

    public static void addUser(User user) {
        try {
            Statement statement = connection.createStatement();
            String sqlAdd = "INSERT INTO `mate_academy`.`users` (`login`, `password`) " +
                            "VALUES ('" + user.getLogin() + "', '" + user.getPassword() + "');";
            statement.execute(sqlAdd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String selectPassword (String login) {
        try {
            Statement statement = connection.createStatement();
            String sqlSelect = "SELECT password FROM mate_academy.users WHERE login = '" + login + "';";
            ResultSet resultPassword = statement.executeQuery(sqlSelect);
            while (resultPassword.next()) {
                String resPass = resultPassword.getString("password");
                return resPass;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void updatePassword (String newPassword, String login){
        try {
            Statement statement = connection.createStatement();
            String sqlUpdatePassword = "UPDATE `mate_academy`.`users` SET `password` = '" + newPassword + "' " +
                                       "WHERE `login` = '" + login + "';";
            statement.execute(sqlUpdatePassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteAccount (String login){
        try {
            Statement statement = connection.createStatement();
            String sqlDeleteAccount = "DELETE FROM `mate_academy`.`users` WHERE `login` = '"+ login + "';";
            statement.execute(sqlDeleteAccount);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}