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
import java.util.List;

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

        //persist

        //get persisted like object from db
        Like likeFromDb = this.likeDao.getLike(likeId);


        //TODO persist like object into likes array inside the Post model
        /*//get persisted post object from db
        Post post = this.postDao.getOnePost(postId);
        //add persisted post to like
        likeFromDb.setPostFk(post);
        //get persisted user object form db
        User user = this.userDao.getUserGivenId(userId);
        //add persisted post and user object id to like
        likeFromDb.setAuthorFk(user);*/

       return likeDao.getLike(likeId);
    }

    public Like getLike (Integer likeId) {
        return likeDao.getLike(likeId);
    }

    public void removeLike(Integer likeId) {
        //get like by Id
        Like like = this.likeDao.getLike(likeId);

        //remove the like
        this.likeDao.removeLike(like);
    }


    public Like getLikeByPostIdAndAuthorId(Integer authorId, Integer postId){
        return likeDao.getLikeByPostIdAndAuthorId(authorId,postId);
    }
}
