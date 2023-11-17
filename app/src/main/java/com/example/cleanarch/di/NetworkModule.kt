package com.example.cleanarch.di

import com.example.data.remote.APiServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttp():OkHttpClient{
         return OkHttpClient.Builder().connectTimeout(20,TimeUnit.SECONDS)
             .readTimeout(20,TimeUnit.SECONDS).build()
    }
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient):Retrofit{
        return Retrofit.Builder().client(okHttpClient).baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Provides
    @Singleton
    fun provideApiServices(retrofit: Retrofit):APiServices{
        return retrofit.create(APiServices::class.java)
    }
}