package com.nikita.mvc.dao;

import com.nikita.mvc.dao.UserDao;
import com.nikita.mvc.model.User;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    private SessionFactory sessionFactory;
    private static final int limitResultsPerPage = 7;


    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(user);
    }

    public void updateUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(user);
    }

    public void removeUser(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = (User)session.load(User.class, id);
        if(user != null) {
            session.delete(user);
        }

    }

    public User getUser(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = (User)session.get(User.class,id);
        return user;
    }
    @SuppressWarnings("unchecked")
    public List<User> getUsers(Long page) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM User");
        query.setFirstResult((int)(page - 1) * limitResultsPerPage);
        query.setMaxResults(limitResultsPerPage);
        List<User> users = query.list();
        return users;
    }
    @SuppressWarnings("unchecked")
    public List<User> getUsers(String name) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM User WHERE name = :name");
        query.setParameter("name", name);
        List<User> users = query.list();
        return users;
    }
}
