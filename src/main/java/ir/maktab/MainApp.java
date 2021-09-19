package ir.maktab;

import ir.maktab.util.HibernateUtil;

public class MainApp {
    public static void main(String[] args) {
        HibernateUtil.getMainEntityManagerFactory().createEntityManager();
    }
}
