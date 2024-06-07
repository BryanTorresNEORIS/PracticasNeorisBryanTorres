package com.Practica1.ResourceServer.controller;

import com.Practica1.ResourceServer.model.User;
import com.Practica1.ResourceServer.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("{id}")
    public User GetUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @DeleteMapping("{id}")
    public void DeleteUserById(@PathVariable Long id){
        userService.deleteUser(id);
    }
}
