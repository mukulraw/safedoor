package com.technobrix.tbx.safedoors.FacilityPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class FacilityList {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("facility_img")
    @Expose
    private String facilityImg;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("price_per")
    @Expose
    private String pricePer;
    @SerializedName("available")
    @Expose
    private Integer available;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFacilityImg() {
        return facilityImg;
    }

    public void setFacilityImg(String facilityImg) {
        this.facilityImg = facilityImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPricePer() {
        return pricePer;
    }

    public void setPricePer(String pricePer) {
        this.pricePer = pricePer;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

}
