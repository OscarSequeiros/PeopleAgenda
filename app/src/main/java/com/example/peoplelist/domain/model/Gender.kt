package com.example.peoplelist.domain.model

sealed class Gender {

    open val value : String = ""

    object Male : Gender() {
        override val value = MALE
    }

    object Female : Gender() {
        override val value = FEMALE
    }

    object Undefined : Gender()

    companion object {

        private const val MALE = "male"
        private const val FEMALE = "female"

        fun build(gender: String) =
            when (gender) {
                MALE -> Male
                FEMALE -> Female
                else -> Undefined
            }

    }
}