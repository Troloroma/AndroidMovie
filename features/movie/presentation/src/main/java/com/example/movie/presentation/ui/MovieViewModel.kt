package com.example.movie.presentation.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.common.Entity
import com.example.movie.domain.MovieEntity
import com.example.movie.domain.MovieInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

class MovieViewModel(
    private val interactor: MovieInteractor
) : ViewModel() {

    private val _movieModel = MutableStateFlow<MovieEntity?>(null)
    val movieModel = _movieModel.asStateFlow()

    fun getMovie(movieId: Int){
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = interactor.getMovie(movieId)) {
                is Entity.Success -> {
                    _movieModel.value = response.data
                    Log.d("ViewModel", response.data.toString())
                }
                is Entity.Error -> {
                    _movieModel.value = null
                }
            }
        }

    }

    companion object {
        class Factory @Inject constructor(
            private val interactor: Provider<MovieInteractor>
        ) : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                require(modelClass == MovieViewModel::class.java)
                return MovieViewModel(interactor.get()) as T
            }
        }
    }
}
