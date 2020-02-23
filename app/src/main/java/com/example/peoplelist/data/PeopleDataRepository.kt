package com.example.peoplelist.data

import com.example.peoplelist.data.mapper.DataMapper
import com.example.peoplelist.domain.PeopleRepository
import com.example.peoplelist.domain.model.Person
import io.reactivex.Completable
import io.reactivex.Single

class PeopleDataRepository(
    private val localStore: PeopleLocalStore,
    private val remoteStore: PeopleRemoteStore,
    private val mapper: DataMapper
) : PeopleRepository {

    override fun getAll(): Single<List<Person>> {
        return remoteStore.getAll()
            .map { response -> with(mapper) { response.people.toRoom() } }
            .flatMapCompletable { people -> localStore.insertAll(people) }
            .onErrorResumeNext { Completable.complete() }
            .andThen(localStore.getAll())
            .map { people -> with(mapper) { people.toDomain() } }
    }

    override fun giveLike(personId: String): Completable {
        return localStore.giveLike(personId)
    }
}