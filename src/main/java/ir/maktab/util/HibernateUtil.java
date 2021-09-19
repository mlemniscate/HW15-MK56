package ir.maktab.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {
    private static final EntityManagerFactory mainEntityManagerFactory;
    private static final EntityManagerFactory testEntityManagerFactory;

    static {
        mainEntityManagerFactory = Persistence.createEntityManagerFactory("Unit");
        testEntityManagerFactory = Persistence.createEntityManagerFactory("Test");
    }

    public static EntityManagerFactory getMainEntityManagerFactory() {
        return mainEntityManagerFactory;
    }

    public static EntityManagerFactory getTestEntityManagerFactory() {
        return testEntityManagerFactory;
    }
}
