package com.dani.kotlin.dagger

import android.app.Application
import com.dani.kotlin.dagger.component.AppComponent
import com.dani.kotlin.dagger.component.DaggerAppComponent

open class BaseApplication : Application() {

    val appComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }
}