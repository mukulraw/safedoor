package com.technobrix.tbx.safedoors.SocityPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



public class SocityList {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("socity_name")
    @Expose
    private String socityName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSocityName() {
        return socityName;
    }

    public void setSocityName(String socityName) {
        this.socityName = socityName;
    }
}
