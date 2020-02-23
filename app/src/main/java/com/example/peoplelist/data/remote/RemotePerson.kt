package com.example.peoplelist.data.remote

import com.google.gson.annotations.SerializedName

data class RemotePerson(
    @SerializedName("gender") val gender: String?,
    @SerializedName("name") val name: Name?,
    @SerializedName("email") val email: String?,
    @SerializedName("login") val login: Login,
    @SerializedName("picture") val picture: Picture
) {

    data class Name(
        @SerializedName("title") val title: String?,
        @SerializedName("first") val firstName: String?,
        @SerializedName("last") val lastName: String?
    )

    data class Login(
        @SerializedName("uuid") val uuid: String?
    )

    data class Picture(
        @SerializedName("thumbnail") val url: String?
    )
}