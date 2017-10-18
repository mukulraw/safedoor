package com.technobrix.tbx.safedoors.GetHelpPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tvs on 10/16/2017.
 */

public class HelpBean {

    @SerializedName("help_list")
    @Expose
    private List<HelpList> helpList = null;

    public List<HelpList> getHelpList() {
        return helpList;
    }

    public void setHelpList(List<HelpList> helpList) {
        this.helpList = helpList;
    }
}
