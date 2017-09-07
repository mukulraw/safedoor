package com.technobrix.tbx.safedoors.flatPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by TBX on 9/7/2017.
 */

public class flatBean {


    @SerializedName("flat_list")
    @Expose
    private List<FlatList> flatList = null;

    public List<FlatList> getFlatList() {
        return flatList;
    }

    public void setFlatList(List<FlatList> flatList) {
        this.flatList = flatList;
    }


}
