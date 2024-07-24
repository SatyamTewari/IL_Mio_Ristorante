package com.example.ilmioristorante.domain

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import com.example.ilmioristorante.model.client.User
import com.example.ilmioristorante.model.unsplash.UnsplashImage
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getAllImages(): Flow<PagingData<UnsplashImage>>
    fun searchImages(query: String): Flow<PagingData<UnsplashImage>>
    suspend fun getUserDetails(email: String, password: String): User?
    suspend fun addUserDetails(user: User): Long
}