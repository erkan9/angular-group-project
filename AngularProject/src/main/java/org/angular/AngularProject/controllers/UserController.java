package org.angular.AngularProject.controllers;

import org.angular.AngularProject.dtos.UserDto;
import org.angular.AngularProject.services.interfaces.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
@Validated
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/user", params = {"userID"})
    public UserDto getById(@RequestParam(name = "userID")
                           @Positive(message = "User ID must be a positive number")
                           int userID) {
        return userService.getUserById(userID);
    }

    @DeleteMapping(value = "/user/delete", params = {"userID"})
    public void deleteUser(@RequestParam(name = "userID")
                           @Positive(message = "User ID must be a positive number")
                           int userID) {

        userService.deleteUser(userID);
    }

    @PostMapping("/user/register")
    public UserDto registerUser(@Valid @RequestBody UserDto userDto) {

        return userService.registerUser(userDto);
    }

    @PostMapping(value = "/user/login", params = {"username", "password"})
    public UserDto registerUser(@RequestParam(name = "username")
                                @NotNull
                                @NotEmpty
                                @NotBlank
                                String username,
                                @RequestParam(name = "password")
                                @NotNull
                                @NotEmpty
                                @NotBlank
                                String password) {

        return userService.logIn(username, password);
    }

    @PatchMapping("/user/update")
    public void createJobAdd(@RequestBody UserDto userDto) {

        userService.updateUser(userDto);
    }
}