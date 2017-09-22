package com.technobrix.tbx.safedoors.InventryListPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tvs on 9/22/2017.
 */

public class InventoryBean {

    @SerializedName("inventry_list")
    @Expose
    private List<InventryList> inventryList = null;

    public List<InventryList> getInventryList() {
        return inventryList;
    }

    public void setInventryList(List<InventryList> inventryList) {
        this.inventryList = inventryList;
    }

}
