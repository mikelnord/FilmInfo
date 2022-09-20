package com.android.filminfo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.android.filminfo.adapter.FilmListAdapter
import com.android.filminfo.databinding.FragmentResultBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ResultFindFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FilmListViewModel by activityViewModels()
    private val movieAdapter: FilmListAdapter by lazy { FilmListAdapter() }
    private var searchJob: Job? = null
    private val args: ResultFindFragmentArgs by navArgs()
    private var oldType = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        context ?: return binding.root
        binding.recyclerResult.adapter = movieAdapter
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        if (viewModel.queryString != oldType) {
            search(viewModel.queryString)
            binding.recyclerResult.scrollToPosition(0)
            oldType = viewModel.queryString
        }
    }

    private fun search(query: String) {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.launchMovies(query).collectLatest {
                movieAdapter.submitData(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}