package com.example.peoplelist.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.peoplelist.presentation.di.DependencyProvider

class PeopleViewModelFactory(private val context: Context) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == PeopleViewModel::class.java) {
            return DependencyProvider(context).peopleViewModel() as T
        } else {
            throw IllegalArgumentException("Unknown model class :$modelClass")
        }
    }

}