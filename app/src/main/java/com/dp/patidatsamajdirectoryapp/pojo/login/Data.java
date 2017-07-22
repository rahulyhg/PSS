
package com.dp.patidatsamajdirectoryapp.pojo.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

    @SerializedName("PersonalInfo")
    @Expose
    private PersonalInfo personalInfo;
    @SerializedName("Members")
    @Expose
    private String members;
    @SerializedName("KaryaKari")
    @Expose
    private List<KaryaKarus> karyaKari = null;

    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    public void setPersonalInfo(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
    }

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }

    public List<KaryaKarus> getKaryaKari() {
        return karyaKari;
    }

    public void setKaryaKari(List<KaryaKarus> karyaKari) {
        this.karyaKari = karyaKari;
    }

}
