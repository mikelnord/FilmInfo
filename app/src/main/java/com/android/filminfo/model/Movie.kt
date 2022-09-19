package com.android.filminfo.model


data class Movie(
    val id: Int,
    val type: String?,
    val name: String?,
    val alternativeName:String?,
    val description: String?,
    val slogan: String?,
    val year: Int,
    val logo: Logo,
    val poster: Poster,
    val rating: Rating,
    val votes: Rating,
    val budget: Budget
)


data class MoviesList(
    val docs: List<Movie>,
    val total: String?,
    val page: String?,
    val pages: String?
)

data class Logo(
    val _id: String,
    val url: String?
)

data class Poster(
    val _id: String,
    val url: String?,
    val previewUrl: String?
)

data class Rating(
    val _id: String,
    val kp: String?,
    val imdb: String?,
    val filmCritics: String?,
    val russianFilmCritics: String?,
    val await: String?
)

data class Budget(
    val _id: String,
    val value: Long,
    val currency: String?
)
