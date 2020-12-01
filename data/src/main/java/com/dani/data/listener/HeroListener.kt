package com.dani.data.listener

import com.dani.domain.entities.MarvelData
import io.reactivex.rxjava3.core.Observable

interface HeroListener {
    fun getHeroes(): Observable<MarvelData>
}