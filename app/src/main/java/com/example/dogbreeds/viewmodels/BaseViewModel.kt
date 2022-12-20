package com.example.dogbreeds.viewmodels

import androidx.lifecycle.ViewModel
import com.example.dogbreeds.injection.component.DaggerViewModelInjector
import com.example.dogbreeds.injection.module.NetworkModule
import com.example.dogbreeds.injection.component.ViewModelInjector

abstract class BaseViewModel: ViewModel(){
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
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