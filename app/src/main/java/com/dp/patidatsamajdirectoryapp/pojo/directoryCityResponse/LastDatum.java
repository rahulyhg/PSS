
package com.dp.patidatsamajdirectoryapp.pojo.directoryCityResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LastDatum {

    @SerializedName("City")
    @Expose
    private String city;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
