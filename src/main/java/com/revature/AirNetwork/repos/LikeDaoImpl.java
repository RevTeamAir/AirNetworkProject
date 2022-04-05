package com.revature.AirNetwork.repos;

import com.revature.AirNetwork.models.Like;
import com.revature.AirNetwork.models.Post;
import com.revature.AirNetwork.models.User;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class LikeDaoImpl implements LikeDao {

    @PersistenceContext
    EntityManager em;

    @Override
    public User addLike(User userId, Post postId) {
        Session session = em.unwrap(Session.class);

        return null;
    }

    @Override
    public Like getAllLikes() {
        return null;
    }

    @Override
    public void removeLike() {

    }
}
