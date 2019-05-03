package dao;

import model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class UserDao {

    private final static Connection connection = DbConnector.connect();

    public static void addUser(User user) {
        try {
            Statement statement = connection.createStatement();
            String sqlAdd = "INSERT INTO `mate_academy`.`users` (`name`,`surname`,`login`, `password`) " +
                            "VALUES ('" + user.getName() + "', '" + user.getSurname() +"', '"
                                        + user.getLogin() + "', '" + user.getPassword() + "');";
            statement.execute(sqlAdd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Optional<String> selectPassword (String login) {
        try {
            Statement statement = connection.createStatement();
            String sqlSelect = "SELECT password FROM mate_academy.users WHERE login = '" + login + "';";
            ResultSet resultPassword = statement.executeQuery(sqlSelect);
            while (resultPassword.next()) {
                Optional<String> resPass = Optional.of(resultPassword.getString("password"));
                return resPass;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public static void updateValue (String whatChange, String newValue, String login){
        try {
            Statement statement = connection.createStatement();
            String sqlUpdatePassword = "UPDATE `mate_academy`.`users` SET `" + whatChange +"` = '" + newValue + "' " +
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