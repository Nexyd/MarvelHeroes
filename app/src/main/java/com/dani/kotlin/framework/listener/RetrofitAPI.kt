package com.dani.kotlin.framework.listener

import com.dani.domain.entities.HeroInfo
import com.dani.domain.entities.MarvelData
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import java.sql.Timestamp

interface RetrofitAPI {
    @GET("v1/public/characters")
    fun loadHeroes(@Query("limit")  limit:  Int,
                   @Query("apikey") apiKey: String,
                   @Query("hash") hash: String,
                   @Query("ts") timestamp: String):
        Observable<MarvelData>
}