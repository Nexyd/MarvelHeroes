package com.dani.domain.entities

data class MarvelHeroData(
    val offset: Int?,
    val limit: Int?,
    val total: Int?,
    val count: Int?,
    val results: List<HeroInfo>
)
