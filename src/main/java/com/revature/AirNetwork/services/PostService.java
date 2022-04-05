package com.revature.AirNetwork.services;

import com.revature.AirNetwork.models.Post;
import com.revature.AirNetwork.repos.PostDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PostService {
    @Autowired
    private PostDao postDao;

    public Post createPost(Post post) {
        Integer postId = this.postDao.createPost(post);
        return this.postDao.getOnePost(postId);
    }

    public Post getOnePost (Integer postId){
        return this.postDao.getOnePost(postId);
    }

    public List<Post> getAllPosts() {
        return this.postDao.getAllPosts();
    }

    //TODO add remove one post
    public void removeOnePost(Integer postId) {

    }

}
