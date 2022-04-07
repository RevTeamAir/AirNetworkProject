package com.revature.AirNetwork.repos;

import com.revature.AirNetwork.models.User;
import org.hibernate.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<User> getAllUsers() {
        Session session = em.unwrap(Session.class);
        return session.createQuery("from User", User.class).getResultList();
    }

    @Override
    public Integer createUser(User user) {
        Session  session = em.unwrap(Session.class);
        return (Integer) session.save(user);
    }

    @Override
    public void updateUser(User user) {
        Session session = em.unwrap(Session.class);
        session.update(user);
    }

    @Override
    public User getUserGivenId(Integer userId) {
        Session  session = em.unwrap(Session.class);
        return session.get(User.class, userId);
    }

    @Override
    public User getUserGivenUsername(String username) {
        // need to wrap this into a try catch and catch javax.persistence.NoResultException and have it return null
        // this makes it so no error is thrown when a username was not found in the database. we can then successfully create the user
        try {
            Session session = em.unwrap(Session.class);
            return session.createQuery("from User where username = '" + username + "'", User.class).getSingleResult();

        } catch (javax.persistence.NoResultException noResultException) {
            return null;
        }
    }

    @Override
    public User getUserGivenEmail(String email) {
        // need to wrap this into a try catch and catch javax.persistence.NoResultException and have it return null
        // this makes it so no error is thrown when an email was not found in the database. we can then successfully create the user
        try {
            Session session = em.unwrap(Session.class);
            return session.createQuery("from User where email = '" + email + "'", User.class).getSingleResult();

        } catch(javax.persistence.NoResultException noResultException){
            return null;
        }
    }
}
