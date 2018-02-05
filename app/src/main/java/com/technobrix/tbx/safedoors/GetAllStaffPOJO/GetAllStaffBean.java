package com.technobrix.tbx.safedoors.GetAllStaffPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by USER on 18-01-2018.
 */

public class GetAllStaffBean {

    @SerializedName("staff_list")
    @Expose
    private List<StaffList> staffList = null;

    public List<StaffList> getStaffList() {
        return staffList;
    }

    public void setStaffList(List<StaffList> staffList) {
        this.staffList = staffList;
    }


}
