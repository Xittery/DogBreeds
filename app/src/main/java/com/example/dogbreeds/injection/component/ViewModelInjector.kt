package com.example.dogbreeds.injection.component

import com.example.dogbreeds.injection.module.NetworkModule
import com.example.dogbreeds.viewmodels.AllDogsListViewModel
import com.example.dogbreeds.viewmodels.DetailListViewModel
import com.example.dogbreeds.viewmodels.FavoriteDogsListViewModel
import com.example.dogbreeds.viewmodels.SingleDogViewModel
import dagger.Component

import javax.inject.Singleton


@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    fun inject(allDogsListViewModel: AllDogsListViewModel)
    fun inject(favoriteDogsListViewModel: FavoriteDogsListViewModel)
    fun inject(detailViewModel: DetailListViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}