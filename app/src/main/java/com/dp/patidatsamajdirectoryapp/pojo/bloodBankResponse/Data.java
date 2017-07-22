
package com.dp.patidatsamajdirectoryapp.pojo.bloodBankResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

    @SerializedName("lastIndex")
    @Expose
    private String lastIndex;
    @SerializedName("Users")
    @Expose
    private List<User> users = null;

    public String getLastIndex() {
        return lastIndex;
    }

    public void setLastIndex(String lastIndex) {
        this.lastIndex = lastIndex;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}
