package Dao;

import Model.Dress;
import Util.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DressDao {
    public Dress findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Dress.class, id);
    }

    public void save(Dress dress) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        session.save(dress);
        tr.commit();
        session.close();
    }

    public void update(Dress dress) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        session.update(dress);
        tr.commit();
        session.close();
    }

    public void delete(Dress dress) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        session.delete(dress);
        tr.commit();
        session.close();
    }

    public List<Dress> findAll() {
        List<Dress> dresses = (List<Dress>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Dress ").list();
        return dresses;
    }
}
