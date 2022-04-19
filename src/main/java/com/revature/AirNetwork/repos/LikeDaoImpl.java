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
    public Integer addLike(Like like) {
        Session session = em.unwrap(Session.class);

        return (Integer) session.save(like);
    }

    @Override
    public Like getLike(Integer likeId) {
        Session session = em.unwrap(Session.class);
        return session.get(Like.class, likeId);
    }

    @Override
    public void removeLike(Like like) {
        Session session = em.unwrap(Session.class);
        session.delete(like);
    }

    @Override
    public Like getLikeByPostIdAndAuthorId(Integer authorId, Integer postId) {
        Session session = em.unwrap(Session.class);
        return session.createQuery("select l from Like l where l.authorFk.id = " + authorId + "and l.postFk.id = " + postId, Like.class).getSingleResult();
    }
}
