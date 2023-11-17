package com.example.cleanarch.di

import com.example.data.local.MealsRoomDatabase
import com.example.data.remote.APiServices
import com.example.data.repo.MealsRepoImpl
import com.example.domain.repo.MealsRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {
    @Provides
    fun provideRepo(api: APiServices,localDatabase: MealsRoomDatabase): MealsRepo{

        return MealsRepoImpl(api,localDatabase)
    }
}