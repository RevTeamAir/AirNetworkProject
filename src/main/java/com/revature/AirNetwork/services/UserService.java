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
    private UserDao userDao;

    public UserService() {
    }

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getAllUsers (){
        return this.userDao.getAllUsers();
    }

    public User getUserGivenId(Integer userId){
        return userDao.getUserGivenId(userId);
    }

    /**
     * Returns the current user as a User object if the credentials are valid and null otherwise
     * */
    public User validateCredentials(String username, String password){
        User userLoggingIn = this.userDao.getUserGivenUsername(username);

        //User wasn't found so return null
        if(userLoggingIn == null)
            return null;

        //Invalid password so return null
        if(!password.equals(userLoggingIn.getPassword()))
            return null;

        return userLoggingIn;
    }

    public User updateUserInfo(User user){
        userDao.updateUser(user);
        return userDao.getUserGivenId(user.getId());
    }

    // this doesn't pass the tests for some unknown reason (JUNIT)
    public Integer createUser (User userToCreate) {
        // checking if username and email are available
        // both of these should return null if email and username are available
        User userByUsername = userDao.getUserGivenUsername(userToCreate.getUsername());
        User userByEmail = userDao.getUserGivenEmail(userToCreate.getEmail());

        if (userByUsername == null && userByEmail == null){
            // both the username and email are available (they are not found in the database) -> create the user
            return this.userDao.createUser(userToCreate);
        } else {
            // either the username is taken already or the email is taken already -> return null
            return null;
        }

    }
}
