package com.example.peoplelist.data.mapper

import com.example.peoplelist.data.local.room.model.RoomPerson
import com.example.peoplelist.data.local.room.model.RoomPersonWithLike
import com.example.peoplelist.data.remote.RemotePerson
import com.example.peoplelist.domain.model.Gender
import com.example.peoplelist.domain.model.Person

class DataMapper {

    fun List<RoomPersonWithLike>.toDomain(): List<Person> {
        return map { it.toDomain() }
    }

    private fun RoomPersonWithLike.toDomain(): Person {
        return Person(
            id = person.id,
            name = person.name ?: "",
            email = person.email,
            urlPicture = person.urlPicture,
            gender = Gender.build(person.gender ?: ""),
            hasLike = personLike != null
        )
    }

    fun List<RemotePerson>.toRoom(): List<RoomPerson> {
        return map { it.toRoom() }
    }

    private fun RemotePerson.toRoom(): RoomPerson {
        return RoomPerson(
            id = login.uuid ?: "",
            name = "${name?.title ?: ""} ${name?.firstName ?: ""} ${name?.lastName ?: ""}",
            email = email,
            gender = gender,
            urlPicture = picture.url
        )
    }
}