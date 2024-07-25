package com.example.ilmioristorante.data.remote

import com.example.ilmioristorante.BuildConfig
import com.example.ilmioristorante.model.restaurant.SearchResults
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RestaurantApi {

    /** not using anymore but api support added for future use-case */
//    @Headers("Authorization: Client-ID ${BuildConfig.API_KEY}")
//    @GET("/photos")
//    suspend fun getAllImages(
//        @Query("page") page: Int,
//        @Query("per_page") perPage: Int
//    ): List<RestaurantModel>

//    @Headers("Authorization: Client-ID ${BuildConfig.API_KEY}")
    @GET("/search/photos")
    suspend fun searchImages(
        @Query("query") query: String,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int
    ): SearchResults
}