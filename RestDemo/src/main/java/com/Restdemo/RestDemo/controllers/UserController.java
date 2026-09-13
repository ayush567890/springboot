package com.Restdemo.RestDemo.controllers;

import com.Restdemo.RestDemo.Service.UserService;
import com.Restdemo.RestDemo.dto.Createuserdto;
import com.Restdemo.RestDemo.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;


import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController{
    private final UserService userservice;

    public UserController(UserService userservice){
        this.userservice = userservice;
    }

    @PostMapping
    public ResponseEntity<?> creatuser(@RequestBody Createuserdto dto){
        try{
            User user = userservice.createuser(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> getallusers(){
        return ResponseEntity.ok(userservice.getallusers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>getuserbyid(@PathVariable UUID id){
        try{
            User user = userservice.getuserbyid(id);
            if(user == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }

            return ResponseEntity.ok(user);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteuser(@PathVariable UUID id) {
        try {
            userservice.deleteuser(id);
            return ResponseEntity.noContent().build();

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }
}