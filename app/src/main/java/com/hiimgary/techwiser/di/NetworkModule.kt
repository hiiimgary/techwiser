package com.hiimgary.techwiser.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.hiimgary.techwiser.network.TechyService
import dagger.Module;
import dagger.Provides
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl("https://techy-api.vercel.app/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun provideTechyService(retrofit: Retrofit.Builder): TechyService {
        return retrofit
            .build()
            .create(TechyService::class.java)
    }
}
