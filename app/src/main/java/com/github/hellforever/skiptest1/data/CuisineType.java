package com.github.hellforever.skiptest1.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CuisineType implements Serializable {

    @SerializedName("Id")
    private int id;

    @SerializedName("IsTopCuisine")
    private boolean isTopCuisine;

    @SerializedName("Name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isTopCuisine() {
        return isTopCuisine;
    }

    public void setTopCuisine(boolean topCuisine) {
        isTopCuisine = topCuisine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
