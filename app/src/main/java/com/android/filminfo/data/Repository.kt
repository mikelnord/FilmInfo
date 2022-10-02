package com.android.filminfo.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.android.filminfo.db.MoviesDatabase
import com.android.filminfo.model.Movie
import com.android.filminfo.network.api.MovieService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val movieService: MovieService,
    private val database: MoviesDatabase
) {

    fun getSearchResultStream(query: String, isFind: Boolean): Flow<PagingData<Movie>> {
        val pagingSourceFactory = when (isFind) {
            true -> {
                { database.moviesDao().moviesByName(query) }
            }
            false -> {
                { database.moviesDao().moviesByType(query) }
            }
        }

        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            remoteMediator = MovieRemoteMediator(
                query,
                movieService,
                database,
                isFind
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
            .flowOn(Dispatchers.IO)
    }

    fun getMovie(movieId: String) = database.moviesDao().getMovie(movieId)

    companion object {
        private const val NETWORK_PAGE_SIZE = 50
    }

}