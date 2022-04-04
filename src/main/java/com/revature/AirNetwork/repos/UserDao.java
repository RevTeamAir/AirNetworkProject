package com.revature.AirNetwork.repos;

import com.revature.AirNetwork.models.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();
    User getOneUser(Integer userId);
    Integer createUser(User user);
    void updateUser(User user);
    void deleteUser(User user);

    User getOneByUsername(String username);
}
