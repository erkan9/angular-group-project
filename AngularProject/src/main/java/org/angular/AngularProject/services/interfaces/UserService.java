package org.angular.AngularProject.services.interfaces;

import org.angular.AngularProject.dtos.UserDto;

public interface UserService {

    void deleteUser(int userID);

    UserDto registerUser(UserDto userDto);

    UserDto logIn(String username, String password);

    UserDto getUserById(int userId);

    void updateUser(UserDto userDto);
}