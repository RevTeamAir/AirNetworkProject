package com.revature.AirNetwork.controllers;

import com.revature.AirNetwork.models.JsonResponse;
import com.revature.AirNetwork.models.User;
import com.revature.AirNetwork.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "session")
public class SessionController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    public SessionController() {
    }

    @Autowired
    public SessionController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<JsonResponse> loginSession (HttpSession httpSession, @RequestBody User userLoggingIn){
        //validating credentials
        User retrievedUser = userService.validateCredentials(userLoggingIn.getUsername(),userLoggingIn.getPassword());

        logger.trace("Login method accessed");

        // Invalid username or password
        if (retrievedUser == null){
            JsonResponse jsonResponse = new JsonResponse(false, "Invalid Username or Password", null);
            return ResponseEntity.ok(jsonResponse);

        } else { //valid credentials

            // creating session
            httpSession.setAttribute("user", retrievedUser);

            //returning the user in a JsonResponse
            JsonResponse jsonResponse = new JsonResponse(true, "Login Successful", retrievedUser);
            return ResponseEntity.ok(jsonResponse);
        }
    }

    @DeleteMapping
    public ResponseEntity<JsonResponse> logoutSession (HttpSession httpSession){
        httpSession.invalidate();
        JsonResponse jsonResponse = new JsonResponse(true, "Successfully logged out and session invalidated.", null);
        return ResponseEntity.ok(jsonResponse);
    }

    @GetMapping
    public ResponseEntity<JsonResponse> checkSession (HttpSession httpSession){
        //retrieving the user from the session
        User userLoggedIn = (User) httpSession.getAttribute("user");

        if(userLoggedIn == null){
            // session is empty meaning no user is logged in
            JsonResponse jsonResponse = new JsonResponse(false, "No session found", null);
            return ResponseEntity.ok(jsonResponse);
        } else {
            //session is found and user is logged in
            JsonResponse jsonResponse = new JsonResponse(true, "Session found", userLoggedIn);
            return ResponseEntity.ok(jsonResponse);
        }

    }



}



