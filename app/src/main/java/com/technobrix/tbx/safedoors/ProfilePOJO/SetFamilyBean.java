package com.technobrix.tbx.safedoors.ProfilePOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class SetFamilyBean {

    @SerializedName("status")
    @Expose
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
