package com.dani.kotlin.dagger.component

import com.dani.kotlin.dagger.module.HeroModule
import com.dani.kotlin.framework.HeroSource
import dagger.Component

@Component(modules = [HeroModule::class])
interface HeroComponent {
    fun inject(heroSource: HeroSource)
}