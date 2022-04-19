package com.revature.AirNetwork.controllers;

import com.revature.AirNetwork.models.JsonResponse;
import com.revature.AirNetwork.models.Like;
import com.revature.AirNetwork.models.Post;
import com.revature.AirNetwork.models.User;
import com.revature.AirNetwork.services.LikeService;
import com.revature.AirNetwork.services.PostService;
import com.revature.AirNetwork.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("like")
@CrossOrigin(origins = {"http://localhost:4200"}, allowCredentials = "true")
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

    @PostMapping("author/{userId}/post/{postId}")
    public ResponseEntity<JsonResponse> toggleLike(@PathVariable Integer userId, @PathVariable Integer postId){

        try {
            Like like = new Like();
            User author = userService.getUserGivenId(userId);
            like.setAuthorFk(author);

            Post post = postService.getOnePost(postId);
            like.setPostFk(post);

            this.likeService.addLike(like);

            JsonResponse jsonResponse = new JsonResponse(true, "Post successfully liked", like);

            return ResponseEntity.ok(jsonResponse);

        }catch (Exception e){

            Like likeToRemove = likeService.getLikeByPostIdAndAuthorId(userId,postId);
            Integer likeId = likeToRemove.getId();
            likeService.removeLike(likeId);

            JsonResponse jsonResponse = new JsonResponse(false, "Like successfully deleted", null);
            return ResponseEntity.ok(jsonResponse);
        }
    }

    @GetMapping("{likeId}")
    public ResponseEntity<JsonResponse> getLike(@PathVariable Integer likeId){
        Like grabbedLike = likeService.getLike(likeId);
        JsonResponse jsonResponse = new JsonResponse(true, "like successfully grabbed", grabbedLike);
        return ResponseEntity.ok(jsonResponse);
    }

    @DeleteMapping("{likeId}")
    public ResponseEntity<JsonResponse> removeLike(@PathVariable Integer likeId) {
        likeService.removeLike(likeId);
        Like like = likeService.getLike(likeId);
        JsonResponse jsonResponse = new JsonResponse(true, "like successfully removed", like);
        return ResponseEntity.ok(jsonResponse);
    }


    @GetMapping("{userId}/{postId}")
    public ResponseEntity<JsonResponse> getLikeByPostIdAndAuthorId(@PathVariable Integer userId, @PathVariable Integer postId){
        Like retrievedLike = likeService.getLikeByPostIdAndAuthorId(userId,postId);
        JsonResponse jsonResponse = new JsonResponse(true, "like: ", retrievedLike);
        return ResponseEntity.ok(jsonResponse);
    }
}
