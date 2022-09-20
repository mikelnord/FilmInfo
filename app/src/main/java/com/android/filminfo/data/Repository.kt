package com.android.filminfo.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.android.filminfo.model.Movie
import com.android.filminfo.network.api.MovieService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val movieService: MovieService
) {

    suspend fun getMovie() = movieService.getMovie()

//    suspend fun getFilmForType(search: String): Flow<Resource<MoviesList>?> {
//        return flow {
//            emit(Resource.loading(data = null))
//            val result = Resource.success(data = movieService.getFilmForType(search))
//            emit(result)
//        }.flowOn(Dispatchers.IO)
//    }

    fun getSearchResultStream(query: String): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = { MoviePagingSource(movieService, query) }
        ).flow
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 10
    }

}