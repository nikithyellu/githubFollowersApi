package com.followers.api.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public class GraphQlResponseUser {

    private String name;

    @JsonAlias({"databaseId"})
    private String githubId;
    private GraphQlResponseFollowers followers;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGithubId() {
        return githubId;
    }

    public void setGithubId(String githubId) {
        this.githubId = githubId;
    }

    public GraphQlResponseFollowers getFollowers() {
        return followers;
    }

    public void setFollowers(GraphQlResponseFollowers followers) {
        this.followers = followers;
    }

}
