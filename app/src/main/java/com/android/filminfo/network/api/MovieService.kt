package com.android.filminfo.network.api

import com.android.filminfo.model.MoviesList
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("/movie")
    suspend fun getMovie(
        @Query("search") search: String = "2020",
        @Query("field") field: String = "year"
    ): MoviesList

}