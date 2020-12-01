package com.dani.domain.entities

import java.io.Serializable

data class HeroInfo(
    val id: Int?,
    val name: String?,
    val modified: String?,
    val thumbnail: Thumbnail?,
    val resourceURI: String?,
    val comics:  CollectionData,
    val series:  CollectionData,
    val stories: CollectionData,
    val events:  CollectionData,
    val urls: List<UriInfo>
): Serializable