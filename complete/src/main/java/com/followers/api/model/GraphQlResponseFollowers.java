package com.followers.api.model;

import java.util.ArrayList;

public class GraphQlResponseFollowers {

    private ArrayList<GraphQlResponseEdges> edges;

    public ArrayList<GraphQlResponseEdges> getEdges() {
        return edges;
    }

    public void setEdges(ArrayList<GraphQlResponseEdges> edges) {
        this.edges = edges;
    }

}
