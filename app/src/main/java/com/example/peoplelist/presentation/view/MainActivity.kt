package com.example.peoplelist.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.peoplelist.R
import com.example.peoplelist.presentation.PeopleViewModel
import com.example.peoplelist.presentation.PeopleViewModelFactory
import com.example.peoplelist.presentation.PeopleViewState
import com.example.peoplelist.presentation.PeopleViewState.*
import com.example.peoplelist.presentation.model.UiPerson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), PersonActionsCallback {

    private val adapter = PeopleAdapter(callback = this)

    private val viewModel: PeopleViewModel? by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProvider(this, PeopleViewModelFactory(this))
            .get(PeopleViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUp()
    }

    override fun onStart() {
        super.onStart()
        viewModel?.getAll()
    }

    private fun setUp() {
        setUpObserver()
        setUpFragment()
    }

    private fun setUpObserver() {
        viewModel?.liveData()?.observe(this, Observer<PeopleViewState> { handleState(it) })
    }

    private fun handleState(state: PeopleViewState) {
        when (state) {
            LoadingViewState            -> showLoading()
            is PeopleRecoveredViewState -> showPeople(state.people)
            is LikeGivenViewState       -> showLike(state.personId)
        }
    }

    private fun showLoading() {
        progressLoading.visibility = View.VISIBLE
    }

    private fun showPeople(people: List<UiPerson>) {
        hideLoading()
        adapter.updateItems(people)
    }

    private fun hideLoading() {
        progressLoading.visibility = View.GONE
    }

    private fun showLike(personId: String) {
        adapter.updateLike(personId)
        //viewModel?.getAll()
    }

    private fun setUpFragment() {
        recyclerItems.layoutManager = LinearLayoutManager(this)
        recyclerItems.adapter = adapter
    }

    override fun atGivenLike(personId: String) {
        viewModel?.giveLike(personId)
    }
}
