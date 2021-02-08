package com.followers.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.followers.api.model.GraphQlResponse;
import com.followers.api.model.GraphQlResponseUser;
import com.followers.api.model.User;
import com.followers.exception.BadRequest;
import com.followers.exception.InternalServerError;
import com.followers.exception.NotFound;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserFollowersService {

    @Value("${graphql.authorization}")
    private String auth;

    @Value("${graphql.url}")
    private String url;

    @Value("${graphql.noOfFollowers}")
    private String noOfFollowers;

    public GraphQlResponseUser getFollowers(String id) throws JsonProcessingException {
        if(id.isEmpty()){
            throw new BadRequest(String.format("Id can't be null"));
        }
        ObjectMapper Obj = new ObjectMapper();
        GraphQlResponse user = new GraphQlResponse();
        String userName= null;
        try{
            userName = getUsername(id);
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
            String query = formatGraphQlRequest(userName);
            response = restTemplate.postForEntity(url+"graphql", new HttpEntity<>(query, headers), String.class);
            user = new ObjectMapper().readValue(response.getBody().toString(), GraphQlResponse.class);
        }else{
            throw new NotFound(String.format("User not found for the id"));
        }
        return user.getData().getUser();
    }

    //Formatting query for graphQl request
    public String formatGraphQlRequest(String userName){
        // query is a grapql query wrapped into a String
        String query = "{\n" +
                "    \"query\":\"query{user(login:\\\""+userName+"\\\"){name databaseId followers(first:"+noOfFollowers+") {edges {" +
                "        node {          name          databaseId          followers(first:"+noOfFollowers+") {            edges {    " +
                "          node {                name                databaseId                followers(first:"+noOfFollowers+") {     " +
                "             edges{                    node{                      name                      databaseId " +
                "followers(first:"+noOfFollowers+") { edges{                    node{                      name                      databaseId }}}"+
                "                   }                  }                }              }            }          }        }      }    }  }}\"\n" +
                "}";
        return query;
    }

    //Github API call to fetch username based on the id passed in the request
    public String getUsername(String userId) throws  JsonProcessingException {
        User user=null;
        RestTemplate restTemplate = new RestTemplate();
        try{
            ResponseEntity<String> response = restTemplate.getForEntity(url+"user/"+userId+"", String.class);
            user = new ObjectMapper().readValue(response.getBody().toString(), User.class);
        }catch(Exception e){
            if(e.getMessage().startsWith("404 Not Found")){
                throw new NotFound(String.format("User not found"));
            }
        }
        return user.getlogin();
    }
}
