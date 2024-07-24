package com.example.ilmioristorante.model

import kotlinx.serialization.Serializable

@Serializable
data class Urls(
//    @SerialName("regular") => use this in-case when variable is different from api key name
    val regular: String
)