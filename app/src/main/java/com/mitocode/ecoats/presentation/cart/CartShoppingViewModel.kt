package com.mitocode.ecoats.presentation.cart

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mitocode.ecoats.core.Result
import com.mitocode.ecoats.domain.repository.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CartShoppingViewModel @Inject constructor(val repository: CartRepository): ViewModel() {
    var state by mutableStateOf(CartShoppingState())
        private set

    fun fetchData(idUser:Int){
        getCart(idUser)
        getTotalPayment(idUser)
    }
    fun getCart(idUser:Int) {
        viewModelScope.launch {
            repository.getCart(idUser).onEach {
                when(it)
                {
                    is Result.Error -> {
                        state = state.copy(isLoading = false , error = it.message)
                    }
                    is Result.Loading -> {
                        state = state.copy(isLoading = true)
                    }
                    is Result.Success -> {
                        state = state.copy(isLoading = false,  successfull = it.data )
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    fun getTotalPayment(idUser:Int) {
        viewModelScope.launch {
            repository.getTotalPayment(idUser).onEach {
                when(it)
                {
                    is Result.Error -> {
                        state = state.copy(isLoading = false , error = it.message)
                    }
                    is Result.Loading -> {
                        state = state.copy(isLoading = true)
                    }
                    is Result.Success -> {
                        state = state.copy(isLoading = false,  total = it.data )
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    fun getCantidadDishCart(idUser:Int) {
        viewModelScope.launch {
            repository.getCountCart(idUser).onEach {
                when(it)
                {
                    is Result.Error -> {
                        state = state.copy(isLoading = false , error = it.message)
                    }
                    is Result.Loading -> {
                        state = state.copy(isLoading = true)
                    }
                    is Result.Success -> {
                        state = state.copy(isLoading = true,  cantidad = it.data )
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    fun updateCantidadDishCart(idDishCart:Int,idUser:Int, cantidad: Int)
    {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                repository.updateDishCart(idDishCart,cantidad)
            }
            state = state.copy(isLoading = false)
            fetchData(idUser)
        }
    }

    fun updateIsPayment(idUser:Int)
    {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                repository.updateCartPayment(idUser)
            }
            state = state.copy(isLoading = false)
        }
    }

    fun DeleteDishCart(idDishCart:Int, idUser: Int) {

        viewModelScope.launch {
            withContext(Dispatchers.IO){
                repository.DeleteDishCart(idDishCart)
            }
            state = state.copy(isLoading = false)
            fetchData(idUser)
        }
    }
}

