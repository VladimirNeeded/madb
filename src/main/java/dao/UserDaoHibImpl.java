package dao;

import model.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.internal.SessionImpl;
import utils.HashUtil;
import utils.HibernateSessionFactoryUtil;

import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class UserDaoHibImpl implements UserDao {

    private static final Logger LOGGER = Logger.getLogger(UserDaoHibImpl.class);

    @Override
    public void addUser(User user) {
        String hashPassword = HashUtil.getSHA512SecurePassword(user.getPassword());
        User userWithHashPassword = new User(user.getId(), user.getName(), user.getSurname(), user.getLogin(), hashPassword, user.getEmail(), user.getRole());
        try (Session session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession();){
            session.beginTransaction();
            session.save(userWithHashPassword);
            session.getTransaction().commit();
            session.close();
        }
        catch (Exception e){
            LOGGER.error("User was't added");
        }
    }

    @Override
    public void updateValue(User user) {
        try (Session session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession();){
            Transaction tx1 = session.beginTransaction();
            session.update(user);
            tx1.commit();
            session.close();
        }
        catch (Exception e){
            LOGGER.error("Values were not updated");
        }
    }

    @Override
    public void deleteAccount(User user) {
        try (Session session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession();){
            Transaction tx1 = session.beginTransaction();
            session.delete(user);
            tx1.commit();
            session.close();
        }
        catch (Exception e){
            LOGGER.error("Account was't deleted");
        }
    }

    @Override
    public Optional<User> getUser(int id) {
        User user = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()
                .get(User.class, id);
        return Optional.of(user);
    }

    @Override
    public Optional<List<User>> getAllUsers() {
        List<User> users = (List<User>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From User").list();
        return Optional.of(users);
    }
}
