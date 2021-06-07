package com.example.sharing.controller;

import com.example.sharing.entity.User;
import com.example.sharing.service.MaterialService;
import com.example.sharing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class MaterialController {

    @Autowired

    MaterialService materialService;
    @Autowired
    UserService userService;

    @GetMapping("/downloadmaterial")
    public void downloadMaterial(@RequestParam("id")Long id, HttpServletResponse response) throws IOException {
        materialService.downloadMaterial(id,response);
    }

    @DeleteMapping("/deletematerial")
    public void deleteMaterial(@RequestParam("id")Long id,@RequestParam("userid") Long userId){
        User deleter =userService.getById(userId);
        System.out.println(deleter);
        if(deleter.getIsAdmin()==1){
            materialService.deleteMaterial(id);
        }
    }

    @PostMapping("/uploadmaterial")
    public void uploadMaterial(@RequestParam("file") MultipartFile file, @RequestParam("userid")Long userid) {
        User user=userService.getById(userid);
        materialService.uploadMaterial(file,user);
    }
    @GetMapping("/findall")
    public List findAll(){
        return materialService.findAll();
    }



}