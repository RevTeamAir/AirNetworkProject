package com.revature.AirNetwork.repos;

import com.revature.AirNetwork.models.Post;

import com.revature.AirNetwork.models.User;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PostDaoImpl implements PostDao{


    @PersistenceContext
    EntityManager em;

    @Override
    public Integer createPost(Post post) {
        Session session = em.unwrap(Session.class);
        return (Integer) session.save(post);
    }

    @Override
    public List<Post> getAllPosts() {
        Session session = em.unwrap(Session.class);
        // todo Figure out how to sort this by most recent
        return session.createQuery("FROM Post p ORDER BY p.creationDate DESC", Post.class).getResultList();
    }

    @Override
    public List<Post> getAllPostsGivenUserId(Integer userId) {
        Session session = em.unwrap(Session.class);
        return session.createQuery("FROM Post p WHERE p.authorIdFK.id = " + userId + "ORDER BY p.creationDate DESC" , Post.class).getResultList();

    }

    @Override
    public Post getOnePost(Integer postId) {
        Session session = em.unwrap(Session.class);
        return null;
    }

    //Optional
    @Override
    public void removePost(Post post) {
        Session session = em.unwrap(Session.class);

    }
}
