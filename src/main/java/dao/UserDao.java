package dao;

import model.User;
import org.apache.log4j.Logger;
import utils.HashUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao {

    private static final Logger logger = Logger.getLogger(UserDao.class);

    private final static Connection connection = DbConnector.connect();

    public static void addUser(User user) {
        try {
            String sqlAdd = "INSERT INTO `mate_academy`.`users` (`name`,`surname`,`login`, `password`, `email`) VALUES (?,?,?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlAdd);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, HashUtil.getSHA512SecurePassword(user.getPassword()));
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.execute();
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
            String sqlUpdate = "UPDATE `mate_academy`.`users` SET `" + whatChange +"` = '" + newValue + "' " +
                                       "WHERE `login` = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate);
            preparedStatement.setString(1, login);
            preparedStatement.execute();
            logger.info(whatChange + "was update");
        } catch (SQLException e) {
            logger.error("Can't update values", e);
        }
    }

    public static void deleteAccount (String login){
        try {
            Statement statement = connection.createStatement();
            String sqlDeleteAccount = "DELETE FROM `mate_academy`.`users` WHERE `login` = '"+ login + "';";
            statement.execute(sqlDeleteAccount);
            logger.info("Account '" + login + "' deleted");
        } catch (SQLException e) {
            logger.error("Account '" + login + "' does't delete");
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
                String email = users.getString(6);
                list.add(new User(id, name, surname, login, password, email));
            }
            return Optional.of(list);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public static Optional<User> getUser(String login){
        try {
            Statement statement = connection.createStatement();
            String sqlSelectUser = "SELECT * FROM mate_academy.users where login = '" + login + "';";
            ResultSet user = statement.executeQuery(sqlSelectUser);
            if (user.next()) {
                int id = user.getInt(1);
                String name = user.getString(2);
                String surname = user.getString(3);
                String password = user.getString(5);
                String email = user.getString(6);
                User resUser = new User(id, name, surname, login, password, email);
                return Optional.of(resUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
            return Optional.empty();
    }
}