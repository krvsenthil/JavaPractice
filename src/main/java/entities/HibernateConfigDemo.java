package entities;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.stat.Statistics;

import java.util.Date;

public class HibernateConfigDemo {
    public static void main(String[] args) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Statistics statistics = sessionFactory.getStatistics();
        statistics.setStatisticsEnabled(true);
        createEntityInDb(sessionFactory);

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        System.out.println("Example 1");
        User user = (User) session.load(User.class, 1);
        System.out.println("Id 1"+user.getId());
        printStatistics(statistics);

        System.out.println("Example 2");
        User user1 = (User) session.load(User.class, 1);
        System.out.println("Id 2"+user1.getId());
        printStatistics(statistics);

        //clear First Level Cache so that Second Level Cache is used.
        System.out.println("Example 3");
        session.evict(user);
        user = (User) session.load(User.class, 1);
        System.out.println("Id 3"+user.getId());
        printStatistics(statistics);

        //close resources
        transaction.commit();
        session.close();
        sessionFactory.close();
    }

    private static void printStatistics(Statistics statistics){
        System.out.println("Hit Count:-->"+statistics.getSecondLevelCacheHitCount());
        System.out.println("Miss Count:-->"+statistics.getSecondLevelCacheMissCount());
        System.out.println("Put Count:-->"+statistics.getSecondLevelCachePutCount());
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
