package com.example.popularmovies.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.core.dependency.findDependencies
import com.example.core.uicomponent.base.BaseFragment
import com.example.popularmovies.presentation.R
import com.example.popularmovies.presentation.databinding.FragmentPopularMoviesBinding
import com.example.popularmovies.presentation.di.DaggerPopularMoviesComponent
import javax.inject.Inject
import dagger.Lazy

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}