package com.revature.AirNetwork.services;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.revature.AirNetwork.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class S3Service {
    private UserService userService;

    ////////////////// HAVE THESE IN ENVIRONMENT VARIABLES BEFORE PUSHING  ////////////////////////
    private String awsId = System.getenv("S3_BUCKET_ID");
    private String secretKey = System.getenv("S3_BUCKET_SECRETKEY");
    private String region = "us-east-1";
    private String bucketName = "ons3bucket";

    public S3Service() {
    }

    @Autowired
    public S3Service(UserService userService) {
        this.userService = userService;
    }

    // user id passed from user controller to here using path param
    // profile picture image file passed as a File object from user controller
    // returns the updated user with the profile picture location
    public User uploadProfilePicture (File file, Integer userId){
        // prepare our credentials for the next statement
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(awsId, secretKey); // getEnv();

        //create a connection with the s3 client
        AmazonS3 s3Client = AmazonS3ClientBuilder
                .standard()
                .withRegion(Regions.fromName(region))
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();

        // upload the image to S3
        s3Client.putObject(bucketName, "profilePics/" + file.getName(), file);

        // Add the image location to the users profile using .setProfilePictureLocation();
        String picLocation = "https://ons3bucket.s3.amazonaws.com/profilePics/" + file.getName();
        User userToUpdate = userService.getUserGivenId(userId);
        userToUpdate.setProfilePictureLocation(picLocation);

        // update the user using userService.updateUser()
        return userService.updateUserInfo(userToUpdate);
    }

    public void uploadPostPicture(File file, Integer postId){
        //todo write this method

    }
}
