package com.android.filminfo.di

import android.content.Context
import androidx.room.Room
import com.android.filminfo.db.MovieKeysDao
import com.android.filminfo.db.MoviesDao
import com.android.filminfo.db.MoviesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideMoviesDatabase(@ApplicationContext appContext: Context): MoviesDatabase {
        return Room.databaseBuilder(
            appContext,
            MoviesDatabase::class.java,
            "Movies.db"
        ).build()
    }

    @Provides
    fun provideMovieDao(moviesDatabase: MoviesDatabase): MoviesDao {
        return moviesDatabase.moviesDao()
    }

    @Provides
    fun provideMovieKeysDao(moviesDatabase: MoviesDatabase): MovieKeysDao {
        return moviesDatabase.movieKeysDao()
    }

}