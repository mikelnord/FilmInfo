package com.android.filminfo.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey(autoGenerate = true) val keyid: Long,
    val id: Long,
    val type: String?,
    val name: String?,
    val alternativeName: String?,
    val description: String?,
    val slogan: String?,
    val year: Int,
    @Embedded(prefix = "logo_")
    val logo: Logo?,
    @Embedded(prefix = "poster_")
    val poster: Poster?,
    @Embedded(prefix = "rating_")
    val rating: Rating?,
    @Embedded(prefix = "budget_")
    val budget: Budget?,
    @Embedded(prefix = "gen_")
    val genres: Genres?
)

data class Genres(
    val name:String?
)

data class MoviesList(
    val docs: List<Movie>,
    val total: Int,
    val page: Int,
    val pages: Int
)

data class Logo(
    val _id: String?,
    val url: String?
)

data class Poster(
    val _id: String?,
    val url: String?,
    val previewUrl: String?
)

data class Rating(
    val _id: String?,
    val kp: String?,
    val imdb: String?,
    val filmCritics: String?,
    val russianFilmCritics: String?,
    val await: String?
)

data class Budget(
    val id: String?,
    val value: Long?,
    val currency: String?
)
