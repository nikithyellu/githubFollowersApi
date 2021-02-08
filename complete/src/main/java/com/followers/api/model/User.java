package com.followers.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Value;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    @Value("login")
    private String login;
    public void setlogin(String login) {
        this.login = login;
    }
    public String getlogin(){
        return login;
    }
}
