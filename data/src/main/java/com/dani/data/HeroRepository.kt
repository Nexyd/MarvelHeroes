package com.dani.data

import com.dani.data.listener.HeroListener
import com.dani.domain.entities.MarvelData
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class HeroRepository @Inject constructor(private val listener: HeroListener) {
    fun getHeroes(): Observable<MarvelData> = listener.getHeroes()
}