package dao;

import model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    public void addUser(User user);

    public void updateValue (User user);

    public void deleteAccount (User user);

    public Optional<User> getUser(int id);

    public Optional<List<User>> getAllUsers();
}
