package com.javisc.roomexample

import android.app.Application
import android.content.Context
import com.javisc.roomexample.di.modules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    companion object {
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this.applicationContext

        startKoin{
            androidLogger()
            androidContext(this@App)
            modules(modules)
        }
    }
}
