package com.example.cleanarch.di

import android.content.Context
import com.example.data.local.MealDao
import com.example.data.local.MealsRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MealsDatabaseModule {
    @Provides
    @Singleton
    fun provideMealsRoomDatabase(@ApplicationContext context: Context): MealsRoomDatabase {
        return MealsRoomDatabase.getDatabase(context)
    }

    @Provides
    fun provideMealDao(mealsRoomDatabase: MealsRoomDatabase): MealDao {
        return mealsRoomDatabase.mealDao()
    }
}