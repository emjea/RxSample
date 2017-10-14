package com.frontinelabs.rxsample.service

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory


interface GithubApiService {

    @retrofit2.http.GET("search/users")
    fun search(@retrofit2.http.Query("q") query: String,
               @retrofit2.http.Query("page") page: Int,
               @retrofit2.http.Query("per_page") perPage: Int = 10): io.reactivex.Observable<Result>


    /**
     * Companion object for the factory
     */
    companion object Factory {
        fun create(): GithubApiService {
            val retrofit = retrofit2.Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
                    .baseUrl("https://api.github.com/")
                    .build()

            return retrofit.create(GithubApiService::class.java)
        }
    }

}