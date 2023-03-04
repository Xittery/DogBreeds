package com.frostdev.dogbreeds.injection.module

import android.content.Context
import com.frostdev.dogbreeds.helpers.PersistentSettings
import dagger.Module
import dagger.Provides

@Module
object DataModule {

    val ACTIVE_FAVORITES = "activeFavorites"

    @Provides
    fun providePersistentSettings(context: Context): PersistentSettings {
        return PersistentSettings(context)
    }
}