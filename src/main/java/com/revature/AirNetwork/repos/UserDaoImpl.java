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

    /**
     * <h2>Used to display all profiles</h2>
     * Retrieves information for all users from the database
     * @return a list of all users
     */
    @Override
    public List<User> getAllUsers() {
        Session session = em.unwrap(Session.class);
        return session.createQuery("from User", User.class).getResultList();
    }

    /**
     * <h2>Used to register new user</h2>
     * receives a user object from the front-end and creates a persisted object in the database based on the information passed to it
     * @param user
     * @return the user id of the created user
     */
    @Override
    public Integer createUser(User user) {
        Session  session = em.unwrap(Session.class);
        return (Integer) session.save(user);
    }

    /**<h2>Used to update user information </h2>
     * receives a user object from the front-end and updates it in the database
     * @param user
     */
    @Override
    public void updateUser(User user) {
        Session session = em.unwrap(Session.class);
        session.update(user);
    }

    /**
     * <h2>Used to get info for a specific user given a user id</h2>
     * @param userId
     * @return the User from the database
     */
    @Override
    public User getUserGivenId(Integer userId) {
        Session  session = em.unwrap(Session.class);
        return session.get(User.class, userId);
    }

    /**
     * <h2>Used to check unique username when registering new user</h2>
     * @param username
     * @return
     */
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

    /**
     * <h2>Used to check unique email when registering new user</h2>
     * @param email
     * @return
     */
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
