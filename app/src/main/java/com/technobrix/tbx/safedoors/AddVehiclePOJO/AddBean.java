package com.technobrix.tbx.safedoors.AddVehiclePOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class AddBean {

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
