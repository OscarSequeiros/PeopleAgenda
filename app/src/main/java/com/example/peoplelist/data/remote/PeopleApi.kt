package com.example.peoplelist.data.remote

import com.example.peoplelist.data.remote.model.RemoteResponse
import io.reactivex.Single
import retrofit2.http.GET

interface PeopleApi {

    @GET("api/?results=12")
    fun getAll(): Single<RemoteResponse>
}