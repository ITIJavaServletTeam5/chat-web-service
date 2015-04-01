/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

/**
 * @author root
 */
public class DaoUser {
    private static DaoUser instance = new DaoUser();
    SessionFactory sessionFactory;

    private DaoUser() {
        this.sessionFactory = SessionUtil.getSessionFactory();
    }

    public static DaoUser getInstance() {
        return instance;
    }

    //dao methods protected by singleton
    public boolean presist(User u) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        //find by example
        User userSearched = findByMobile(session, u.getMobile());

        if (userSearched == null) {
            session.persist(u);
            session.getTransaction().commit();
            session.flush();
            session.close();
            return true;
        } else {
            session.getTransaction().commit();
            session.flush();
            session.close();
            return false;
        }
    }

    public boolean update(User u) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        User userSearched = findByMobile(session, u.getMobile());

        if (userSearched == null) {
            session.getTransaction().commit();
            session.flush();
            session.close();
            return false;
        } else {
            session.saveOrUpdate(u);
            session.getTransaction().commit();
            session.flush();
            session.close();
            return true;
        }
    }

    public boolean checkUser(User u) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        //find by example
        User userSearched = findByMobile(session, u.getMobile());

        if (userSearched == null) {
            session.persist(u);
            session.getTransaction().commit();
            session.flush();
            session.close();
            return false;
        } else {
            session.getTransaction().commit();
            session.flush();
            session.close();
            return false;
        }
    }

    public User getByMobile (String mobile) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User userSearched = findByMobile(session, mobile);
        session.getTransaction().commit();
        session.flush();
        session.close();
        return userSearched;
    }

    private User findByMobile(Session s, String mobile) {
//        Criteria c = s.createCriteria(User.class);
//        User searchUser = new User();
//        searchUser.setMobile(mobile);
//        Example example = Example.create(searchUser);
//        c.add(example);
//        return c.list();
        Query query = s.createQuery("from User where mobile = ?");
        query.setString(0, mobile);
        return (User) query.uniqueResult();
    }
}
