package dao;

import model.Role;
import model.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import utils.HashUtil;
import utils.HibernateSessionFactoryUtil;

import java.io.Serializable;

public class RoleHibImpl {

    private static final Logger LOGGER = Logger.getLogger(UserDaoHibImpl.class);

    public void addRole(Role role) {

        try (Session session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession();){
            session.beginTransaction();
            session.save(role);
            session.getTransaction().commit();
            session.close();
            LOGGER.info("Role was saved");
        }
        catch (Exception e){
            LOGGER.error("Role was't saved", e);
        }
    }

    public Role findRole(User user) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Role.class, (Serializable) user);
    }
}
