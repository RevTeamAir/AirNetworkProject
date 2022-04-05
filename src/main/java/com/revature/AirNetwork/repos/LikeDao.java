package com.revature.AirNetwork.repos;

import com.revature.AirNetwork.models.Like;
import com.revature.AirNetwork.models.Post;
import com.revature.AirNetwork.models.User;

public interface LikeDao {
    User addLike(User userId, Post postId);
    void removeLike();

    //void addLike();
}
