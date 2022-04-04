package com.revature.AirNetwork.services;

import com.revature.AirNetwork.models.User;
import com.revature.AirNetwork.repos.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional //ensures that every method in the class waits until the whole method is complete before making the official commit to the database
public class UserService {

    @Autowired
    private UserDao userDao;


    public User createUser(User user){
        Integer userId = userDao.createUser(user);
        User userFromDb = userDao.getOneUser(userId);
        return userFromDb;
    }

    public User getOneById(Integer userId){
        return userDao.getOneUser(userId);
    }

    public User updateUser(User user) {
        userDao.updateUser(user);
        return userDao.getOneUser(user.getId());
    }

    public void deleteUser(Integer userId) {
        User userFromDb = userDao.getOneUser(userId);
        userDao.deleteUser(userFromDb);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }


    public User getOneByGivenUsername(String username) {
        return userDao.getOneByUsername(username);
    }
}
