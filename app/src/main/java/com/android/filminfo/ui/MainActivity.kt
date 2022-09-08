package com.android.filminfo.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.android.filminfo.databinding.ActivityMainBinding
import com.android.filminfo.util.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupObservers()
    }



    private fun setupObservers() {
        viewModel.getMovie().observe(this) {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { moveList ->
                            if (moveList.docs.isNotEmpty()) {
                                binding.lableText.text = moveList.docs[0].name
                                binding.textView.text = "total: ${moveList.total}"
                                binding.textView2.text = "page: ${moveList.page}"
                                binding.textView3.text = "pages: ${moveList.pages}"
                            }
                        }
                    }
                    Status.ERROR -> {
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        binding.lableText.text = "LOADING"
                    }
                }
            }
        }
    }
}