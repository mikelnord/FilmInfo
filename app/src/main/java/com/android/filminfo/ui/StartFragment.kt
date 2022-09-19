package com.android.filminfo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.android.filminfo.databinding.FragmentStartBinding

class StartFragment : Fragment() {
    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FilmListViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        setupUI()
        return binding.root
    }

    private fun setupUI() {
        val home = parentFragment as HomeFragment
        binding.buttonMovie.setOnClickListener {
            viewModel.search = "movie"
            viewModel.launchMovies()
            home.setResult()
        }
        binding.buttonCartoon.setOnClickListener {
            viewModel.search = "cartoon"
            viewModel.launchMovies()
            home.setResult()
        }
        binding.buttonTvseries.setOnClickListener {
            viewModel.search = "tv-series"
            viewModel.launchMovies()
            home.setResult()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}