package com.mitocode.ecoats.presentation.favorite

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mitocode.ecoats.core.Result
import com.mitocode.ecoats.domain.repository.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel@Inject constructor(val repository: FavoriteRepository): ViewModel() {
    var state by mutableStateOf(FavoriteState())
        private set


    fun fetchData(idUser:Int){
        isEmptyFavorite(idUser)
        getFavorites()
    }
    fun isEmptyFavorite(idUser:Int)
    {
        viewModelScope.launch {
            repository.isEmptyFavorite(idUser).onEach {
                when(it)
                {
                    is Result.Error -> {
                        state = state.copy(isLoading = false , error = it.message)
                    }
                    is Result.Loading -> {
                        state = state.copy(isLoading = true)
                    }
                    is Result.Success -> {
                        state = state.copy(isLoading = false, isEmpty = it.data)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    fun getFavorites()
    {
        viewModelScope.launch {
            repository.getFavorites().onEach {
                when(it)
                {
                    is Result.Error -> {
                        state = state.copy(isLoading = false , error = it.message)
                    }
                    is Result.Loading -> {
                        state = state.copy(isLoading = true)
                    }
                    is Result.Success -> {
                        state = state.copy(isLoading = false, successfull = it.data)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}