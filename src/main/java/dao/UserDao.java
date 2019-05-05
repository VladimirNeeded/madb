package dao;

import model.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao {

    private static final Logger logger = Logger.getLogger(UserDao.class);

    private final static Connection connection = DbConnector.connect();

    public static void addUser(User user) {
        try {
            Statement statement = connection.createStatement();
            String sqlAdd = "INSERT INTO `mate_academy`.`users` (`name`,`surname`,`login`, `password`) " +
                            "VALUES ('" + user.getName() + "', '" + user.getSurname() +"', '"
                                        + user.getLogin() + "', '" + user.getPassword() + "');";
            statement.execute(sqlAdd);
            logger.info("User '" + user.getLogin() + "' added to DB");
        } catch (SQLException e) {
            logger.error("Can't add user to DB", e);
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
            logger.error("Can't get user's password", e);
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
    public static Optional<String> selectRole (String login) {
        try {
            Statement statement = connection.createStatement();
            String sqlSelect = "SELECT roles.name " +
            "FROM users " +
            "INNER JOIN roles " +
            "ON users.role_id = roles.id " +
            "WHERE login = '" + login + "';";
            ResultSet resRole = statement.executeQuery(sqlSelect);
            if (resRole.next()) {
                Optional<String> role = Optional.of(resRole.getString(1));
                return role;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
    public static Optional<List<User>> selectUsers() {
        try {
            List<User> list = new ArrayList<>();
            Statement statement = connection.createStatement();
            String sqlSelectUsers = "SELECT * FROM mate_academy.users;";
            ResultSet users = statement.executeQuery(sqlSelectUsers);
            while (users.next()) {
                int id = users.getInt(1);
                String name = users.getString(2);
                String surname = users.getString(3);
                String login = users.getString(4);
                String password = users.getString(5);
                list.add(new User(id, name, surname, login, password));
            }
            return Optional.of(list);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}