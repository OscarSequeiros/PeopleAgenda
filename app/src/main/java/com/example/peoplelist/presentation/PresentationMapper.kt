package com.example.peoplelist.presentation

import com.example.peoplelist.domain.model.Person
import com.example.peoplelist.presentation.model.UiPerson

class PresentationMapper {

    fun List<Person>.toUi(): List<UiPerson> {
        return map { it.toUi() }
    }

    private fun Person.toUi(): UiPerson {
        return UiPerson(
            id = id,
            name = name,
            email = email,
            gender = gender.value,
            urlPicture = urlPicture,
            hasLike = hasLike
        )
    }
}