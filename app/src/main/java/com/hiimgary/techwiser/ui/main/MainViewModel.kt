package com.hiimgary.techwiser.ui.main

import androidx.lifecycle.*
import com.hiimgary.techwiser.model.Techy
import com.hiimgary.techwiser.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state: MutableLiveData<Techy> = MutableLiveData()

    val state: LiveData<Techy> get() = _state

    fun setStateEvent(mainStateEvent: MainStateEvent) {
        viewModelScope.launch {
            when(mainStateEvent) {
                is MainStateEvent.GetTechyQuote -> {
                    mainRepository.getTechyQuote().onEach { s ->
                        _state.value = s
                    }.launchIn(viewModelScope)
                }
            }
        }
    }

    fun markAsFavorite(techy: Techy) {
        viewModelScope.launch {
            mainRepository.addToFavorite(techy);
        }
    }
}

sealed class MainStateEvent {
    object GetTechyQuote: MainStateEvent()
}