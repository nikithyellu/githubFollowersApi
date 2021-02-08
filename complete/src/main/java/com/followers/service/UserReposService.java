package com.followers.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.followers.api.model.GraphQlResponse;
import com.followers.api.model.GraphQlResponseUser;
import com.followers.exception.BadRequest;
import com.followers.exception.InternalServerError;
import com.followers.exception.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class UserReposService {

    @Value("${graphql.authorization}")
    private String auth;

    @Value("${graphql.url}")
    private String url;

    @Autowired
    private UserFollowersService userFollowersService;

    public GraphQlResponseUser getFollowers(String id) throws JsonProcessingException {
        if(id.isEmpty()){
            throw new BadRequest(String.format("Id can't be null"));
        }
        ObjectMapper Obj = new ObjectMapper();
        GraphQlResponse user = new GraphQlResponse();
        String userName= null;
        try{
            userName = userFollowersService.getUsername(id);
        }catch(Exception e){
            if(e.getMessage().startsWith("404")){
                throw new NotFound(e.getMessage());
            }else{
                throw new InternalServerError(String.format("Username fetch error"));
            }
        }
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = null;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", auth);
        headers.add("content-type", "application/json");
        if(userName !=null){
            String query = formatGraphQlReposRequest(userName);
            response = restTemplate.postForEntity(url+"graphql", new HttpEntity<>(query, headers), String.class);
            user = new ObjectMapper().readValue(response.getBody().toString(), GraphQlResponse.class);
        }else{
            throw new NotFound(String.format("User not found for the id"));
        }
        return user.getData().getUser();
    }

    private String formatGraphQlReposRequest(String userName) {

        return null;
    }
}
