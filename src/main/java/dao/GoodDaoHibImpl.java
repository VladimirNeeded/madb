package dao;

import model.Goods;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;
import java.util.Optional;

public class GoodDaoHibImpl implements GoodDao {
    private static final Logger LOGGER = Logger.getLogger(UserDaoSql.class);

    @Override
    public void addGood(Goods good) {
        Session session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession();
        session.beginTransaction();
        session.save(good);
        session.getTransaction().commit();
        session.close();
        LOGGER.info("Good was added");
    }

    @Override
    public Optional<Goods> getGoodById(int id) {
        LOGGER.info("Good by ID was gotten");
        return Optional.of(HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()
                .get(Goods.class, id));
    }

    @Override
    public List<Goods> getAllGoods() {
        List<Goods> goods = (List<Goods>)  HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()
                .createQuery("From Goods")
                .list();
        return goods;
    }

    @Override
    public void updateValue(Goods good) {
        try (Session session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession();){
            session.beginTransaction();
            session.update(good);
            session.getTransaction().commit();
            session.close();
            LOGGER.info("Values updated");
        } catch (Exception e){
            LOGGER.error("Cant't update values", e);
        }
    }
}