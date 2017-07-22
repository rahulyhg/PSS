
package com.dp.patidatsamajdirectoryapp.pojo.directoryStateResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LastDatum {

    @SerializedName("SID")
    @Expose
    private String sID;
    @SerializedName("StateName")
    @Expose
    private String stateName;

    public String getSID() {
        return sID;
    }

    public void setSID(String sID) {
        this.sID = sID;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

}
