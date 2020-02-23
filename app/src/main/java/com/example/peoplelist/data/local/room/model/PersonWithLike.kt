package com.example.peoplelist.data.local.room.model

import androidx.room.Embedded

data class RoomPersonWithLike(
    @Embedded val person: RoomPerson,
    @Embedded val personLike: RoomPersonLike?
)