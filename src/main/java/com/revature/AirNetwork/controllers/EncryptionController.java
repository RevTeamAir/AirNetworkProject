package com.revature.AirNetwork.controllers;

import com.revature.AirNetwork.services.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/encryption/")
@CrossOrigin(origins = {"http://localhost:4200"}, allowCredentials = "true")
public class EncryptionController {

    @Autowired
    private EncryptionService encryptionService;

    @GetMapping("encrypt")
    public String encrypt(@RequestParam String data){
        //System.out.println("**************" + data + " " + encryptionService.encrypt(data));
        String result = encryptionService.encrypt(data);

        //lets decrypt
        String decrypt = encryptionService.decrypt(result);
        //System.out.println("***********decrypt " + decrypt);
        return result;
    }

    @GetMapping("decrypt")
    public String decrypt(@RequestParam String data){
        return encryptionService.decrypt(data);
    }
}
