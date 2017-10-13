package com.frontinelabs.rxsample.service;


import com.frontinelabs.rxsample.model.ModelItems;
import com.frontinelabs.rxsample.model.ModelProduct;
import com.frontinelabs.rxsample.model.ModelSlider;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RequestInterface {

    @GET("/answers?order=desc&sort=activity&site=stackoverflow")
    Observable<ModelItems> register();

    @POST("/shop/product_by_category/69")
    @FormUrlEncoded
    Observable<ModelProduct> load_product(@Field("orderby") String orderby);

    @GET("/main/slider")
    Observable<ModelSlider> load_slider();
}
