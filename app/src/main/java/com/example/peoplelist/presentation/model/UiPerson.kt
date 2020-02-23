package com.example.peoplelist.presentation

data class UiPerson(
    val id: String,
    val name: String,
    val email: String?,
    val gender: String,
    val urlPicture: String?,
    val hasLike: Boolean
)