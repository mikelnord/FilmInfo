package com.android.filminfo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.android.filminfo.adapter.FilmListAdapter
import com.android.filminfo.databinding.FragmentResultBinding
import com.android.filminfo.util.Status
import dagger.hilt.android.AndroidEntryPoint

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

        val adapter = FilmListAdapter()
        binding.recyclerResult.adapter = adapter
        subscribeUi(adapter)

        return binding.root
    }

    private fun subscribeUi(adapter: FilmListAdapter) {
        viewModel.movieList.observe(viewLifecycleOwner) {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { moveList ->
                            if (moveList.docs.isNotEmpty()) {
                                adapter.submitList(moveList.docs)
                            }
                        }
                        binding.loading.visibility = View.GONE
                    }
                    Status.ERROR -> {
                        Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                        binding.loading.visibility = View.GONE
                    }
                    Status.LOADING -> {
                        binding.loading.visibility = View.VISIBLE
                    }
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}