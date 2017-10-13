
package com.frontinelabs.rxsample.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataSlider {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("slide_url")
    @Expose
    private String slideUrl;
    @SerializedName("img_url")
    @Expose
    private String imgUrl;
    @SerializedName("method")
    @Expose
    private String method;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlideUrl() {
        return slideUrl;
    }

    public void setSlideUrl(String slideUrl) {
        this.slideUrl = slideUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

}
