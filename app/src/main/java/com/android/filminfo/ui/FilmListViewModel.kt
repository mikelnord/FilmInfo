package com.android.filminfo.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.android.filminfo.data.Repository
import com.android.filminfo.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class FilmListViewModel @Inject constructor(
    private val repository: Repository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    var accept: (UiAction) -> Unit
    val pagingDataFlow: Flow<PagingData<Movie>>

    init {
        val initialQuery: String =
            DEFAULT_QUERY //savedStateHandle.get(LAST_SEARCH_QUERY) ?: DEFAULT_QUERY
        val actionStateFlow = MutableSharedFlow<UiAction>()
        val searches = actionStateFlow
            .filterIsInstance<UiAction.Search>()
            .distinctUntilChanged()
            .onStart { emit(UiAction.Search(query = initialQuery)) }

        accept = { action ->
            viewModelScope.launch { actionStateFlow.emit(action) }
        }
        pagingDataFlow = searches
            .flatMapLatest { searchFilmByType(queryString = it.query) }
            .cachedIn(viewModelScope)
    }


    private fun searchFilmByType(queryString: String): Flow<PagingData<Movie>> =
        repository.getSearchResultStream(queryString)

    override fun onCleared() {
//        savedStateHandle[LAST_SEARCH_QUERY] = state.value.query
//        savedStateHandle[LAST_QUERY_SCROLLED] = state.value.lastQueryScrolled
        super.onCleared()
    }

}

sealed class UiAction {
    data class Search(val query: String) : UiAction()
}

private const val LAST_SEARCH_QUERY: String = "last_search_query"
private const val LAST_QUERY_SCROLLED: String = "last_query_scrolled"
private const val DEFAULT_QUERY = "movie"