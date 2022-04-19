package com.revature.AirNetwork.controllers;

import com.revature.AirNetwork.models.JsonResponse;
import com.revature.AirNetwork.services.PostService;
import com.revature.AirNetwork.services.S3Service;
import com.revature.AirNetwork.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("file")
@CrossOrigin(origins = {"http://localhost:4200"}, allowCredentials = "true")
public class FileController {

    private S3Service s3Service;
    private UserService userService;
    private PostService postService;

    @Autowired
    public FileController(S3Service s3Service, UserService userService, PostService postService) {
        this.s3Service = s3Service;
        this.userService = userService;
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<JsonResponse> uploadFile (@RequestParam("file") MultipartFile uploadedFile) throws IOException {

        try {
            String fileLocation = s3Service.uploadFile(uploadedFile);
            JsonResponse jsonResponse = new JsonResponse(true, "file successfully uploaded", fileLocation);
            return ResponseEntity.ok(jsonResponse);
        } catch (Exception e) {
            JsonResponse jsonResponse = new JsonResponse(false, "An error occurred", null);
            return ResponseEntity.ok(jsonResponse);
        }
    }
}
