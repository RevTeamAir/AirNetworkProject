package com.revature.AirNetwork.repos;

import com.revature.AirNetwork.models.Like;
import com.revature.AirNetwork.models.Post;
import com.revature.AirNetwork.models.User;


public interface LikeDao {
    Integer addLike(Like like);
    Like getLike(Integer likeId);
    void removeLike(Like like);
    Like getLikeByPostIdAndAuthorId(Integer authorId, Integer postId);

}
