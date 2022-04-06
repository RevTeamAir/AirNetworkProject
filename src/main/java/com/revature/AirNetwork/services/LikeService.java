package com.revature.AirNetwork.services;

import com.revature.AirNetwork.models.Like;
import com.revature.AirNetwork.models.Post;
import com.revature.AirNetwork.models.User;
import com.revature.AirNetwork.repos.LikeDao;
import com.revature.AirNetwork.repos.PostDao;
import com.revature.AirNetwork.repos.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class LikeService {

    LikeDao likeDao;
    PostDao postDao;
    UserDao userDao;

    @Autowired
    public LikeService(LikeDao likeDao, PostDao postDao, UserDao userDao){
        this.likeDao = likeDao;
        this.postDao = postDao;
        this.userDao = userDao;
    }

    public Like addLike (Like like) {
        //get likeId
        Integer likeId = this.likeDao.addLike(like);

        //get persisted like object from db
        Like likeFromDb = this.likeDao.getLike(likeId);
        //get persisted post object from db
        Post post = this.postDao.getOnePost(likeFromDb.getPostFk().getId());
        //add persisted post to like
        likeFromDb.setPostFk(post);

        //get persisted user object form db
        User user = this.userDao.getUserGivenId(likeFromDb.getAuthorFk().getId());
        //add persisted post and user object id to like
        likeFromDb.setAuthorFk(user);

       return likeDao.getLike(likeId);
    }

    public Like getLike (Integer likeId) {
        return likeDao.getLike(likeId);
    }
}
