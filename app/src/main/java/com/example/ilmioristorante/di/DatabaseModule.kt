package com.example.ilmioristorante.di

import android.content.Context
import androidx.room.Room
import com.example.ilmioristorante.data.local.RestaurantDatabase
import com.example.ilmioristorante.data.util.Constants.RESTAURANT_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): RestaurantDatabase {
        return Room.databaseBuilder(
            context,
            RestaurantDatabase::class.java,
            RESTAURANT_DATABASE
        ).build()
    }
}