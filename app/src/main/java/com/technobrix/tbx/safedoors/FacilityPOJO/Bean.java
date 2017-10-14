package com.technobrix.tbx.safedoors.FacilityPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Bean {

    @SerializedName("facility_list")
    @Expose
    private List<FacilityList> facilityList = null;

    public List<FacilityList> getFacilityList() {
        return facilityList;
    }

    public void setFacilityList(List<FacilityList> facilityList) {
        this.facilityList = facilityList;
    }

}
