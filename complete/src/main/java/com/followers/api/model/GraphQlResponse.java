package com.followers.api.model;

import org.springframework.beans.factory.annotation.Value;

public class GraphQlResponse {

    @Value("data")
    private GraphQlResponseData data;

    public GraphQlResponseData getData() {
        return data;
    }

    public void setData(GraphQlResponseData data) {
        this.data = data;
    }

}
