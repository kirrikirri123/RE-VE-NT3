package com.ahlenius.revent3fx.util;

import com.ahlenius.revent3fx.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.InputStream;
import java.util.Properties;

public class HibernateUtil {

    private static final String PROPERTIES_FILE = "hibernate.properties";

    private static final SessionFactory SESSION_FACTORY = buildSessionFactory();

    private HibernateUtil() { } // endast här inne

    public static SessionFactory getSessionFactory() {return SESSION_FACTORY;    }

    private static SessionFactory buildSessionFactory() {
        try {
            Properties properties = new Properties(); // Vad händer här eg?

            try (InputStream in =
                         HibernateUtil.class
                                 .getClassLoader()
                                 .getResourceAsStream(PROPERTIES_FILE)) {
                if (in == null) {
                    throw new IllegalStateException(
                            PROPERTIES_FILE + "not found in resources");

                }
                properties.load(in);
            }
            Configuration configuration = new Configuration();
            configuration.setProperties(properties);

            configuration.addAnnotatedClass(BouncyCastle.class);
            configuration.addAnnotatedClass(DiscoMachine.class);
            configuration.addAnnotatedClass(Costume.class);
            configuration.addAnnotatedClass(Member.class);
            configuration.addAnnotatedClass(Rental.class);

            ServiceRegistry serviceRegistry =
                    new StandardServiceRegistryBuilder()
                            .applySettings(configuration.getProperties())
                            .build();

            return configuration.buildSessionFactory(serviceRegistry);

        } catch (Exception e) { throw new ExceptionInInitializerError(
                    "Failed to build SessionFactory: " + e.getMessage()
            );

        }
    }
}