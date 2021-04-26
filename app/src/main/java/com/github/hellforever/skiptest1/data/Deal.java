package com.github.hellforever.skiptest1.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Deal implements Serializable {
    @SerializedName("Description")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
