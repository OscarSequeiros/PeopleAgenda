package com.example.peoplelist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.peoplelist.domain.usecase.GetPeopleUseCase
import com.example.peoplelist.domain.usecase.GiveLikeUseCase
import com.example.peoplelist.presentation.PeopleViewState.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PeopleViewModel(
    private val getPeopleUseCase: GetPeopleUseCase,
    private val giveLikeUseCase: GiveLikeUseCase,
    private val mapper: PresentationMapper
) : BaseViewModel() {

    private val liveData: MutableLiveData<PeopleViewState> = MutableLiveData()

    fun liveData(): LiveData<PeopleViewState> = liveData

    override fun onCleared() {
        super.onCleared()
        stop()
    }

    fun getAll() {
        liveData.postValue(LoadingViewState)
        launch {
            getPeopleUseCase.execute()
                .map { people -> with(mapper) { people.toUi() } }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { people -> liveData.postValue(PeopleRecoveredViewState(people)) },
                    { it.printStackTrace() }
                )
        }
    }

    fun giveLike(personId: String) {
        launch {
            giveLikeUseCase.execute(personId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { liveData.postValue(LikeGivenViewState(personId)) },
                    { it.printStackTrace() }
                )
        }
    }
}