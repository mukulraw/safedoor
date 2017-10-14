package com.technobrix.tbx.safedoors.InventryListPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class InventryList {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("price_per")
    @Expose
    private String pricePer;
    @SerializedName("qty")
    @Expose
    private String qty;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }
}
