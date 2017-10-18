package com.technobrix.tbx.safedoors.GetVehiIdPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tvs on 10/16/2017.
 */

public class VehicleList {

    @SerializedName("vehicle_name")
    @Expose
    private String vehicleName;
    @SerializedName("no_of_vehicle")
    @Expose
    private String noOfVehicle;
    @SerializedName("vehicle_no")
    @Expose
    private String vehicleNo;

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getNoOfVehicle() {
        return noOfVehicle;
    }

    public void setNoOfVehicle(String noOfVehicle) {
        this.noOfVehicle = noOfVehicle;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

}
