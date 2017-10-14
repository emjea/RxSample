package com.frontinelabs.rxsample.service

object SearchRepositoryProvider {

    fun provideSearchRepository(): SearchRepository {
        return SearchRepository(GithubApiService.Factory.create())
    }

}