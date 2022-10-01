package com.android.filminfo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.android.filminfo.R
import com.android.filminfo.databinding.MovieDetailBinding
import com.android.filminfo.util.loadImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private var _binding: MovieDetailBinding? = null
    private val binding get() = _binding!!
    private val detailViewModel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MovieDetailBinding.inflate(inflater, container, false)
        detailViewModel.movie.observe(viewLifecycleOwner) { it ->
            with(binding) {
                val name = it.name ?: it.alternativeName
                tvTitle.text = name ?: "NoName"
                it?.description.let {
                    tvDescription.text=it
                }
                it?.rating.let {
                    tvRating.text= it?.kp.toString()
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