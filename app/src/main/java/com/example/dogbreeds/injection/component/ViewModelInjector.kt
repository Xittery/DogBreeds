package com.example.dogbreeds.injection.component

import com.example.dogbreeds.injection.module.DataModule
import com.example.dogbreeds.injection.module.NetworkModule
import com.example.dogbreeds.viewmodels.AllDogsListViewModel
import com.example.dogbreeds.viewmodels.DetailListViewModel
import com.example.dogbreeds.viewmodels.FavoriteDogsListViewModel
import dagger.Component

import javax.inject.Singleton


@Singleton
@Component(modules = [
    (NetworkModule::class),
    (DataModule::class)
    ])
interface ViewModelInjector {

    fun inject(allDogsListViewModel: AllDogsListViewModel)
    fun inject(favoriteDogsListViewModel: FavoriteDogsListViewModel)
    fun inject(detailViewModel: DetailListViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
        fun dataModule(dataModule: DataModule): Builder
    }
}