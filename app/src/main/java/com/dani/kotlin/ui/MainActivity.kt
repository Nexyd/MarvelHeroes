package com.dani.kotlin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dani.data.HeroRepository
import com.dani.domain.entities.HeroInfo
import com.dani.interactors.HeroInteractor
import com.dani.kotlin.R
import com.dani.kotlin.dagger.component.DaggerAppComponent
import com.dani.kotlin.framework.HeroSource
import com.dani.kotlin.ui.adapter.HeroAdapter
import com.dani.kotlin.ui.presenter.MainPresenter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private val heroList = ArrayList<HeroInfo>()
    private val presenter =
        DaggerAppComponent.factory()
        .create(this).getMainPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val heroAdapter = HeroAdapter(heroList)
        val recycler = findViewById<RecyclerView>(R.id.heroesList)

        recycler.apply {
            layoutManager = LinearLayoutManager(baseContext)
            adapter = heroAdapter
        }

        presenter.getHeroes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->

            heroList.addAll(list.data.results)
            runOnUiThread { heroAdapter.notifyDataSetChanged() }

        }, { error ->
            error.printStackTrace()
        })
    }
}