package com.android.filminfo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.android.filminfo.data.Repository
import com.android.filminfo.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilmListViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    lateinit var queryString: String

    fun launchMovies(query:String): Flow<PagingData<Movie>> {
        return repository.getSearchResultStream(query).cachedIn(viewModelScope)
    }
}