package com.example.peoplelist.data

import com.example.peoplelist.data.local.room.dao.PeopleDao
import com.example.peoplelist.data.local.room.model.RoomPerson
import com.example.peoplelist.data.local.room.model.RoomPersonLike
import com.example.peoplelist.data.local.room.model.RoomPersonWithLike
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

class PeopleLocalStore(private val dao: PeopleDao) {

    fun getAll(): Single<List<RoomPersonWithLike>> {
        return Single.fromCallable { dao.getAll() }
    }

    fun giveLike(personId: String): Completable {
        return Completable.fromAction { dao.giveLike(RoomPersonLike(personId)) }
    }

    fun insertAll(people: List<RoomPerson>): Completable {
        return Completable.fromAction { dao.insertAll(people) }
    }
}