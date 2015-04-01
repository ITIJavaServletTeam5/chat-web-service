/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

/**
 *
 * @author root
 */
public class DaoUser {
    private static DaoUser instance = new DaoUser();
    SessionFactory sessionFactory;
    
    private DaoUser(){
        this.sessionFactory = SessionUtil.getSessionFactory();
    }

    public static DaoUser getInstance() {
        return instance;
    }
    
    //dao methods protected by singleton
    public boolean presist(User u){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        
        //find by example
        List list= findByMobile(session, u.getMobile());
        
        if(list.isEmpty()){
            session.persist(u);
        session.getTransaction().commit();
        session.flush();
        session.close();
        return true;
        }
        session.getTransaction().commit();
        session.flush();
        session.close();
        return false;
    }
    
    public boolean checkUser(User u ){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        
        //find by example
        List list= findByMobile(session, u.getMobile());
        
        if(list.isEmpty()){
            session.persist(u);
        session.getTransaction().commit();
        session.flush();
        session.close();
        return false;
        }
        session.getTransaction().commit();
        session.flush();
        session.close();
        return false;
    }
    
    private List findByMobile (Session s,String mobile){
        Criteria c = s.createCriteria(User.class);
        User searchUser = new User();
        searchUser.setMobile(mobile);
        Example example = Example.create(searchUser);
        c.add(example);
        
        return c.list();
    }
}
