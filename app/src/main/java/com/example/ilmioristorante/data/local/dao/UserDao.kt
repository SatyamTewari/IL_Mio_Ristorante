package com.example.ilmioristorante.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ilmioristorante.model.client.User

@Dao
interface UserDao {

    @Query("SELECT * FROM USER_TABLE WHERE email=:email AND password=:password")
    suspend fun getUserDetails(email: String, password: String): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(user: User): Long
}