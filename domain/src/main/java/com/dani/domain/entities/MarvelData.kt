package com.dani.domain.entities

data class MarvelData(
    val code: Int?,
    val status: String?,
    val copyright: String?,
    val attributionText: String?,
    val attributionHTML: String?,
    val etag: String?,
    val data: MarvelHeroData
)
