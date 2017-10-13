
package com.frontinelabs.rxsample.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelProduct {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("view_of")
    @Expose
    private Integer viewOf;
    @SerializedName("total_of")
    @Expose
    private Integer totalOf;
    @SerializedName("category_name")
    @Expose
    private String categoryName;
    @SerializedName("data")
    @Expose
    private List<DataProductShop> data = null;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getViewOf() {
        return viewOf;
    }

    public void setViewOf(Integer viewOf) {
        this.viewOf = viewOf;
    }

    public Integer getTotalOf() {
        return totalOf;
    }

    public void setTotalOf(Integer totalOf) {
        this.totalOf = totalOf;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<DataProductShop> getData() {
        return data;
    }

    public void setData(List<DataProductShop> data) {
        this.data = data;
    }

}
