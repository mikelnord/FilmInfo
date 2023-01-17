package com.android.filminfo.ui

import androidx.lifecycle.*
import com.android.filminfo.data.Repository
import com.android.filminfo.model.Person
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: Repository
) : ViewModel() {

    val movieId: Long? = savedStateHandle.get<Long>(MOVIE_ID_SAVED_STATE_KEY)
    val movie = movieId.let {
        repository.getMovie(it.toString()).asLiveData()
    }
    val personList: LiveData<List<Person>> = movie.switchMap {
        liveData(Dispatchers.IO) {
            emit(repository.getPersonList(it.id.toString(), "movies.id").docs)
        }
    }

    companion object {
        private const val MOVIE_ID_SAVED_STATE_KEY = "movieId"
    }

}