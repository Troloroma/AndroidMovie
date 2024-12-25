package com.example.popularmovies.presentation.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.popularmovies.presentation.databinding.MovieItemBinding
import com.example.popularmovies.presentation.ui.MovieModel
import com.squareup.picasso.Picasso

class PopularMoviesAdapter(
    private val onItemClick: (Int) -> Unit
) : PagingDataAdapter<MovieModel, PopularMoviesAdapter.ViewHolder>(
    DataDifferentiator
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        onItemClick
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class ViewHolder(
        private val binding: MovieItemBinding,
        private val onItemClick: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movieModel: MovieModel) = with(binding) {
            title.text = movieModel.title
            Picasso.get().load(movieModel.posterPath).into(poster);
            description.text = movieModel.overview
            rating.text = movieModel.voteAverage.toString()

            root.setOnClickListener {
                movieModel.id?.let { id -> onItemClick(id) }
            }
        }
    }

    object DataDifferentiator : DiffUtil.ItemCallback<MovieModel>() {
        override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
            return oldItem == newItem
        }
    }
}