package com.technobrix.tbx.safedoors.GetVehiIdPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tvs on 10/16/2017.
 */

public class IdVehicleBean {

    @SerializedName("vehicle_list")
    @Expose
    private List<VehicleList> vehicleList = null;

    public List<VehicleList> getVehicleList() {
        return vehicleList;
    }

    public void setVehicleList(List<VehicleList> vehicleList) {
        this.vehicleList = vehicleList;
    }
}
