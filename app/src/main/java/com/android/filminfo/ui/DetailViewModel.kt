package com.android.filminfo.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.android.filminfo.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    repository: Repository
) : ViewModel() {

    val movieId: Long = savedStateHandle.get<Long>(MOVIE_ID_SAVED_STATE_KEY)!!
    val movie = repository.getMovie(movieId.toString()).asLiveData()


    companion object {
        private const val MOVIE_ID_SAVED_STATE_KEY = "movieId"
    }

}