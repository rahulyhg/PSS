
package com.dp.patidatsamajdirectoryapp.pojo.bloodBankResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Mobile")
    @Expose
    private String mobile;
    @SerializedName("LastName")
    @Expose
    private String lastName;
    @SerializedName("FatherName")
    @Expose
    private String fatherName;
    @SerializedName("Gotra")
    @Expose
    private String gotra;
    @SerializedName("Tehsil")
    @Expose
    private String tehsil;
    @SerializedName("District")
    @Expose
    private String district;
    @SerializedName("City")
    @Expose
    private String city;
    @SerializedName("State")
    @Expose
    private String state;
    @SerializedName("P_Address")
    @Expose
    private String pAddress;
    @SerializedName("S_Address")
    @Expose
    private String sAddress;
    @SerializedName("Occupation")
    @Expose
    private String occupation;
    @SerializedName("BloodGroup")
    @Expose
    private String bloodGroup;
    @SerializedName("DOB")
    @Expose
    private String dOB;
    @SerializedName("DOA")
    @Expose
    private String dOA;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("marital_statuse")
    @Expose
    private String maritalStatuse;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("WhatsApp")
    @Expose
    private String whatsApp;
    @SerializedName("LandLine")
    @Expose
    private String landLine;
    @SerializedName("NoOfBoys")
    @Expose
    private String noOfBoys;
    @SerializedName("NoOfGirls")
    @Expose
    private String noOfGirls;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getGotra() {
        return gotra;
    }

    public void setGotra(String gotra) {
        this.gotra = gotra;
    }

    public String getTehsil() {
        return tehsil;
    }

    public void setTehsil(String tehsil) {
        this.tehsil = tehsil;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPAddress() {
        return pAddress;
    }

    public void setPAddress(String pAddress) {
        this.pAddress = pAddress;
    }

    public String getSAddress() {
        return sAddress;
    }

    public void setSAddress(String sAddress) {
        this.sAddress = sAddress;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getDOB() {
        return dOB;
    }

    public void setDOB(String dOB) {
        this.dOB = dOB;
    }

    public String getDOA() {
        return dOA;
    }

    public void setDOA(String dOA) {
        this.dOA = dOA;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMaritalStatuse() {
        return maritalStatuse;
    }

    public void setMaritalStatuse(String maritalStatuse) {
        this.maritalStatuse = maritalStatuse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWhatsApp() {
        return whatsApp;
    }

    public void setWhatsApp(String whatsApp) {
        this.whatsApp = whatsApp;
    }

    public String getLandLine() {
        return landLine;
    }

    public void setLandLine(String landLine) {
        this.landLine = landLine;
    }

    public String getNoOfBoys() {
        return noOfBoys;
    }

    public void setNoOfBoys(String noOfBoys) {
        this.noOfBoys = noOfBoys;
    }

    public String getNoOfGirls() {
        return noOfGirls;
    }

    public void setNoOfGirls(String noOfGirls) {
        this.noOfGirls = noOfGirls;
    }

}
