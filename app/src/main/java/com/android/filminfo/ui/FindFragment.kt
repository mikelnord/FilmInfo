package com.android.filminfo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.android.filminfo.databinding.FragmentFindBinding

class FindFragment : Fragment() {

    private var _binding: FragmentFindBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FilmListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFindBinding.inflate(inflater, container, false)
        val home = parentFragment as HomeFragment
        binding.buttonFind.setOnClickListener {
            viewModel.accept(UiAction.Search(query = binding.textFind.text.toString().trim(),true))
            home.setResult()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}