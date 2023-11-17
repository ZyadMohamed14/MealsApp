package com.example.cleanarch.di

import com.example.cleanarch.MealsAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MealsAdapterModule {

    @Provides
    @Singleton
    fun provideMealsAdapter(): MealsAdapter {
        return MealsAdapter()
    }
}