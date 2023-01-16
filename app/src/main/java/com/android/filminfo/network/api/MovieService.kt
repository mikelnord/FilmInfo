package com.android.filminfo.network.api

import com.android.filminfo.model.MoviesList
import com.android.filminfo.model.PersonList
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("movie")
    suspend fun getFilmForType(
        @Query("search") query: String,
        @Query("field") field: String,
        @Query("search") search2: String,
        @Query("field") field2: String,
        @Query("sortField") sortField: String,
        @Query("sortType") sortType: String,
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): MoviesList

    @GET("movie")
    suspend fun getFindByName(
        @Query("search") search: String,
        @Query("field") field: String,
        @Query("isStrict") isStrict: String,
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): MoviesList

    @GET("person")
    suspend fun getPersonByFilm(
        @Query("search") query: String,
        @Query("field") field: String
    ): PersonList
}