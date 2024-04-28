package com.mitocode.ecoats.presentation.dish

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mitocode.ecoats.core.Result
import com.mitocode.ecoats.domain.repository.DishRepository
import com.mitocode.ecoats.presentation.util.ConnectivityChecker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DishViewModel @Inject constructor(val repository: DishRepository, val connectivityChecker: ConnectivityChecker)  : ViewModel() {

    var state by mutableStateOf(DishState())
        private set

    fun fetchData(idUser:Int){
        if(connectivityChecker.isConnectedToInternet()){
            getDishesRemote(idUser)
        }else{
            getDishesLocally(idUser)
        }
    }

    fun getDishesRemote(idUser:Int)
    {
        viewModelScope.launch {
            repository.getDishesRemote(idUser).onEach {
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

    private fun getDishesLocally(idUser:Int) {
        viewModelScope.launch {
            repository.getDishesLocally(idUser).onEach {
                when(it)
                {
                    is Result.Error -> {
                        state = state.copy(isLoading = false , error = it.message)
                    }
                    is Result.Loading -> {
                    }
                    is Result.Success -> {
                        state = state.copy(isLoading = false, successfull = it.data)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    fun updateFavoriteDish(idUser:Int, idDish : Int, favorite:Boolean)
    {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                repository.updateFavoriteDish(idUser,idDish,favorite)
            }
            state = state.copy(isLoading = false)
            getDishesLocally(idUser)
        }
    }
}