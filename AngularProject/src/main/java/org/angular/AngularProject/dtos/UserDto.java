package org.angular.AngularProject.dtos;

import lombok.Data;

@Data
public class UserDto {

    private int userID;

    private String username;

    private String userFirstName;

    private String userEmail;

    private String password;

    public UserDto() {
    }

    public UserDto(String username, String userFirstName, String userEmail, String password) {
        this.username = username;
        this.userFirstName = userFirstName;
        this.userEmail = userEmail;
        this.password = password;
    }

    public UserDto(int userID, String username, String userFirstName, String userEmail, String password) {
        this.userID = userID;
        this.username = username;
        this.userFirstName = userFirstName;
        this.userEmail = userEmail;
        this.password = password;
    }
}