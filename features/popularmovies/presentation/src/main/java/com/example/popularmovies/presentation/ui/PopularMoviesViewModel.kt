package com.example.popularmovies.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.popularmovies.domain.PopularMoviesInteractor
import javax.inject.Inject
import javax.inject.Provider

class PopularMoviesViewModel(
    private val interactor: PopularMoviesInteractor
) : ViewModel(){



    companion object {
        class Factory @Inject constructor(
            private val interactor: Provider<PopularMoviesInteractor>
        ) : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                require(modelClass == PopularMoviesViewModel::class.java)
                return PopularMoviesViewModel(interactor.get()) as T
            }
        }
    }
}