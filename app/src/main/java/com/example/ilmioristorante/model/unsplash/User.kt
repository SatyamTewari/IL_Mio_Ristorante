package com.example.ilmioristorante.model.unsplash

import androidx.room.Embedded
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("links")
    @Embedded
    val userLinks: UserLinks,
    @SerialName("name")
    val username: String
)