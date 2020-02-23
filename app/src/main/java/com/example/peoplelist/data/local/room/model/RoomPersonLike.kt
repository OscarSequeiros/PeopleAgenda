package com.example.peoplelist.data.local.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Person_like")
data class RoomPersonLike(
    @PrimaryKey
    @ColumnInfo(name = "person_id")
    val personId: String
)