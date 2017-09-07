package com.technobrix.tbx.safedoors.SocityPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;



public class SocityBean {
    @SerializedName("socity_list")
    @Expose
    private List<SocityList> socityList = null;

    public List<SocityList> getSocityList() {
        return socityList;
    }

    public void setSocityList(List<SocityList> socityList) {
        this.socityList = socityList;
    }
}
