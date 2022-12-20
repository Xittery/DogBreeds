package com.example.dogbreeds.injection.module

import android.content.Context
import com.example.dogbreeds.helpers.PersistentSettings
import dagger.Module
import dagger.Provides

@Module
object DataModule {

    @Provides
    fun providePersistentSettings(context: Context): PersistentSettings {
        return PersistentSettings(context)
    }
}