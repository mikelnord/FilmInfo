package com.android.filminfo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import com.android.filminfo.adapter.FilmListAdapter
import com.android.filminfo.databinding.FragmentResultBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ResultFindFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FilmListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
   //     context ?: return binding.root
        setupUI()
        return binding.root
    }

    private fun setupUI() {
        val adapter = FilmListAdapter()
        val decoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        binding.recyclerResult.addItemDecoration(decoration)
        val header = LoadStateAdapter { adapter.retry() }
        binding.recyclerResult.adapter = adapter.withLoadStateHeaderAndFooter(
            header = header,
            footer = LoadStateAdapter { adapter.retry() }
        )
        lifecycleScope.launch {
            viewModel.pagingDataFlow.collectLatest {
                adapter.submitData(it)
            }
        }

        lifecycleScope.launch {
            adapter.loadStateFlow.collect { loadState ->
                header.loadState = loadState.mediator
                    ?.refresh
                    ?.takeIf { it is LoadState.Error && adapter.itemCount > 0 }
                    ?: loadState.prepend

                val isListEmpty =
                    loadState.refresh is LoadState.NotLoading && adapter.itemCount == 0
                if (_binding != null) {
                    binding.emptyList.isVisible = isListEmpty
                    binding.recyclerResult.isVisible =
                        loadState.source.refresh is LoadState.NotLoading || loadState.mediator?.refresh is LoadState.NotLoading
                    binding.loading.isVisible = loadState.mediator?.refresh is LoadState.Loading
                    binding.retryButton.isVisible =
                        loadState.mediator?.refresh is LoadState.Error && adapter.itemCount == 0
                }
                val errorState = loadState.source.append as? LoadState.Error
                    ?: loadState.source.prepend as? LoadState.Error
                    ?: loadState.append as? LoadState.Error
                    ?: loadState.prepend as? LoadState.Error
                errorState?.let {
                    Toast.makeText(
                        context,
                        "\uD83D\uDE28 Wooops ${it.error}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}