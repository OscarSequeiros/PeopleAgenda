package com.example.peoplelist.presentation.di

import android.content.Context
import com.example.peoplelist.data.PeopleDataRepository
import com.example.peoplelist.data.PeopleLocalStore
import com.example.peoplelist.data.PeopleRemoteStore
import com.example.peoplelist.data.local.PeopleDB
import com.example.peoplelist.data.mapper.DataMapper
import com.example.peoplelist.data.remote.ApiClient
import com.example.peoplelist.domain.usecase.GetPeopleUseCase
import com.example.peoplelist.domain.usecase.GiveLikeUseCase
import com.example.peoplelist.presentation.PeopleViewModel
import com.example.peoplelist.presentation.PresentationMapper

class DependencyProvider(context: Context) {

    private val dataBase = PeopleDB.getInstance(context)

    private val peopleApi = ApiClient().initApi()

    private val localStore = PeopleLocalStore(dataBase.peopleDao())

    private val remoteStore = PeopleRemoteStore(peopleApi)

    private val dataMapper = DataMapper()

    private val peopleRepository = PeopleDataRepository(
        localStore = localStore,
        remoteStore = remoteStore,
        mapper = dataMapper
    )

    private val getPeopleUseCase = GetPeopleUseCase(
        repository = peopleRepository
    )

    private val giveLikeUseCase = GiveLikeUseCase(
        repository = peopleRepository
    )

    private val presentationMapper = PresentationMapper()

    fun peopleViewModel() = PeopleViewModel(
        getPeopleUseCase = getPeopleUseCase,
        giveLikeUseCase = giveLikeUseCase,
        mapper = presentationMapper
    )







}