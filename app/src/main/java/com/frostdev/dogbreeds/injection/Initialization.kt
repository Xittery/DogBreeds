package com.frostdev.dogbreeds.injection

import android.app.Application
import com.frostdev.dogbreeds.injection.component.ContextComponent
import com.frostdev.dogbreeds.injection.component.DaggerContextComponent
import com.frostdev.dogbreeds.injection.module.ContextModule

class Initialization : Application() {

    override fun onCreate() {
        super.onCreate()

        contextComponent = DaggerContextComponent.builder()
            .contextModule(ContextModule(this.applicationContext))
            .build()
    }

    companion object {
        lateinit var contextComponent: ContextComponent
    }
}