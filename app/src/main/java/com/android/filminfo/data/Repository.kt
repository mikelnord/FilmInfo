package com.android.filminfo.data

import com.android.filminfo.network.api.MovieService
import javax.inject.Inject

class Repository @Inject constructor(
    private val movieService: MovieService
) {

    suspend fun getMovie() = movieService.getMovie()

}