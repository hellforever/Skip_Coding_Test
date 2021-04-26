package com.github.hellforever.skiptest1.data;

import androidx.annotation.Keep;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Keep
public class Restaurant implements Serializable {

    @SerializedName("Id")
    private int id;

    @SerializedName("Name")
    private String name;

    @SerializedName("Rating")
    private Rating rating;

    @SerializedName("CuisineTypes")
    private List<CuisineType> cuisineTypeList;

    @SerializedName("Address")
    private Address address;

    @SerializedName("OpeningTime")
    private Date openingTime;

    @SerializedName("Deals")
    private List<Deal> deals;

    @SerializedName("LogoUrl")
    private String logoUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public List<CuisineType> getCuisineTypeList() {
        return cuisineTypeList;
    }

    public void setCuisineTypeList(List<CuisineType> cuisineTypeList) {
        this.cuisineTypeList = cuisineTypeList;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Date getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(Date openingTime) {
        this.openingTime = openingTime;
    }

    public List<Deal> getDeals() {
        return deals;
    }

    public void setDeals(List<Deal> deals) {
        this.deals = deals;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
}
