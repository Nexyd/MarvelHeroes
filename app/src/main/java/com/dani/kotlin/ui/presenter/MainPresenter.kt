package com.dani.kotlin.ui.presenter

import com.dani.domain.entities.MarvelData
import com.dani.interactors.HeroInteractor
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class MainPresenter @Inject constructor(private val interactor: HeroInteractor) {
    fun getHeroes(): Observable<MarvelData> = interactor.getHeroes()
}