package com.Restdemo.RestDemo.controllers;


import com.Restdemo.RestDemo.Service.UserService;
import com.Restdemo.RestDemo.dto.Createuserdto;
import com.Restdemo.RestDemo.dto.Userdto;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userservice;

    public UserController(UserService userservice) {
        this.userservice = userservice;
    }

    @GetMapping
    public List<Userdto> getallusers() {
        return this.userservice.getallusers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Userdto> getuserbyid(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(userservice.getuserbyid(id));
    }

    @PostMapping
    public ResponseEntity<Userdto> createuser(@RequestBody Createuserdto createuserdto){
        return ResponseEntity.status(HttpStatus.CREATED).body(userservice.createuser(createuserdto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Userdto> updateuser(@RequestBody Createuserdto updateuserdto, @PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(userservice.updateuser(updateuserdto,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteuserbyid(@PathVariable String id) {
        userservice.deleteuserbyid(id);
        return ResponseEntity.noContent().build();
    }

}

