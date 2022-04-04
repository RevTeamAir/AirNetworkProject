package com.revature.AirNetwork.repos;

import com.revature.AirNetwork.models.Post;

import java.util.List;

public interface PostDao {

    Integer createPost(Post post);  // used to create new posts
    List<Post> getAllPost();  // used for the main feed
    List<Post> getAllPostsGivenUserId(Integer userId);  // used to build user profile in front-end
    Post getOnePost(Integer postId); // used to add a picture to a post
     //optional
    void removePost(Post post);

}
