package com.android.filminfo.ui

import androidx.lifecycle.*
import com.android.filminfo.data.Repository
import com.android.filminfo.model.Person
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: Repository
) : ViewModel() {

    val movieId: Long? = savedStateHandle.get<Long>(MOVIE_ID_SAVED_STATE_KEY)
    val movie = repository.getMovie(movieId.toString()).asLiveData()
    var personList = MutableLiveData<List<Person>>()

    fun getPersonList(){
        viewModelScope.launch(Dispatchers.IO) {
            personList.postValue(repository.getPersonList( movie.value?.id.toString(),"movies.id").docs)
        }
    }


    companion object {
        private const val MOVIE_ID_SAVED_STATE_KEY = "movieId"
    }

}