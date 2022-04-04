package com.revature.AirNetwork.repos;

import com.revature.AirNetwork.models.Post;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public class PostDaoImpl implements PostDao{
    @Override
    public Integer createPost(Post post) {
        return null;
    }

    @Override

    public List<Post> getAllPost() {

        return null;
    }

    @Override
    public List<Post> getAllPostsGivenUserId(Integer userId) {
        return null;
    }

    @Override
    public Post getOnePost(Integer postId) {
        return null;
    }

    @Override
    public void removePost(Post post) {

    }
}
