package Dao;

import Model.Client;
import org.hibernate.Session;
import org.hibernate.Transaction;
import Util.HibernateSessionFactoryUtil;

import java.util.List;

public class ClientDao {
    public Client findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Client.class, id);
    }

    public void save(Client client) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        session.save(client);
        tr.commit();
        session.close();
    }

    public void update(Client client) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        session.update(client);
        tr.commit();
        session.close();
    }

    public void delete(Client client) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        session.delete(client);
        tr.commit();
        session.close();
    }

    public List<Client> findAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Client> clients = (List<Client>) session.createQuery("FROM Client ").list();
        session.close();
        return clients;
    }

}
