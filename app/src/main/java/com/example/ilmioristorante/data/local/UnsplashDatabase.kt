package com.example.ilmioristorante.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ilmioristorante.data.local.dao.UnsplashImageDao
import com.example.ilmioristorante.data.local.dao.UnsplashRemoteKeysDao
import com.example.ilmioristorante.model.UnsplashImage
import com.example.ilmioristorante.model.UnsplashRemoteKeys

@Database(entities = [UnsplashImage::class, UnsplashRemoteKeys::class], version = 1)
abstract class UnsplashDatabase: RoomDatabase() {

    abstract fun unsplashImageDao(): UnsplashImageDao
    abstract fun unsplashRemoteKeysDao(): UnsplashRemoteKeysDao
}