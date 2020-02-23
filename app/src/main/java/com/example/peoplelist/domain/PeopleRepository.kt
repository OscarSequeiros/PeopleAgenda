package com.example.peoplelist.domain

import com.example.peoplelist.domain.model.Person
import io.reactivex.Completable
import io.reactivex.Single

interface PeopleRepository {

    fun getAll(): Single<List<Person>>

    fun giveLike(personId: String): Completable
}