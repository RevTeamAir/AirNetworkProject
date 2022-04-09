package com.revature.AirNetwork.controllers;

import com.revature.AirNetwork.services.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/encryption/")
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
