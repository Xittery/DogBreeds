package com.frostdev.dogbreeds.injection.module

import com.frostdev.dogbreeds.helpers.PersistentDogs
import com.frostdev.dogbreeds.injection.Initialization
import dagger.Module
import dagger.Provides

@Module
class DataModule() {

    @Provides
    fun providePersistentDogs(): PersistentDogs {
        return PersistentDogs(Initialization.contextComponent.inject())
    }

}
