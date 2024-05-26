package com.example.myfin

import android.app.Application
import com.example.myfin.modules.dataSourceModule
import com.example.myfin.modules.globalModule
import com.example.myfin.modules.transactionModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyFinApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyFinApplication)
            koin.loadModules(modules)
        }
    }

    private val modules = listOf(
        dataSourceModule,
        globalModule,
        transactionModule
    )
}