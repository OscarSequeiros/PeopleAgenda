package com.example.peoplelist.domain.model

data class Person(
    val id: String,
    val name: String,
    val email: String?,
    val gender: Gender,
    val urlPicture: String?,
    val hasLike: Boolean
)