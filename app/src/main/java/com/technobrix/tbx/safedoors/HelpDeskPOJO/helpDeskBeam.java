package com.technobrix.tbx.safedoors.HelpDeskPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by TBX on 9/23/2017.
 */

public class helpDeskBeam {

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
