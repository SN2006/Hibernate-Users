package com.example.utils;

import com.example.entity.User;
import com.example.view.AppView;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;


import java.io.IOException;
import java.util.Properties;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory(){
        if(sessionFactory == null){
            try{
                Configuration configuration = getConfiguration();
                configuration.addAnnotatedClass(User.class);
                ServiceRegistry serviceRegistry =
                        new StandardServiceRegistryBuilder()
                                .applySettings(configuration.getProperties()).build();
                sessionFactory =
                        configuration.buildSessionFactory(serviceRegistry);
            }catch (Exception e){
                new AppView().getOutput(e.getMessage());
            }
        }
        return sessionFactory;
    }

    private static Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        Properties properties = new Properties();

        try {
            properties.load(HibernateUtil.class.getResourceAsStream("/db/db.properties"));
        } catch (IOException e) {
            new AppView().getOutput(e.getMessage());
        }
        properties.put(Environment.JAKARTA_JDBC_DRIVER, properties.getProperty("driver"));
        properties.put(Environment.JAKARTA_JDBC_URL, properties.getProperty("url"));
        properties.put(Environment.JAKARTA_JDBC_USER, properties.getProperty("username"));
        properties.put(Environment.JAKARTA_JDBC_PASSWORD, properties.getProperty("password"));
        configuration.setProperties(properties);
        return configuration;
    }

}
