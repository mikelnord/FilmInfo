package com.android.filminfo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.filminfo.R
import com.android.filminfo.databinding.ListItemFilmBinding
import com.android.filminfo.model.Movie
import com.android.filminfo.ui.HomeFragmentDirections
import com.android.filminfo.util.loadImage

class FilmListAdapter : PagingDataAdapter<Movie, FilmListAdapter.ViewHolder>(
    FilmListDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListItemFilmBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class ViewHolder(
        private val binding: ListItemFilmBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Movie) {
            with(binding) {
                val name = item.name ?: item.alternativeName
                mTitle.text = name ?: "NoName"
                if (item.rating?.kp != null) {
                    if (item.rating.kp != "0") {
                        mRaiting.text = item.rating.kp
                        mRaiting.visibility = View.VISIBLE
                    } else{
                        mRaiting.visibility = View.GONE
                    }
                }
                if (item.poster?.previewUrl != null) {
                    mPoster.loadImage(item.poster.previewUrl)
                } else {
                    mPoster.loadImage(R.drawable.ic_broken_image)
                }

            }

            binding.root.setOnClickListener {
                navigateToMovieDetail(item, binding.root)
            }
        }

        private fun navigateToMovieDetail(
            movie: Movie,
            view: View
        ) {
            val direction = HomeFragmentDirections.actionHomeFragmentToHomeDetailFragment(movie.id)
            view.findNavController().navigate(direction)
        }
    }

}

class FilmListDiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }

}
