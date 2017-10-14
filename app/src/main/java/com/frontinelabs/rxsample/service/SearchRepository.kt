package com.frontinelabs.rxsample.service

import android.util.Log

/**
 * Created by EBaba on 13/10/2017.
 */
class SearchRepository(val apiService: GithubApiService) {

    fun searchUsers(location: String, language: String, page: Int): io.reactivex
    .Observable<Result> {
        Log.e("searchUsers", page.toString())
        Log.e("searchUsers", "location:$location language:$language")

        return apiService.search(query = "location:$location language:$language", page = page)
    }

}