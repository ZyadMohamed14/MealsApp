package com.example.data.repo

import com.example.data.local.MealsRoomDatabase
import com.example.data.remote.APiServices
import com.example.domain.entity.CategoryRespons
import com.example.domain.repo.MealsRepo

class MealsRepoImpl(private val aPiServices: APiServices,private val localDatabase: MealsRoomDatabase) :MealsRepo{
    override suspend fun getMealFromRemote(): CategoryRespons {
        return  aPiServices.getMeals()
    }

    override suspend fun getMealFromLocal(): CategoryRespons {
        return localDatabase.mealDao().getMealsFromLocal()
    }
}