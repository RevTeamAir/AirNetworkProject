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

    // todo test toggleLike() endpoint in controller that adds or removes the like based on the return of this method
    // todo find out why its not removing the like


    public Like toggleLike(Integer userId, Integer postId){

        // first check if like exist for that post by that user
        Boolean postIsAlreadyLikedByUser = false;

        // to be used if post is liked already
        Integer likeToRemoveId = null;

        User userTryingToLikePost = userDao.getUserGivenId(userId);
        Post postToLike = postDao.getOnePost(postId);

        List<Like> likesOfThisPost = postToLike.getLikes();

        /*for (Integer i = 0; i < likesOfThisPost.size(); i++){

            Like like = likesOfThisPost.get(i);

            // get the author for each like
            User authorOfLike = like.getAuthorFk();

            if (authorOfLike.equals(userTryingToLikePost)) {
                postIsAlreadyLikedByUser = true;
                likeToRemoveId = like.getId();
                indexOfLikeToRemove = i;
                break;
            }
        }*/

        //using enhanced for loop

        for (Like like : likesOfThisPost){
            // get the author for each like
            User authorOfLike = like.getAuthorFk();

            if (authorOfLike.equals(userTryingToLikePost)) {
                postIsAlreadyLikedByUser = true;
                likeToRemoveId = like.getId();
                break;
            }
        }

        if (postIsAlreadyLikedByUser){
            // remove the like from the post


            /*

            // deleting the like from the likesOfThisPost array in the postToLike
            likesOfThisPost.remove(indexOfLikeToRemove); // delete this if we use the enhanced for loop

            //update the likesOfThisPost object in the postToLike
            postToLike.setLikes(likesOfThisPost); // delete this if we use the enhanced for loop

            // persisting the updated post in the db
            this.postDao.updatePost(postToLike); // delete this if we use the enhanced for loop

            */

            //remove the like from the db and return null
            System.out.println(likeToRemoveId); //todo remove this print statement
            //this.removeLike(likeToRemoveId);

            //get like by Id
            //Like likeToRemove = this.likeDao.getLike(likeToRemoveId);

            //remove the like
            this.removeLike(likeToRemoveId);

            postIsAlreadyLikedByUser = false;

            return null;

        } else {
            // current user has not liked the post yet -> add the like

            // creating the like
            Like like = new Like();

            // setting the author and post foreign keys
            like.setAuthorFk(userTryingToLikePost);
            like.setPostFk(postToLike);

            // persisting the like
            Integer createdLikeId = this.likeDao.addLike(like);
            Like createdLike = this.likeDao.getLike(createdLikeId);

            // adding the persisted like to the likes array in the postToLike
            likesOfThisPost.add(createdLike);
            postToLike.setLikes(likesOfThisPost);

            // use the update method on the post to save the updated post in the db
            this.postDao.updatePost(postToLike);

            //return the like
            return createdLike;
        }
    }
}
