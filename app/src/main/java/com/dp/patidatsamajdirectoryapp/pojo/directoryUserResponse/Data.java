
package com.dp.patidatsamajdirectoryapp.pojo.directoryUserResponse;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("lastIndex")
    @Expose
    private Object lastIndex;
    @SerializedName("lastData")
    @Expose
    private List<LastDatum> lastData = null;

    public Object getLastIndex() {
        return lastIndex;
    }

    public void setLastIndex(Object lastIndex) {
        this.lastIndex = lastIndex;
    }

    public List<LastDatum> getLastData() {
        return lastData;
    }

    public void setLastData(List<LastDatum> lastData) {
        this.lastData = lastData;
    }

}
