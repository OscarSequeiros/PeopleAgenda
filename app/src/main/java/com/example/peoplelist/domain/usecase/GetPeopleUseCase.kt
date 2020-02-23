package com.example.peoplelist.domain.usecase

import com.example.peoplelist.domain.PeopleRepository
import com.example.peoplelist.domain.model.Person
import io.reactivex.Single

class GetPeopleUseCase(private val repository: PeopleRepository) {

    fun execute(): Single<List<Person>> = repository.getAll()
}