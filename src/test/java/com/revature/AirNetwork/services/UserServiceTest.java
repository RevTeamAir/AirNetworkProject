package com.revature.AirNetwork.services;

import com.revature.AirNetwork.models.User;
import com.revature.AirNetwork.repos.UserDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {
    private UserService userService;
    private UserDao userDao = Mockito.mock(UserDao.class);

    public UserServiceTest(){
        this.userService = new UserService(userDao);
    }


    @Test
    void getUserGivenId() {
    }

    @Test
    void validateCredentialsInvalidUsername() {
        //arrange
        String expectedUsername = "incorrectusername";
        String expectedPassword = "pass123";
        User expectedOutput = null;
        Mockito.when(this.userDao.getUserGivenUsername(expectedUsername)).thenReturn(expectedOutput);

        //act
        User actualOutput = this.userService.validateCredentials(expectedUsername, expectedPassword);

        //assert
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void validateCredentialsInvalidPassword() {
        //arrange
        String expectedUsername = "correctusername";
        String expectedPassword = "pass123";
        User expectedOutput = null;
        User userFromDb = new User("correctusername", "pass1234", "firstname", "lastname", "lalitha@gmail.com");
        Mockito.when(userDao.getUserGivenUsername(expectedUsername)).thenReturn(userFromDb);

        //act
        User actualOutput = userService.validateCredentials(expectedUsername,expectedPassword);

        //assert
        Assertions.assertEquals(expectedOutput, actualOutput);

    }

    @Test
    void validateCredentialsInvalidCredentials() {
        //arrange
        String expectedUsername = "correctusername";
        String expectedPassword = "correctpassword";
        User expectedOutput = new User(expectedUsername, expectedPassword, "user", "one", "user@gmail.com");
        Mockito.when(userDao.getUserGivenUsername(expectedUsername)).thenReturn(expectedOutput);

        //act
        User actualOutput = userService.validateCredentials(expectedUsername, expectedPassword);

        //assert
        assertEquals(expectedOutput, actualOutput);
    }



    @Test
    void updateUserInfo() {
        //arrange
        User userToPass = new User("username", "password", "user", "one", "user@gmail.com");

        //act
        userService.updateUserInfo(userToPass);

        //assert
        Mockito.verify(userDao, Mockito.times(1)).updateUser(userToPass);
    }

    @Test
    void createUser() {
        //arrange
        User userToPass = new User("username", "password", "user", "one", "user@gmail.com");

        //act
        userService.createUser(userToPass);

        //assert
        Mockito.verify(userDao, Mockito.times(1)).createUser(userToPass);
    }



}