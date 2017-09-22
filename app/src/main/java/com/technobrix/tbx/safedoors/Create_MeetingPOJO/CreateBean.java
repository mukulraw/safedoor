package com.technobrix.tbx.safedoors.Create_MeetingPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CreateBean {

    @SerializedName("status")
    @Expose
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
