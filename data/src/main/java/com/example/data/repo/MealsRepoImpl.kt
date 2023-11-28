package com.example.data.repo

import com.example.data.local.MealsRoomDatabase
import com.example.data.remote.APiServices
import com.example.domain.entity.Category
import com.example.domain.entity.CategoryRespons
import com.example.domain.repo.MealsRepo
import javax.inject.Inject

class MealsRepoImpl @Inject constructor(private val aPiServices: APiServices,private val localDatabase: MealsRoomDatabase) :MealsRepo {
    override suspend fun getMealFromRemote(): CategoryRespons {
        return aPiServices.getMeals(Constans.Category_Base_Url)
    }


    override suspend fun getMealFromLocal(): List<Category> {
        return localDatabase.mealDao().getMealFromLocal()
    }

    override suspend fun addMeal(category: Category) {
        localDatabase.mealDao().insert(category)
    }
}
/*

 */