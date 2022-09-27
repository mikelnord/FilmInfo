package com.android.filminfo.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.filminfo.model.Movie

@Database(
    entities = [Movie::class, RemoteKeys::class],
    version = 1,
    exportSchema = false
)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun moviesDao(): MoviesDao
    abstract fun movieKeysDao(): MovieKeysDao

}