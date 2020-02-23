package com.example.peoplelist.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.peoplelist.data.local.room.model.RoomPerson
import com.example.peoplelist.data.local.room.model.RoomPersonLike
import com.example.peoplelist.data.local.room.model.RoomPersonWithLike
import retrofit2.http.DELETE

@Dao
interface PeopleDao {

    @Query("SELECT Person.*, Person_like.* FROM Person LEFT JOIN Person_like ON Person.id = Person_like.person_id")
    fun getAll(): List<RoomPersonWithLike>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(people: List<RoomPerson>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun giveLike(personWithLike: RoomPersonLike)


}