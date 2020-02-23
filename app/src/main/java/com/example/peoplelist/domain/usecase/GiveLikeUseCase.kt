package com.example.peoplelist.domain.usecase

import com.example.peoplelist.domain.PeopleRepository

class GiveLikeUseCase(private val repository: PeopleRepository) {

    fun execute(personId: String) = repository.giveLike(personId)
}