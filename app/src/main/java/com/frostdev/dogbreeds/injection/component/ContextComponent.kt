package com.frostdev.dogbreeds.injection.component

import android.content.Context
import com.frostdev.dogbreeds.injection.module.ContextModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ContextModule::class])
interface ContextComponent {
    fun inject(): Context
}