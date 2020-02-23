package com.example.peoplelist.data.remote.model

import com.example.peoplelist.data.remote.RemotePerson
import com.google.gson.annotations.SerializedName

data class RemoteResponse(
    @SerializedName("results") val people: List<RemotePerson>
)