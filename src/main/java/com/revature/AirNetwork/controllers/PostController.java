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

    @GetMapping("/user/{userId}")
    public ResponseEntity<JsonResponse> getAllPostsForUser(@PathVariable Integer userId){
        List<Post> allPosts = this.postService.getAllPostsGivenUserId(userId);
        JsonResponse jsonResponse = new JsonResponse(true, "All posts for user with id: " + userId + " retrieved:", allPosts);
        return ResponseEntity.ok(jsonResponse);
    }

    @GetMapping("{postId}")
    public ResponseEntity<JsonResponse> getOnePost(@PathVariable Integer postId){
        Post retrievedPost = postService.getOnePost(postId);
        JsonResponse jsonResponse= new JsonResponse(true, "Retrieved post with id " + postId + ":", retrievedPost);
        return ResponseEntity.ok(jsonResponse);
    }

    @DeleteMapping("{postId}")
    public ResponseEntity<JsonResponse> deleteOnePost(@PathVariable Integer postId){
        postService.removeOnePost(postId); // deleting post 
        JsonResponse jsonResponse = new JsonResponse(true, "Post with id " + postId + " was deleted", null);
        return ResponseEntity.ok(jsonResponse);
    }


    //todo add this functionality after we figure out s3
    public ResponseEntity<JsonResponse> addPictureToPost(){

        return null;
    }
}
