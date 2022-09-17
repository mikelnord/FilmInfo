package com.android.filminfo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.filminfo.databinding.ListItemFilmBinding
import com.android.filminfo.model.Movie

class FilmListAdapter : ListAdapter<Movie, FilmListAdapter.ViewHolder>(
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
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ListItemFilmBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Movie) {
            with(binding) {
                item.name?.let {
                    headerTextviewRecyclerItem.text = item.name
                }
                item.description?.let {
                    descriptionTextviewRecyclerItem.text = item.description
                }
            }
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
