package com.transportia.transport.Jwt;

/**
 * This class represents the request from the user. The user sends username & password
 *  in the request
 */

public class UsernameAndPasswordAuthenticationRequest {

    private String username;
    private String password;

    public UsernameAndPasswordAuthenticationRequest() {
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
}
