package com.example.peoplelist.data

import com.example.peoplelist.data.remote.PeopleApi
import com.example.peoplelist.data.remote.model.RemoteResponse
import io.reactivex.Single

class PeopleRemoteStore(private val peopleApi: PeopleApi) {

    fun getAll(): Single<RemoteResponse> {
        return peopleApi.getAll()
    }
}