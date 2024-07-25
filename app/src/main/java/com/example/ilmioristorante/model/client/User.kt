package com.example.ilmioristorante.model.client

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ilmioristorante.data.util.Constants.USER_TABLE

@Entity(tableName = USER_TABLE)
data class User(
    @PrimaryKey(autoGenerate = false)
    val email: String,
    val password: String,
    val name: String,
    val lastName: String
)