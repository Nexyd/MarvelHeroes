package com.dani.domain.entities

import java.io.Serializable

data class CollectionData(
    val available: Int?,
    val collectionURI: String?,
    val items: List<ItemInfo>,
    val returned: Int?
): Serializable
