package com.dani.kotlin.dagger.module

import com.dani.data.listener.HeroListener
import com.dani.kotlin.framework.HeroSource
import dagger.Binds
import dagger.Module

@Module
abstract class HeroModule {
    @Binds
    abstract fun getHeroListener(checker: HeroSource): HeroListener
}