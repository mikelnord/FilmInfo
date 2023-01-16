package com.android.filminfo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.filminfo.R
import com.android.filminfo.databinding.ListItemPersonBinding
import com.android.filminfo.model.Person
import com.android.filminfo.util.loadImage

class PersonListAdapter :
    ListAdapter<Person, PersonListAdapter.ViewHolder>(PersonListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListItemPersonBinding.inflate(
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
        private val binding: ListItemPersonBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Person) {
            with(binding) {
                val name = item.name ?: ""
                mTitle.text = name
                if (item.photo != null) {
                    mPoster.loadImage(item.photo)
                } else {
                    mPoster.loadImage(R.drawable.ic_broken_image)
                }

            }

        }

    }
}

class PersonListDiffCallback : DiffUtil.ItemCallback<Person>() {
    override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
        return oldItem == newItem
    }
}
