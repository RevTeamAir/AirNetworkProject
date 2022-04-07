package com.revature.AirNetwork.repos;

import com.revature.AirNetwork.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface UserDao{
    List<User> getAllUsers(); // will be used to display all profiles
    Integer createUser(User user); // will be used to register new user (returns the id of newly created user)
    void updateUser(User user); // will be used to edit profile and upload a profile picture
    User getUserGivenId(Integer userId); // will be used for various tasks
    User getUserGivenUsername(String username); // will be used to log in and register new user
    User getUserGivenEmail(String email); // will be used to register new user

}



