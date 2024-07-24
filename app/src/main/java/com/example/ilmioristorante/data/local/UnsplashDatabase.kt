package com.example.ilmioristorante.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ilmioristorante.data.local.dao.UnsplashImageDao
import com.example.ilmioristorante.data.local.dao.UnsplashRemoteKeysDao
import com.example.ilmioristorante.data.local.dao.UserDao
import com.example.ilmioristorante.model.client.User
import com.example.ilmioristorante.model.unsplash.UnsplashImage
import com.example.ilmioristorante.model.unsplash.UnsplashRemoteKeys

@Database(entities = [UnsplashImage::class, UnsplashRemoteKeys::class, User::class], version = 1)
abstract class UnsplashDatabase: RoomDatabase() {

    abstract fun unsplashImageDao(): UnsplashImageDao
    abstract fun unsplashRemoteKeysDao(): UnsplashRemoteKeysDao
    abstract fun userDao(): UserDao
}