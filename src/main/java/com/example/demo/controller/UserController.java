package com.example.demo.controller;

import com.example.demo.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/users")
public class UserController {
    static Map<Long,User> users = new ConcurrentHashMap<>();

    @GetMapping("/")
    public ResponseEntity<List<User>> getUserList() {
        List<User> r = new ArrayList<>(users.values());
        return ResponseEntity.ok(r);
    }
    @PostMapping("/")
    public ResponseEntity postUser(@RequestBody User user) {
        users.put(user.getId(), user);
        return ResponseEntity.ok(users);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") Long id){
        users.remove(id);
        return ResponseEntity.ok(new ArrayList<>(users.values()));
    }
    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") Long id) {
        return users.get(id);
    }
    @PutMapping("/{id}")
    public String putUser(@PathVariable Long id, @RequestBody User user) {
        User u = users.get(id);
        u.setName(user.getName());
        u.setAge(user.getAge());
        users.put(id, u);
        return "success";
    }


}
