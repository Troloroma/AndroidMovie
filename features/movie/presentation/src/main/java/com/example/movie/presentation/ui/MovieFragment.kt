package com.example.movie.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.arkivanov.mvikotlin.extensions.coroutines.states
import com.example.core.dependency.findDependencies
import com.example.core.uicomponent.base.BaseFragment
import com.example.movie.presentation.R
import com.example.movie.presentation.databinding.FragmentMovieBinding
import com.example.movie.presentation.di.DaggerMovieComponent
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import javax.inject.Inject

const val MOVIE_ID = "movieId"
class MovieFragment :
    BaseFragment<FragmentMovieBinding>(R.layout.fragment_movie) {
    override val binding by viewBinding(FragmentMovieBinding::bind)

    @Inject
    lateinit var storeFactoryProvider: MovieStoreFactoryProvider
    private var movieId :Int = 0

    private val movieStore by lazy {
        storeFactoryProvider.create().create(movieId)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerMovieComponent.factory().create(findDependencies()).injectFragment(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieId = arguments?.getString(MOVIE_ID, "0")?.toInt() ?: 0
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
    }

    private fun setObservers() {
        viewLifecycleOwner.lifecycleScope.launch{
            lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED){
                movieStore.states.collect { state ->
                    with(binding) {
                        progressBar.isVisible = state.isLoading
                        contentLayout.isVisible = !state.isLoading
                        if (state.error != null) {
                            errorTextView.visibility = View.VISIBLE
                            errorTextView.visibility = View.VISIBLE
                            errorTextView.text = state.error
                            reloadButton.visibility = View.VISIBLE

                            contentLayout.visibility = View.GONE
                        } else{
                            errorTextView.visibility = View.GONE
                            errorTextView.visibility = View.GONE
                            reloadButton.visibility = View.GONE
                            contentLayout.visibility = View.VISIBLE
                            with(state){
                                Picasso.get().load(movie?.backdropPath).into(posterImageView)
                                titleTextView.text = movie?.title ?: getString(R.string.unknown)
                                overviewTextView.text = movie?.overview ?: getString(R.string.unknown)
                                budgetTextView.text = movie?.budget?.toString() ?: getString(R.string.unknown)
                                revenueTextView.text = movie?.revenue?.toString() ?: getString(R.string.unknown)
                                releaseDateTextView.text = movie?.releaseDate ?: getString(R.string.unknown)
                                ratingTextView.text = movie?.voteAverage?.toString() ?: getString(R.string.unknown)
                                statusTextView.text = movie?.status ?: getString(R.string.unknown)
                                voteCountTextView.text = movie?.voteCount?.toString() ?: getString(R.string.unknown)
                            }
                        }
                    }
                }
            }
        }
        binding.reloadButton.setOnClickListener{
            movieStore.accept(MovieStore.Intent.Reload(movieId))
        }
    }
}