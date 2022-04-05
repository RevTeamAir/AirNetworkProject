package com.revature.AirNetwork.repos;

import com.revature.AirNetwork.models.Post;

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
        return session.createQuery("from Post", Post.class).getResultList();
    }

    @Override
    public List<Post> getAllPostsGivenUserId(Integer userId) {
        Session session = em.unwrap(Session.class);
        return null;
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
