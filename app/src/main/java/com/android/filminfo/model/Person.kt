package com.android.filminfo.model

data class Person(
    val id: Int,
    val name: String?,
    val photo: String?
)

data class PersonList(
    val docs: List<Person>,
    val total: Int,
    val page: Int,
    val pages: Int
)