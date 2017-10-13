
package com.frontinelabs.rxsample.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataProductShop {

    @SerializedName("product_id")
    @Expose
    private String productId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("sale_price")
    @Expose
    private String salePrice;
    @SerializedName("tag")
    @Expose
    private String tag;
    @SerializedName("brand")
    @Expose
    private String brand;
    @SerializedName("vendor")
    @Expose
    private String vendor;
    @SerializedName("discount")
    @Expose
    private String discount;
    @SerializedName("discount_type")
    @Expose
    private String discountType;
    @SerializedName("category_name")
    @Expose
    private String categoryName;
    @SerializedName("brand_name")
    @Expose
    private String brandName;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("main_thumb")
    @Expose
    private List<String> mainThumb = null;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public List<String> getMainThumb() {
        return mainThumb;
    }

    public void setMainThumb(List<String> mainThumb) {
        this.mainThumb = mainThumb;
    }

}
