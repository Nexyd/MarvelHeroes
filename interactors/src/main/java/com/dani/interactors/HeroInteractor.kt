package com.dani.interactors

import com.dani.data.HeroRepository
import com.dani.domain.entities.MarvelData
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class HeroInteractor @Inject constructor(private val repository: HeroRepository) {
    fun getHeroes(): Observable<MarvelData> = repository.getHeroes()
}