package com.frontinelabs.rxsample.service;

/**
 * Created by EBaba on 10/10/2017.
 */


import com.frontinelabs.rxsample.model.ModelItems;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SOService {

    @GET("/answers?order=desc&sort=activity&site=stackoverflow")
    Call<ModelItems> getAnswers();

    @GET("/answers?order=desc&sort=activity&site=stackoverflow")
    Call<ModelItems> getAnswers(@Query("tagged") String tags);

    @GET("/answers?order=desc&sort=activity&site=stackoverflow")
    Observable<ModelItems> getAnswersRx();

    @GET("/answers?order=desc&sort=activity&site=stackoverflow")
    Observable<ModelItems> getAnswersRx(@Query("tagged") String tags);
}
