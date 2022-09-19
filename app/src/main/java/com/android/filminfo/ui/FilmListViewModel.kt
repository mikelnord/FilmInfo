package com.android.filminfo.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.filminfo.data.Repository
import com.android.filminfo.model.MoviesList
import com.android.filminfo.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilmListViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    lateinit var search: String //= "movie"
    private val _movieList = MutableLiveData<Resource<MoviesList>>()
    val movieList = _movieList

     fun launchMovies() {
        viewModelScope.launch {
            repository.getFilmForType(search).collect {
                _movieList.value = it
            }
        }
    }

}