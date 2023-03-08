package com.frostdev.dogbreeds.injection.module

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ContextModule(private val context: Context) {

    @Singleton
    @Provides
    fun providesContext(): Context {
        return context
    }
}