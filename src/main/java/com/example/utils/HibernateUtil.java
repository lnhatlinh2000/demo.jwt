package com.example.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Built the SessionFactory to create Session  
 * Method refreshNewSessionFactory use to refresh thread and rebuilt SessionFactory
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory;

    static {
        refreshNewSessionFactory();
    }

    public static void refreshNewSessionFactory() {
        Configuration cfg = new Configuration();
        cfg.configure();
        sessionFactory = cfg.buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void setSessionFactory(SessionFactory sessionFactory) {
        HibernateUtil.sessionFactory = sessionFactory;
    }
}
