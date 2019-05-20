package dao;

import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HashUtil;
import utils.HibernateSessionFactoryUtil;

import java.util.List;
import java.util.Optional;

public class UserDaoHibImpl implements UserDao{


    @Override
    public void addUser(User user) {
        String hashPassword = HashUtil.getSHA512SecurePassword(user.getPassword());
        User userWithHashPassword = new User(user.getId(), user.getName(), user.getSurname(), user.getLogin(), hashPassword, user.getEmail(), user.getRole());
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(userWithHashPassword);
        tx1.commit();
        session.close();
    }

    @Override
    public void updateValue(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(user);
        tx1.commit();
        session.close();
    }

    @Override
    public void deleteAccount(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(user);
        tx1.commit();
        session.close();
    }

    @Override
    public Optional<User> getUser(String login) {
        User user = HibernateSessionFactoryUtil.getSessionFactory().openSession().get(User.class, login);
        return Optional.of(user);
    }

    @Override
    public Optional<List<User>> getAllUsers() {
        List<User> users = (List<User>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From User").list();
        return Optional.of(users);
    }
}
