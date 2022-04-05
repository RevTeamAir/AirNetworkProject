package com.revature.AirNetwork.controllers;

import com.revature.AirNetwork.models.JsonResponse;
import com.revature.AirNetwork.models.User;
import com.revature.AirNetwork.services.S3Service;
import com.revature.AirNetwork.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.List;


@RestController
@RequestMapping("user")
public class UserController {

    Logger logger = Logger.getLogger(UserController.class);

    private UserService userService;
    private S3Service s3Service;

    public UserController() {
    }

    @Autowired
    public UserController(UserService userService, S3Service s3Service) {
        this.userService = userService;
        this.s3Service = s3Service;
    }

    @GetMapping("{userId}")
    public ResponseEntity<JsonResponse> getUserGivenId (@PathVariable Integer userId){
        User retrievedUser = userService.getUserGivenId(userId);
        JsonResponse jsonResponse = new JsonResponse(true, "User successfully retrieved", retrievedUser);
        return ResponseEntity.ok(jsonResponse);
    }

    @PostMapping
    public ResponseEntity<JsonResponse> createUser (@RequestBody User userToCreate){
        Integer newUserId = userService.createUser(userToCreate);

        if (newUserId != null){ //user was successfully created
            User createdUser = userService.getUserGivenId(newUserId);
            JsonResponse jsonResponse = new JsonResponse(true, "User Succesfully Created", createdUser);
            return ResponseEntity.ok(jsonResponse);
        }

        JsonResponse jsonResponse = new JsonResponse(false, "Username or Email already taken", null);
        return ResponseEntity.ok(jsonResponse);
    }

    @PutMapping
    public ResponseEntity<JsonResponse> editUserInfo (@RequestBody User userToUpdate){
        // NOTE: user id has to be passed in the body of request or org.hibernate.TransientObjectException will be thrown
        // NOTE: user that is returned to the JSON Response has a creationDate of NULL  <- may need to fix that, or we can work around it

        // updating the user
        userService.updateUserInfo(userToUpdate);

        // returning the updated user
        User updatedUser = userService.getUserGivenId(userToUpdate.getId());
        JsonResponse jsonResponse = new JsonResponse(true, "Information for user with id " + updatedUser.getId() + " was successfully updated.", updatedUser);
        return ResponseEntity.ok(jsonResponse);
    }

    @PostMapping("/profile/{userId}")
    public ResponseEntity<JsonResponse> editUserProfilePicture (@RequestBody File profilePicture, @PathVariable Integer userId){
        // NOTE: Getting 415 unsupported media type error from postman when testing this
        // NOTE: Http 415 Media Unsupported is responded back only when the content type header you are providing is not supported by the application.
        // RequestBody should not be a file


        // todo need to add S3 functionality to add/update profile picture
        User updatedProfilePic = s3Service.uploadProfilePicture(profilePicture,userId);

        JsonResponse jsonResponse = new JsonResponse(true, "Profile picture successfully uploaded", updatedProfilePic);
        return ResponseEntity.ok(jsonResponse);
    }

    @GetMapping("exception-thrown")
    public ResponseEntity<String> throwException(){
        try{
            throw new Exception("OOPSIE");
        }catch (Exception e){
            logger.warn("Stack Trace?", e);
            //e.printStackTrace();
        }

        return ResponseEntity.ok("Exception thrown");
    }

}