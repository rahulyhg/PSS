
package com.dp.patidatsamajdirectoryapp.pojo.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GroupMembersDetail {

    @SerializedName("MemeberName")
    @Expose
    private String memeberName;
    @SerializedName("MemberMobile")
    @Expose
    private String memberMobile;
    @SerializedName("Position")
    @Expose
    private String position;
    @SerializedName("OrderId")
    @Expose
    private String orderId;

    public String getMemeberName() {
        return memeberName;
    }

    public void setMemeberName(String memeberName) {
        this.memeberName = memeberName;
    }

    public String getMemberMobile() {
        return memberMobile;
    }

    public void setMemberMobile(String memberMobile) {
        this.memberMobile = memberMobile;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

}
