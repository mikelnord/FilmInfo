package com.android.filminfo.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.filminfo.model.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(repos: List<Movie>)

    @Query(
        "SELECT * FROM movies WHERE " +
                "type = :queryType AND year = :queryYear " +
                "ORDER BY keyid"
    )
    fun moviesByType(queryType: String, queryYear: String = "2022"): PagingSource<Int, Movie>

    @Query(
        "SELECT * FROM movies WHERE " +
                "name = :queryName " +
                "ORDER BY keyid"
    )
    fun moviesByName(queryName: String): PagingSource<Int, Movie>


    @Query("DELETE FROM movies")
    suspend fun clearRepos()

    @Query("SELECT * FROM movies WHERE id = :movieId")
    fun getMovie(movieId: String): Flow<Movie>
}