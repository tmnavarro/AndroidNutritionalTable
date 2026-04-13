package com.example.nutritionaltable

import android.app.Application
import com.example.nutritionaltable.core.di.AppModules.dataMode
import com.example.nutritionaltable.core.di.AppModules.domainMode
import com.example.nutritionaltable.core.di.AppModules.uiMode
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            modules(
                uiMode,
                domainMode,
                dataMode
            )
        }
    }
}