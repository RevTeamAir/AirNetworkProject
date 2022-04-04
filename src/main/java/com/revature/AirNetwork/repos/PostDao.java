package com.revature.AirNetwork.repos;

import com.revature.AirNetwork.models.Post;

import java.util.List;

public interface PostDao {
    Integer createPost(Post post);
    List<Post> getAllPost();
    Post getOnePost(Integer postId);
    //optional
    void removePost(Post post);
}
