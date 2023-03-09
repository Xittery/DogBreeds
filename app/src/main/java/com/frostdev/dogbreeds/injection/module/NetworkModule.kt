package com.frostdev.dogbreeds.injection.module

import com.frostdev.dogbreeds.helpers.GetDrawable
import com.frostdev.dogbreeds.interfaces.DogService
import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule() {

    @Provides
    @Reusable
    fun provideDogsApi(retrofit: Retrofit): DogService {
        return retrofit.create(DogService::class.java)
    }

    @Provides
    @Reusable
    fun provideRetrofitInterface(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    fun provideGetDrawable(): GetDrawable {
        return GetDrawable()
    }
}