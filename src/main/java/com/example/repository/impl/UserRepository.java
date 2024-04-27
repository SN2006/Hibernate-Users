package com.example.repository.impl;

import com.example.entity.User;
import com.example.repository.AppRepository;
import com.example.utils.Constants;
import com.example.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class UserRepository implements AppRepository<User> {
    @Override
    public String create(User obj) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String hql = "INSERT INTO User (name, email) VALUES (:name, :email)";

            MutationQuery query = session.createMutationQuery(hql);
            query.setParameter("name", obj.getName());
            query.setParameter("email", obj.getEmail());
            query.executeUpdate();

            transaction.commit();

            return Constants.DATA_INSERT_MSG;
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            return e.getMessage();
        }
    }

    @Override
    public Optional<List<User>> read() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            String hql = "FROM User";
            List<User> users = session.createQuery(hql, User.class).list();

            transaction.commit();

            return Optional.of(users);
        }catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public String update(User obj) {
        if (readById(obj.getId()).isEmpty()) return Constants.DATA_ABSENT_MSG;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();

            String hql = "UPDATE User SET name = :name, email = :email WHERE id = :id";
            MutationQuery query = session.createMutationQuery(hql);

            query.setParameter("name", obj.getName());
            query.setParameter("email", obj.getEmail());
            query.setParameter("id", obj.getId());

            query.executeUpdate();

            transaction.commit();

            return Constants.DATA_UPDATE_MSG;
        }catch (Exception e){
            if (transaction != null) {
                transaction.rollback();
            }
            return e.getMessage();
        }
    }

    @Override
    public String delete(Long id) {
        if (readById(id).isEmpty()) return Constants.DATA_ABSENT_MSG;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();

            String hql = "DELETE FROM User WHERE id = :id";

            MutationQuery query = session.createMutationQuery(hql);
            query.setParameter("id", id);

            query.executeUpdate();

            transaction.commit();

            return Constants.DATA_DELETE_MSG;
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            return e.getMessage();
        }
    }

    @Override
    public String deleteAll() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();

            String hql = "DELETE FROM User";

            MutationQuery query = session.createMutationQuery(hql);

            query.executeUpdate();

            transaction.commit();
            return Constants.DATA_DELETE_MSG;
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            return e.getMessage();
        }
    }

    @Override
    public Optional<User> readById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            String hql = "FROM User WHERE id = :id";

            Query<User> query = session.createQuery(hql, User.class);
            query.setParameter("id", id);
            Optional<User> userOptional = query.uniqueResultOptional();

            transaction.commit();
            return userOptional;
        }catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<User>> readByName(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();

            String hql = "FROM User WHERE name = :name";

            Query<User> query = session.createQuery(hql, User.class);
            query.setParameter("name", name);
            List<User> users = query.list();

            transaction.commit();

            return Optional.of(users);
        }catch (Exception e) {
            return Optional.empty();
        }
    }
}
