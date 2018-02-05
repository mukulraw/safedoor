package com.technobrix.tbx.safedoors.GetVehiclePOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tvs on 10/16/2017.
 */

public class VehicleList {

    @SerializedName("vehicle_id")
    @Expose
    private String vehicleId;
    @SerializedName("vehicle_name")
    @Expose
    private String vehicleName;
    @SerializedName("no_of_vehicle")
    @Expose
    private String noOfVehicle;
    @SerializedName("vehicle_no")
    @Expose
    private String vehicleNo;
    @SerializedName("rf_id")
    @Expose
    private String rfId;

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

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

    public String getRfId() {
        return rfId;
    }

    public void setRfId(String rfId) {
        this.rfId = rfId;
    }

}
