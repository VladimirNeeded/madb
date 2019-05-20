package dao;

import model.User;
import org.apache.log4j.Logger;
import utils.HashUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoSQL implements UserDao{

    private static final Logger LOGGER = Logger.getLogger(UserDaoSQL.class);

    private final static Connection connection = DbConnector.connect();

    @Override
    public void addUser(User user) {
        try {
            String sqlAdd = "INSERT INTO `mate_academy`.`users` (`name`,`surname`,`login`, `password`, `email`) VALUES (?,?,?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlAdd);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, HashUtil.getSHA512SecurePassword(user.getPassword()));
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.execute();
            LOGGER.info("User '" + user.getLogin() + "' added to DB");
        } catch (SQLException e) {
            LOGGER.error("Can't add user to DB", e);
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
            LOGGER.error("Can't get user's password", e);
        }
        return Optional.empty();
    }

    @Override
    public void updateValue (User user){
        try {
            String sqlUpdate = "UPDATE `mate_academy`.`users` SET `name` = ?, `surname` = ?, `password` = ? WHERE (`id` = ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setInt(4, user.getId());
            preparedStatement.execute();
            LOGGER.info(user.getLogin() + " was update");
        } catch (SQLException e) {
            LOGGER.error("Can't update values", e);
        }
    }

    @Override
    public void deleteAccount (User user){
        try {
            Statement statement = connection.createStatement();
            String sqlDeleteAccount = "DELETE FROM `mate_academy`.`users` WHERE `id` = '"+ user.getId() + "';";
            statement.execute(sqlDeleteAccount);
            LOGGER.info("Account '" + user.getLogin() + "' deleted");
        } catch (SQLException e) {
            LOGGER.error("Account '" + user.getLogin() + "' does't delete");
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

    @Override
    public Optional<List<User>> getAllUsers() {
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
                String role = users.getString(7);
                list.add(new User(id, name, surname, login, password, email, role));
            }
            return Optional.of(list);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> getUser(String login){
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
                String role = user.getString(7);
                User resUser = new User(id, name, surname, login, password, email, role);
                return Optional.of(resUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
            return Optional.empty();
    }
}