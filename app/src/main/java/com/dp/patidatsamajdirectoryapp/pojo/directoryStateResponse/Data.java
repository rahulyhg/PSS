
package com.dp.patidatsamajdirectoryapp.pojo.directoryStateResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

    @SerializedName("lastIndex")
    @Expose
    private Integer lastIndex;
    @SerializedName("lastData")
    @Expose
    private List<LastDatum> lastData = null;

    public Integer getLastIndex() {
        return lastIndex;
    }

    public void setLastIndex(Integer lastIndex) {
        this.lastIndex = lastIndex;
    }

    public List<LastDatum> getLastData() {
        return lastData;
    }

    public void setLastData(List<LastDatum> lastData) {
        this.lastData = lastData;
    }

}
