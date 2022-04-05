package com.revature.AirNetwork.controllers;

import com.revature.AirNetwork.models.JsonResponse;
import com.revature.AirNetwork.models.Post;
import com.revature.AirNetwork.models.User;
import com.revature.AirNetwork.services.PostService;
import com.revature.AirNetwork.services.S3Service;
import com.revature.AirNetwork.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("post")
public class PostController {

    private UserService userService;
    private PostService postService;
    private S3Service s3Service;

    public PostController() {
    }

    @Autowired
    public PostController(UserService userService, PostService postService, S3Service s3Service) {
        this.userService = userService;
        this.postService = postService;
        this.s3Service = s3Service;
    }

    @PostMapping ("{userId}")
    public ResponseEntity<JsonResponse> createPost(@RequestBody Post postToCreate, @PathVariable Integer userId){

        User author = userService.getUserGivenId(userId);
        postToCreate.setAuthorIdFK(author);

        Post createdPost = this.postService.createPost(postToCreate);

        JsonResponse jsonResponse  = new JsonResponse(true,"Post successfully Created",  postToCreate);
        return ResponseEntity.ok(jsonResponse);
    }

    @GetMapping
    public ResponseEntity<JsonResponse> getAllPosts(){
        List<Post> allPosts = this.postService.getAllPosts();
        JsonResponse jsonResponse = new JsonResponse(true, "All posts retrieved", allPosts);
        return ResponseEntity.ok(jsonResponse);
    }

    @GetMapping("{userId}")
    public ResponseEntity<JsonResponse> getAllPostsForUser(@PathVariable Integer userId){
        List<Post> allPosts = this.postService.getAllPostsGivenUserId(userId);
        JsonResponse jsonResponse = new JsonResponse(true, "All posts for user with id: " + userId + "retrieved:", allPosts);
        return ResponseEntity.ok(jsonResponse);
    }
}
