package com.example.popularmovies.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.arkivanov.mvikotlin.extensions.coroutines.states
import com.example.core.dependency.findDependencies
import com.example.core.navigation.NavigationFlow
import com.example.core.uicomponent.base.BaseFragment
import com.example.popularmovies.presentation.R
import com.example.popularmovies.presentation.databinding.FragmentPopularMoviesBinding
import com.example.popularmovies.presentation.di.DaggerPopularMoviesComponent
import com.example.popularmovies.presentation.ui.adapter.PopularMoviesAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class PopularMoviesFragment :
    BaseFragment<FragmentPopularMoviesBinding>(R.layout.fragment_popular_movies) {
    override val binding by viewBinding(FragmentPopularMoviesBinding::bind)

    @Inject
    lateinit var storeFactoryProvider: PopularMoviesStoreFactoryProvider

    private val popularMoviesStore by lazy {
        storeFactoryProvider.create().create()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerPopularMoviesComponent.factory().create(findDependencies()).injectFragment(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val moviesAdapter = PopularMoviesAdapter { movieId ->
            navigateTopLvl(NavigationFlow.MovieFlow(movieId.toString()))
        }
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = moviesAdapter
        }

        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                popularMoviesStore.states.collectLatest { state ->
                    moviesAdapter.submitData(state.movies)
                }
            }
        }

        moviesAdapter.addLoadStateListener { loadState ->
            val isLoading = loadState.refresh is LoadState.Loading
            binding.progressBar.isVisible = isLoading
            binding.recyclerView.isVisible = !isLoading
            val isError = loadState.refresh is LoadState.Error
            binding.errorTextView.isVisible = isError
            binding.reloadButton.isVisible = isError
        }

        binding.reloadButton.setOnClickListener {
            popularMoviesStore.accept(PopularMoviesStore.Intent.ReloadMovies)
        }
    }
}