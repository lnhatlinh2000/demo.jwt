package com.example.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.example.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class GenericDAO<T> {

    private Class<T> entityClass;

    GenericDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * Insert a entity to database
     * @param entity that will be insert into database
     * @return id of entity that success insert to database
     */
    public Serializable create(T entity) {
        Session session = null;
        Transaction transaction = null;
        Serializable id = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            id = session.save(entity);
            
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return id;
    }

    /**
     * Get a row from database
     * @param id of row that will be get
     * @return entity
     */
    public T readOne(Serializable id) {
        Session session = null;
        Transaction transaction = null;
        T readEntity = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            readEntity = session.get(entityClass, id);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return readEntity;
    }

    /**
     * Read all row of table
     * @return list of entity
     */
    public List<T> readAll() {
        Session session = null;
        Transaction transaction = null;
        List<T> entityList = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

            CriteriaQuery<T> query = criteriaBuilder.createQuery(entityClass);

            Root<T> root = query.from(entityClass);

            query.select(root);

            Query<T> q = session.createQuery(query);

            entityList = q.getResultList();

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return entityList;
    }

    /**
     * Update a entity to database
     * @param entity that will be updated
     */
    public void update (T entity) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.saveOrUpdate(entity);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /**
     * Delete a row at database
     * @param id of row that will be deleted
     */
    public void delete (Serializable id) {
        Session session = null;
        Transaction transaction = null;
        T deletedEntity = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            deletedEntity = session.get(entityClass, id);
            session.delete(deletedEntity);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

}
