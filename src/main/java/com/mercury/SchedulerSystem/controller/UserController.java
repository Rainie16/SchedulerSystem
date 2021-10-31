package com.mercury.SchedulerSystem.controller;

import com.mercury.SchedulerSystem.bean.User;
import com.mercury.SchedulerSystem.http.Response;
import com.mercury.SchedulerSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) { return userService.getById(id); }

    @GetMapping("/name/{name}")
    public User getUserByUsername(@PathVariable String name) {
        return userService.getByName(name);
    }

    @PostMapping
    public Response addUser(@RequestBody User user) {
        return userService.register(user);
    }

    @PutMapping
    public Response changePassword(@RequestBody User user, Authentication authentication) {
        return userService.changePassword(user, authentication);
    }

    @DeleteMapping
    public Response deleteUser(@RequestBody int id) { return userService.deleteUser(id); }
}
