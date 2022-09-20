package com.android.filminfo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
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
            viewModel.queryString = "movie"
            home.setResult()
        }
        binding.buttonCartoon.setOnClickListener {
            viewModel.queryString = "cartoon"
            home.setResult()
        }
        binding.buttonTvseries.setOnClickListener {
            viewModel.queryString = "tv-series"
            home.setResult()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}