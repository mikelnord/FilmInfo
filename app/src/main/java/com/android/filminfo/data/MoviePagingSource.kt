package com.android.filminfo.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.android.filminfo.model.Movie
import com.android.filminfo.network.api.MovieService

class MoviePagingSource(
    private val movieService: MovieService,
    private val query: String
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val nextPage = params.key ?: 1
            val response = movieService.getFilmForType(query,"type","2022","year",params.loadSize,nextPage)

            LoadResult.Page(
                data = response.docs,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (nextPage == response.pages) null else nextPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
//        return state.anchorPosition?.let { anchorPosition ->
//            state.closestPageToPosition(anchorPosition)?.prevKey
//        }
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1) ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}