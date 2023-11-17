package com.example.data.remote

import com.example.domain.entity.CategoryRespons
import retrofit2.http.GET

interface APiServices {
    @GET("categories.php")

    suspend fun getMeals():CategoryRespons
}