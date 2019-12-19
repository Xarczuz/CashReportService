package com.CashReportSystem.model;

import org.json.JSONObject;

import javax.persistence.*;

@Entity
@Table(name = "user_profile_table")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String permission;
    private String username;
    private String password;
    private String salt;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", permission='" + permission + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                '}';
    }

    public JSONObject toJsonObject() {

        JSONObject userJSONObject = new JSONObject();
        userJSONObject.put("id", id);
        userJSONObject.put("permission", permission);
        userJSONObject.put("salt", salt);
        userJSONObject.put("username", username);
        userJSONObject.put("password", password);

        return userJSONObject;
    }
}
