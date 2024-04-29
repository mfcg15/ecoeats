package com.mitocode.ecoats.presentation.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mitocode.ecoats.core.Result
import com.mitocode.ecoats.domain.repository.CartRepository
import com.mitocode.ecoats.domain.repository.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DishDetailViewModel@Inject constructor(val repository: CartRepository): ViewModel() {
    var state by mutableStateOf(DishDetailState())
        private set

    fun isDishCart(idUser:Int, idDish : Int)
    {
        viewModelScope.launch {
            repository.isDishCart(idUser,idDish).onEach {
                when(it)
                {
                    is Result.Error -> {
                        state = state.copy(isLoading = false , error = it.message)
                    }
                    is Result.Loading -> {
                        state = state.copy(isLoading = true)
                    }
                    is Result.Success -> {
                        state = state.copy(isLoading = false, isBuy = it.data)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    fun saveDishCart(idUser:Int, idDish : Int, dishName : String, dishImagen : String, cantidad:Int, precio:Int)
    {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                repository.saveDishCart(idUser,idDish,dishName, dishImagen, cantidad, precio)
            }
            state = state.copy(isLoading = false)
        }
        isDishCart(idUser,idDish)
    }
}