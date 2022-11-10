package org.angular.AngularProject.mappers;

import org.angular.AngularProject.dtos.UserDto;
import org.angular.AngularProject.entities.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public User mapUserDtoToUser(UserDto userDto) {

        return new User(userDto.getUserID(), userDto.getUsername(), userDto.getUserFirstName(), userDto.getUserEmail(), userDto.getPassword());
    }

    public UserDto mapUserToUserDto(User user) {

        return new UserDto(user.getUserID(), user.getUsername(), user.getUserFirstName(), user.getUserEmail(), user.getPassword());
    }

    public List<UserDto> mapListOfUserToUserDto(List<User> allUsers) {

        return allUsers.stream().map(this::mapUserToUserDto).collect(Collectors.toList());
    }
}