package com.frostdev.dogbreeds.viewmodels

import androidx.lifecycle.ViewModel
import com.frostdev.dogbreeds.injection.component.DaggerInjector
import com.frostdev.dogbreeds.injection.component.Injector
import com.frostdev.dogbreeds.injection.module.DataModule
import com.frostdev.dogbreeds.injection.module.NetworkModule

abstract class BaseViewModel: ViewModel(){
    private val injector: Injector = DaggerInjector
        .builder()
        .networkModule(NetworkModule())
        .dataModule(DataModule())
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is AllDogsListViewModel -> injector.inject(this)
            is FavoriteDogsListViewModel -> injector.inject(this)
            is DetailListViewModel -> injector.inject(this)
        }
    }
}