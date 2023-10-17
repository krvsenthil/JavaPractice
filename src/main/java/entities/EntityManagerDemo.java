package entities;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;

public class EntityManagerDemo {

    private static EntityManagerFactory entityManagerFactory;

    static {
        entityManagerFactory =
                Persistence.createEntityManagerFactory("TestPersistence");
    }

    public static void main(String[] args) {
        //refers persistence.xml file under meta-inf
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        Query query = entityManager.createQuery(criteriaQuery.select(userRoot));
        System.out.println("Result ouput:-->"+query.getResultList());

        User user = new User("Senthil",new Date(), 45, Gender.MALE);
        UserProfile userProfile = new UserProfile();
        //userProfile.setUser(user);
        //user.setUserProfile(userProfile);

        entityManager.persist(user);

        user.getId();

        //Propagation and LockType are used to read data with concurrent programming.
        User user1 = entityManager.find(User.class, user.getId());
        System.out.println("Retrieved User:-->"+user1.getDob());
        //Refresh the state of the instance from the database.
        //Overwriting the changes made to entity, if any. Also, triggers payload event.
        user1.setName("Kumar");
        entityManager.refresh(user1);
        //Locks the row and doesn't allow to acquire the lock by another thread
        //locked when its edited
        //entityManager.lock(user, LockModeType.PESSIMISTIC_FORCE_INCREMENT);
        //Locks the row when try to commit the change
        //locked when changes are committed
        //entityManager.lock(user, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
        System.out.println("Entity is persisted successfully.");
    }

}
