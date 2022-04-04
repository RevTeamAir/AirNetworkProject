package com.revature.AirNetwork.controllers;

import com.revature.AirNetwork.models.User;
import com.revature.AirNetwork.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return this.userService.getAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @GetMapping("{userId}")
    public User getOneById(@PathVariable Integer userId){
        User userFromDb = userService.getOneById(userId);
        return userFromDb;
    }

    @PutMapping
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/userId")
    public String deleteUser(@PathVariable Integer userId){
        userService.deleteUser(userId);
        return "user with id" + userId + "was deleted if exists";
    }

    @GetMapping("username/{username}")
    public User getUserGivenUsername(@PathVariable String username){
        return this.userService.getOneByGivenUsername(username);

    }

}