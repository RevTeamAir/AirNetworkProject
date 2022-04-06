package com.revature.AirNetwork.controllers;

import com.revature.AirNetwork.models.JsonResponse;
import com.revature.AirNetwork.models.Like;
import com.revature.AirNetwork.models.Post;
import com.revature.AirNetwork.models.User;
import com.revature.AirNetwork.services.LikeService;
import com.revature.AirNetwork.services.PostService;
import com.revature.AirNetwork.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("like")
public class LikeController {

    private LikeService likeService;
    private PostService postService;
    private UserService userService;

    @Autowired
    public LikeController(LikeService likeService, PostService postService, UserService userService) {
        this.likeService = likeService;
        this.postService = postService;
        this.userService = userService;
    }

    @PostMapping("{userId}/{postId}")
    public JsonResponse addLike(@RequestBody Like like, @PathVariable Integer userId, @PathVariable Integer postId){
        User author = userService.getUserGivenId(userId);
        like.setAuthorFk(author);

        Post post = postService.getOnePost(postId);
        like.setPostFk(post);

        this.likeService.addLike(like);

        JsonResponse jsonResponse  = new JsonResponse(true,"post successfully liked",  like);

        return jsonResponse;
    }
}
