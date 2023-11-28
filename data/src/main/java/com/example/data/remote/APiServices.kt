package com.example.data.remote

import com.example.domain.entity.CategoryRespons
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APiServices {
    @GET
    suspend fun getMeals(@Url url:String):CategoryRespons
}
/*
@GET("categories.php")
 */
