package entities;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.lang.module.Configuration;
import java.util.Date;

public class QueryLevelCacheDemo {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        createEntityInDb(sessionFactory);

        Query query = session.createQuery("FROM corporate_user");
        query.setCacheable(true);
        query.setCacheRegion("user");
        System.out.println("List:-->"+query.list());

        transaction.commit();
    }

    private static void createEntityInDb(SessionFactory sessionFactory){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = new User("Senthil",new Date(),45, Gender.MALE);
        session.save(user);
        transaction.commit();
        session.close();
    }
}
