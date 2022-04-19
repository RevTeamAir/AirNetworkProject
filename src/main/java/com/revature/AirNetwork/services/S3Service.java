package com.revature.AirNetwork.services;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.revature.AirNetwork.models.Post;
import com.revature.AirNetwork.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class S3Service {
    private UserService userService;
    private PostService postService;

    ////////////////// HAVE THESE IN ENVIRONMENT VARIABLES BEFORE PUSHING  ////////////////////////
    private String awsId = System.getenv("S3_BUCKET_ID");
    private String secretKey = System.getenv("S3_BUCKET_SECRETKEY");
    private String region = "us-east-1";
    private String bucketName = "ons3bucket";

    public S3Service() {
    }

    @Autowired
    public S3Service(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    // user id passed from user controller to here using path param
    // profile picture image file passed as a MultipartFile object from user controller
    // returns the updated user with the profile picture location
    public User uploadProfilePicture (MultipartFile file, Integer userId) throws IOException {
        // prepare our credentials for the next statement
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(awsId, secretKey);

        //create a connection with the s3 client
        AmazonS3 s3Client = AmazonS3ClientBuilder
                .standard()
                .withRegion(Regions.fromName(region))
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(file.getContentType());
        objectMetadata.setContentLength(file.getSize());

        // upload the image to S3
        s3Client.putObject(bucketName, "profilePics/" + file.getOriginalFilename(), file.getInputStream(), objectMetadata);

        // Add the image location to the users profile using .setProfilePictureLocation();
        String picLocation = "https://ons3bucket.s3.amazonaws.com/profilePics/" + file.getOriginalFilename();
        User userToUpdate = userService.getUserGivenId(userId);
        userToUpdate.setProfilePictureLocation(picLocation);

        // update the user using userService.updateUser()
        return userService.updateUserInfo(userToUpdate);
    }

    // make this only return the location string and implement this in the create post controller method
    public String addPictureToPost (MultipartFile file) throws IOException {

        // prepare our credentials for the next statement
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(awsId, secretKey);

        //create a connection with the s3 client
        AmazonS3 s3Client = AmazonS3ClientBuilder
                .standard()
                .withRegion(Regions.fromName(region))
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(file.getContentType());
        objectMetadata.setContentLength(file.getSize());

        // upload the image to S3
        s3Client.putObject(bucketName, "postPics/" + file.getOriginalFilename(), file.getInputStream(), objectMetadata);

        return "https://ons3bucket.s3.amazonaws.com/postPics/" + file.getOriginalFilename();

    }

    public String uploadFile(MultipartFile file) throws IOException {
        // prepare our credentials for the next statement
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(awsId, secretKey);

        //create a connection with the s3 client
        AmazonS3 s3Client = AmazonS3ClientBuilder
                .standard()
                .withRegion(Regions.fromName(region))
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(file.getContentType());
        objectMetadata.setContentLength(file.getSize());

        // upload the image to S3
        s3Client.putObject(bucketName, "postPics/" + file.getOriginalFilename(), file.getInputStream(), objectMetadata);

        return "https://ons3bucket.s3.amazonaws.com/AirNetworkPictures/" + file.getOriginalFilename();

    }
}
