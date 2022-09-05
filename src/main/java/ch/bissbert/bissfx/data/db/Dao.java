package ch.bissbert.bissfx.data.db;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.spi.CalendarDataProvider;

/**
 * A class to access the database.
 *
 * @param <T> the type of the objects to access
 * @author Bissbert
 */
public class Dao<T> implements AutoCloseable {
    private final Session session;
    private final SessionFactory sessionFactory;
    private final Class<T> tClass;

    public Dao(Class<T> tClass) {
        this.tClass = tClass;
        this.sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();
        this.session = sessionFactory.openSession();
    }

    public T get(int id) {
        return session.get(tClass, id);
    }

    public void save(T t) {
        session.beginTransaction();
        session.persist(t);
        session.getTransaction().commit();
    }

    public void update(T t) {
        session.beginTransaction();
        session.persist(t);
        session.getTransaction().commit();
    }

    public void delete(T t) {
        session.beginTransaction();
        session.remove(t);
        session.getTransaction().commit();
    }

    public T findBy(String field, String value) {
        return session.createQuery("from " + tClass.getName() + " where " + field + " = '" + value + "'", tClass).uniqueResult();
    }

    public List<T> findAllBy(String field, String value) {
        return session.createQuery("from " + tClass.getName() + " where " + field + " = '" + value + "'", tClass).list();
    }

    public List<T> findAll() {
        return session.createQuery("from " + tClass.getName(), tClass).list();
    }

    public void close() {
        session.close();
        sessionFactory.close();
    }
}

