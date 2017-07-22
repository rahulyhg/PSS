
package com.dp.patidatsamajdirectoryapp.pojo.login;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KaryaKarus {

    @SerializedName("KGID")
    @Expose
    private String kGID;
    @SerializedName("GroupName")
    @Expose
    private String groupName;
    @SerializedName("GroupDistict")
    @Expose
    private String groupDistict;
    @SerializedName("GroupCity")
    @Expose
    private String groupCity;
    @SerializedName("GroupState")
    @Expose
    private String groupState;
    @SerializedName("GroupMembersDetail")
    @Expose
    private List<GroupMembersDetail> groupMembersDetail = null;

    public String getKGID() {
        return kGID;
    }

    public void setKGID(String kGID) {
        this.kGID = kGID;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupDistict() {
        return groupDistict;
    }

    public void setGroupDistict(String groupDistict) {
        this.groupDistict = groupDistict;
    }

    public String getGroupCity() {
        return groupCity;
    }

    public void setGroupCity(String groupCity) {
        this.groupCity = groupCity;
    }

    public String getGroupState() {
        return groupState;
    }

    public void setGroupState(String groupState) {
        this.groupState = groupState;
    }

    public List<GroupMembersDetail> getGroupMembersDetail() {
        return groupMembersDetail;
    }

    public void setGroupMembersDetail(List<GroupMembersDetail> groupMembersDetail) {
        this.groupMembersDetail = groupMembersDetail;
    }

}
