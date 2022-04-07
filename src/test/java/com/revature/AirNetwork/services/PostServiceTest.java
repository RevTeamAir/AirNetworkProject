package com.revature.AirNetwork.services;

import com.revature.AirNetwork.models.Post;
import com.revature.AirNetwork.repos.PostDao;
import com.revature.AirNetwork.repos.UserDao;
import org.hibernate.Session;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PostServiceTest {

    private PostService postService;
    private PostDao postDao = Mockito.mock(PostDao.class);

    public PostServiceTest(){
        this.postService = new PostService(this.postDao);
    }

    @Test
    void createPost() {
        //arrange
        Post postToPass = new Post("testing creating post");
        /*Integer postId = 1;
        Mockito.when(this.postDao.createPost(postToPass)).thenReturn(postId);
        Mockito.when(this.postDao.getOnePost(postId));*/
        postService.createPost(postToPass);
        Mockito.verify(this.postDao, Mockito.times(1 )).createPost(postToPass);
    }

    @Test
    void getOnePost() {
        Integer postId = 1;
        Post expectedOutput = new Post("testing creating post");
        Mockito.when(this.postDao.getOnePost(postId)).thenReturn(expectedOutput);
        Post actualOutput = this.postService.getOnePost(postId);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void getAllPosts() {
        this.postService.getAllPosts();
    }

    @Test
    void getAllPostsGivenUserId() {
        Integer userId = 1;
        List<Post> expectedOutput = new ArrayList<>();
        expectedOutput.add(new Post("post one"));
        expectedOutput.add(new Post(("post second")));
        Mockito.when(this.postDao.getAllPostsGivenUserId(userId)).thenReturn(expectedOutput);
        List<Post> actualOutput = this.postService.getAllPostsGivenUserId(userId);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void removeOnePost() {
        Integer postId = 1;
        this.postService.removeOnePost(postId);
        Mockito.verify(this.postDao).removePost(this.postDao.getOnePost(postId));
    }
}

