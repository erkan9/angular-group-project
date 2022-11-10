package org.angular.AngularProject.entities;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true, updatable = false, insertable = false, nullable = false)
    private int userID;

    @Column(name = "username", length = 50, unique = true, updatable = true, insertable = true, nullable = false)
    private String username;

    @Column(name = "user_first_name", length = 50, unique = false, updatable = true, insertable = true, nullable = false)
    private String userFirstName;

    @Column(name = "user_email", length = 50, unique = true, updatable = true, insertable = true, nullable = false)
    private String userEmail;

    @Column(name = "user_password", length = 500, unique = false, updatable = true, insertable = true, nullable = false)
    private String password;

    public User() {
    }

    public User(String username, String userFirstName, String userEmail, String password) {
        this.username = username;
        this.userFirstName = userFirstName;
        this.userEmail = userEmail;
        this.password = password;
    }

    public User(int userID, String username, String userFirstName, String userEmail, String password) {
        this.userID = userID;
        this.username = username;
        this.userFirstName = userFirstName;
        this.userEmail = userEmail;
        this.password = password;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}