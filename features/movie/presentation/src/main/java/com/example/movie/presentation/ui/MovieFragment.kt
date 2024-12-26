package com.example.movie.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.core.dependency.findDependencies
import com.example.core.uicomponent.base.BaseFragment
import com.example.movie.presentation.R
import com.example.movie.presentation.databinding.FragmentMovieBinding
import com.example.movie.presentation.di.DaggerMovieComponent
import com.squareup.picasso.Picasso
import dagger.Lazy
import kotlinx.coroutines.launch
import javax.inject.Inject

const val MOVIE_ID = "movieId"
class MovieFragment :
    BaseFragment<FragmentMovieBinding>(R.layout.fragment_movie) {
    override val binding by viewBinding(FragmentMovieBinding::bind)

    @Inject
    lateinit var viewModelFactory: Lazy<MovieViewModel.Companion.Factory>

    private val viewModel: MovieViewModel by viewModels {
        viewModelFactory.get()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerMovieComponent.factory().create(findDependencies()).injectFragment(this)
    }

    private var movieId :Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieId = arguments?.getString(MOVIE_ID, "0")?.toInt() ?: 0
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMovie(movieId)
        setObservers()
    }

    private fun setObservers() {
        viewLifecycleOwner.lifecycleScope.launch{
            lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED){
                viewModel.movieModel.collect { movie ->
                    with(binding) {
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