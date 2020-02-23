package com.example.peoplelist.presentation

import com.example.peoplelist.presentation.model.UiPerson

sealed class PeopleViewState {

    object LoadingViewState : PeopleViewState()

    class PeopleRecoveredViewState(val people: List<UiPerson>) : PeopleViewState()

    class LikeGivenViewState(val personId: String) : PeopleViewState()
}