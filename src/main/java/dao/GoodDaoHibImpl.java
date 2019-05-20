package dao;

import model.Goods;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;
import java.util.Optional;

public class GoodDaoHibImpl implements GoodDao {

    @Override
    public void addGood(Goods good) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(good);
        tx1.commit();
        session.close();
    }

    @Override
    public Optional<Goods> getGoodById(int id) {
        return Optional.of(HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Goods.class, id));
    }

    @Override
    public List<Goods> getAllGoods() {
        List<Goods> goods = (List<Goods>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Goods").list();
        return goods;
    }

    @Override
    public void updateValue(Goods good) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(good);
        tx1.commit();
        session.close();
    }
}
