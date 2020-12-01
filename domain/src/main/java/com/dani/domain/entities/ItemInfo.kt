package com.dani.domain.entities

import java.io.Serializable

data class ItemInfo(
    val resourceURI: String?,
    val name: String?,
    val type: String?
): Serializable