package com.dani.kotlin.dagger.component

import android.content.Context
import com.dani.kotlin.dagger.module.HeroModule
import com.dani.kotlin.ui.presenter.MainPresenter
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    HeroModule::class
])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun getMainPresenter(): MainPresenter
}