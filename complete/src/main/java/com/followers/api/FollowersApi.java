package com.followers.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.followers.api.model.GraphQlResponseUser;
import com.followers.service.UserFollowersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FollowersApi {

    @Autowired
    private UserFollowersService userFollowersService;

    @Value("${graphql.authorization}")
    private String auth;

    @Value("${graphql.url}")
    private String url;

    @RequestMapping(method = RequestMethod.GET, value= "/followers",produces = {MediaType.APPLICATION_JSON_VALUE})
    public GraphQlResponseUser graphQlCall(@RequestParam(value="id", required = true) String id) throws JsonProcessingException {
        return userFollowersService.getFollowers(id);
    }

}


