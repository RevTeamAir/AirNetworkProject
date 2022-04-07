package com.revature.AirNetwork.services;

import com.revature.AirNetwork.models.Like;
import com.revature.AirNetwork.models.Post;
import com.revature.AirNetwork.models.User;
import com.revature.AirNetwork.repos.LikeDao;
import com.revature.AirNetwork.repos.PostDao;
import com.revature.AirNetwork.repos.UserDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class LikeServiceTest {

    private LikeService likeService;

    private LikeDao likeDao = Mockito.mock(LikeDao.class);
    private PostDao postDao = Mockito.mock(PostDao.class);
    private UserDao userDao = Mockito.mock(UserDao.class);


    public LikeServiceTest() {
        this.likeService = new LikeService(this.likeDao, this.postDao, this.userDao);
    }

    @Test
    void addLike() {
        Integer likeId = 1;
        Post post = new Post("testing creating post");
        User user = new User("username", "password", "user", "one", "user@gmail.com");

        //make like object
        Like likeToAdd = new Like(likeId, user, post);
        likeService.addLike(likeToAdd);

        Mockito.verify(this.likeDao, Mockito.times(1 )).addLike(likeToAdd);
    }

    @Test
    void getLike() {
        Integer likeId = 1;
        Post post = new Post("testing creating post");
        User user = new User("username", "password", "user", "one", "user@gmail.com");
        Like expectedOutput = new Like(likeId, user, post);
        Mockito.when(this.likeDao.getLike(likeId)).thenReturn(expectedOutput);
        Like actualOutput = this.likeService.getLike(likeId);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void removeLike() {
        Integer likeId = 1;
        this.likeService.removeLike(likeId);
        Mockito.verify(this.likeDao).removeLike(this.likeDao.getLike(likeId));
    }
}