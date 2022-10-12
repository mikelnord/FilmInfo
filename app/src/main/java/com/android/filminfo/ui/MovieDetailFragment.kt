package com.android.filminfo.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.android.filminfo.R
import com.android.filminfo.databinding.FragmentMovieDetailBinding
import com.android.filminfo.util.loadImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!


    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        (parentFragment as HomeDetailFragment).detailViewModel.movie.observe(viewLifecycleOwner) { it ->
            with(binding) {
                val name = it.name ?: it.alternativeName
                tvTitle.text = name ?: "NoName"
                it?.description.let {
                    tvDescription.text = it
                }
                it?.rating.let {
                    if (it?.kp != "0") {
                        tvRating.text = "Рейтинг Кинопоиск ${it?.kp.toString()}"
                        tvRating.visibility = View.VISIBLE
                    }
                }
                if (it.poster?.previewUrl != null) {
                    ivCover.loadImage(it.poster.previewUrl)
                } else {
                    ivCover.loadImage(R.drawable.ic_broken_image)
                }


            }
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}