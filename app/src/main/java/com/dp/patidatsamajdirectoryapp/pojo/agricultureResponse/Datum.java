
package com.dp.patidatsamajdirectoryapp.pojo.agricultureResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("nid")
    @Expose
    private String nid;
    @SerializedName("NewsTitle")
    @Expose
    private String newsTitle;
    @SerializedName("NewsDesc")
    @Expose
    private String newsDesc;
    @SerializedName("NewsUrl")
    @Expose
    private String newsUrl;
    @SerializedName("ImagePath")
    @Expose
    private String imagePath;
    @SerializedName("UploadedBy")
    @Expose
    private String uploadedBy;
    @SerializedName("date_time")
    @Expose
    private String dateTime;

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsDesc() {
        return newsDesc;
    }

    public void setNewsDesc(String newsDesc) {
        this.newsDesc = newsDesc;
    }

    public String getNewsUrl() {
        return newsUrl;
    }

    public void setNewsUrl(String newsUrl) {
        this.newsUrl = newsUrl;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(String uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

}
