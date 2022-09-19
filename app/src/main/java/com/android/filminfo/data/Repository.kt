package com.android.filminfo.data

import com.android.filminfo.model.MoviesList
import com.android.filminfo.network.api.MovieService
import com.android.filminfo.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val movieService: MovieService
) {

    suspend fun getMovie() = movieService.getMovie()

    suspend fun getFilmForType(search: String): Flow<Resource<MoviesList>?> {
        return flow {
            emit(Resource.loading(data = null))
            val result = Resource.success(data = movieService.getFilmForType(search))
            emit(result)
        }.flowOn(Dispatchers.IO)
    }


}