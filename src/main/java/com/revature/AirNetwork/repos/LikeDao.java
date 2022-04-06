package com.revature.AirNetwork.repos;

import com.revature.AirNetwork.models.Like;


public interface LikeDao {
    Integer addLike(Like like);
    Like getLike(Integer likeId);
    void removeLike(Like like);

}
