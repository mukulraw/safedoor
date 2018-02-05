package com.technobrix.tbx.safedoors.GetFamilyPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class GetFamilyBean {

    @SerializedName("familt_list")
    @Expose
    private List<FamiltList> familtList = null;

    public List<FamiltList> getFamiltList() {
        return familtList;
    }

    public void setFamiltList(List<FamiltList> familtList) {
        this.familtList = familtList;
    }
}
