package com.example.data.remote

import com.example.data.repo.Constans
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
 object NetworkModule{

    @Provides
    @Singleton
    fun provideOkHttp():OkHttpClient{
         return OkHttpClient.Builder().connectTimeout(20,TimeUnit.SECONDS)
             .readTimeout(20,TimeUnit.SECONDS).build()
    }
    @Provides
    fun providesRetrofitClient(retrofit:Retrofit):RetrofitClient{
        return RetrofitClient(retrofit)
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient):Retrofit{
        return Retrofit.Builder().client(okHttpClient).baseUrl(Constans.Base_Url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Provides
    @Singleton
    fun provideApiServices(retrofit: Retrofit):APiServices{
        return retrofit.create(APiServices::class.java)
    }

}


























/*


 */  /*
    @Provides
    @Singleton
    fun provideApiServices(retrofit:Retrofit):APiServices {
        return retrofit.newBuilder().baseUrl(baseUrl).build().create(APiServices::class.java)
    }
     */