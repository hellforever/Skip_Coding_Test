package com.github.hellforever.skiptest1.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Address  implements Serializable {

    @SerializedName("City")
    private String city;

    @SerializedName("FirstLine")
    private String fistLine;

    @SerializedName("Postcode")
    private String postcode;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getFistLine() {
        return fistLine;
    }

    public void setFistLine(String fistLine) {
        this.fistLine = fistLine;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
}
