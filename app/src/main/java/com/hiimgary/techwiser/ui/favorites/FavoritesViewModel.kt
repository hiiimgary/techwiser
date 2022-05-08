package com.hiimgary.techwiser.ui.favorites

import androidx.lifecycle.*
import com.hiimgary.techwiser.model.Techy
import com.hiimgary.techwiser.repository.FavoritesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val favoritesRepository: FavoritesRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state: MutableLiveData<List<Techy>> = MutableLiveData()

    val state: LiveData<List<Techy>> get() = _state

    fun setStateEvent(favoritesEventState: FavoritesStateEvent) {
        viewModelScope.launch {
            when(favoritesEventState) {
                is FavoritesStateEvent.GetFavorites -> {
                    favoritesRepository.getFavorites().onEach { s ->
                        _state.value = s
                    }.launchIn(viewModelScope)
                }
            }
        }
    }
}

sealed class FavoritesStateEvent {
    object GetFavorites: FavoritesStateEvent()
}