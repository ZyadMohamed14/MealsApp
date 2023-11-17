package com.example.domain.repo

import com.example.domain.entity.CategoryRespons

interface MealsRepo {
   suspend fun getMealFromRemote():CategoryRespons
   suspend fun getMealFromLocal():CategoryRespons
}