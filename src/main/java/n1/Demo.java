package n1;

import entities.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.enterprise.inject.Typed;
import javax.persistence.Entity;
import javax.persistence.EntityGraph;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        //createProducts(session);
        if(!session.isOpen())
            session = sessionFactory.openSession();
        session.getTransaction().begin();

        EntityGraph entityGraph = session.getEntityGraph("cat-entity-graph");

        TypedQuery<Category> query = session.createQuery("FROM category_problem");
        query.setHint("javax.persistence.fetchgraph",entityGraph);
        List<Category> categoryList = query.getResultList();
        for (Category category : categoryList){
            System.out.println("Cat Id:-->"+category.getId());
            System.out.println("Product Size:-->"+category.getProducts().size());
        }

    }

    private static void createProducts(Session session){
        session.getTransaction().begin();

        Category category = new Category();
        category.setCategoryName("Mobile");
        category.setAvailable(true);
        category.setProducts(Arrays.asList(new Product("Azuz",category),
                new Product("OnePlus",category)));

        Category category1 = new Category();
        category1.setCategoryName("Laptop");
        category1.setAvailable(true);
        category1.setProducts(Arrays.asList(new Product("Apple",category1)));

        Category category2 = new Category();
        category2.setCategoryName("Fridge");
        category2.setAvailable(false);
        category2.setProducts(Arrays.asList(new Product("Samsung",category2)));

        session.persist(category);
        session.persist(category1);
        session.persist(category2);

        session.getTransaction().commit();
    }
}
