package com.frontinelabs.rxsample.model

import org.json.JSONArray
import retrofit2.http.Query

/**
 * Created by EBaba on 04/10/2017.
 */

object Model{
    data class Result(val query: Query)
    data class Query(val search: JSONArray)

}