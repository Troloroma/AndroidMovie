package com.example.popularmovies.presentation.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.core.dependency.findDependencies
import com.example.core.uicomponent.base.BaseFragment
import com.example.popularmovies.presentation.R
import com.example.popularmovies.presentation.databinding.FragmentPopularMoviesBinding
import com.example.popularmovies.presentation.di.DaggerPopularMoviesComponent
import com.example.popularmovies.presentation.ui.adapter.MoviesLoadStateAdapter
import com.example.popularmovies.presentation.ui.adapter.PopularMoviesAdapter
import javax.inject.Inject
import dagger.Lazy
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PopularMoviesFragment :
    BaseFragment<FragmentPopularMoviesBinding>(R.layout.fragment_popular_movies) {
    override val binding by viewBinding(FragmentPopularMoviesBinding::bind)

    @Inject
    lateinit var viewModelFactory: Lazy<PopularMoviesViewModel.Companion.Factory>

    private val viewModel: PopularMoviesViewModel by viewModels {
        viewModelFactory.get()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerPopularMoviesComponent.factory().create(findDependencies()).injectFragment(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val moviesAdapter = PopularMoviesAdapter { movieId ->
            Log.d("MovieClick", "Clicked movie ID: $movieId")
        }
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = moviesAdapter.withLoadStateFooter(
                footer = MoviesLoadStateAdapter { moviesAdapter.retry() }
            )
        }

        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.popularMoviesList.collectLatest { pagingData ->
                    moviesAdapter.submitData(pagingData)
                }
            }
        }

        moviesAdapter.addLoadStateListener { loadState ->
            binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading
            binding.recyclerView.isVisible = loadState.source.refresh !is LoadState.Loading
            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.source.refresh as? LoadState.Error
            errorState?.let {
                Log.e("PagingError", it.error.message.orEmpty())
            }
        }
    }
}