package ru.nsu.group20211.dinermobile

import android.app.Application
import android.content.Context
import ru.nsu.group20211.dinermobile.di.AppComponent
import ru.nsu.group20211.dinermobile.di.DaggerAppComponent

class App : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is App -> appComponent
        else -> this.applicationContext.appComponent
    }
