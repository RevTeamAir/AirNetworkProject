package com.revature.AirNetwork.controllers;

import com.revature.AirNetwork.models.JsonResponse;
import com.revature.AirNetwork.models.User;
import com.revature.AirNetwork.services.S3Service;
import com.revature.AirNetwork.services.UserService;
/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;


@RestController
@RequestMapping("user")
public class UserController {

    private S3Service s3Service;
    private UserService userService;

    public UserController() {
    }

    @Autowired
    public UserController(UserService userService, S3Service s3Service) {
        this.userService = userService;
        this.s3Service = s3Service;
    }

    @GetMapping
    public ResponseEntity<JsonResponse> getAllUsers(){
        List<User> allUsers = this.userService.getAllUsers();
        JsonResponse jsonResponse = new JsonResponse(true, "All Users:", allUsers);
        return ResponseEntity.ok(jsonResponse);
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

    @PostMapping("/upload/{userId}")
    public ResponseEntity<JsonResponse> editUserProfilePicture (@RequestParam("file") MultipartFile uploadedFile, @PathVariable Integer userId){

        try {
           User updatedProfilePic = s3Service.uploadProfilePicture(uploadedFile,userId);
            JsonResponse jsonResponse = new JsonResponse(true, "Profile picture successfully uploaded", updatedProfilePic);
            return ResponseEntity.ok(jsonResponse);

        } catch (Exception e) {
            e.printStackTrace();
            JsonResponse jsonResponse = new JsonResponse(false, "file loading is not successful", null);
            return ResponseEntity.ok(jsonResponse);
        }

    }

    @PatchMapping("{userId}")
    public ResponseEntity<JsonResponse> deleteUserProfilePicture(@PathVariable Integer userId){

        try {
            User userToUpdate = userService.getUserGivenId(userId);
            userToUpdate.setProfilePictureLocation(null);

            // persist profile picture change in database
            User updatedUser = userService.updateUserInfo(userToUpdate);

            JsonResponse jsonResponse = new JsonResponse(true, "Profile Picture Deleted", updatedUser);
            return ResponseEntity.ok(jsonResponse);

        } catch (NullPointerException e){

            JsonResponse jsonResponse = new JsonResponse(false, "User Not Found", null);
            return ResponseEntity.ok(jsonResponse);
        }

    }

}