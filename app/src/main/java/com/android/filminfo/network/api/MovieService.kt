package com.android.filminfo.network.api

import com.android.filminfo.model.MoviesList
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("movie")
    suspend fun getFilmForType(
        @Query("search") query: String,
        @Query("field") field: String = "type",
        @Query("search") search2: String = "2022",
        @Query("field") field2: String = "year",
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): MoviesList

}