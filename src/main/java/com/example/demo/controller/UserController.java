package com.example.demo.controller;

import com.example.demo.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    static Map<Long,User> users = new ConcurrentHashMap<>();

    @GetMapping("/")
    public ResponseEntity<List<User>> getUserList(){
        List<User> r = new ArrayList<>(users.values());
        return ResponseEntity.ok(r);
    }
    @DeleteMapping(value="/{id}",params = "id")
    public ResponseEntity deleteUser(@PathVariable("id") Long id){
        users.remove(id);
        return ResponseEntity.ok(new ArrayList<>(users.values()));
    }


}
