package com.technobrix.tbx.safedoors.flatPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TBX on 9/7/2017.
 */

public class FlatList {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("house_no")
    @Expose
    private String houseNo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

}
