package com.mitocode.ecoats.presentation.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mitocode.ecoats.core.Result
import com.mitocode.ecoats.domain.repository.RegisterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(val repository: RegisterRepository) : ViewModel() {

    var state by mutableStateOf(RegisterState())

    fun signup(name:String,lastname:String,email:String,password:String)
    {

        viewModelScope.launch {

            repository.signup(name,lastname,email,password).onEach {
                when(it)
                {
                    is Result.Error -> {
                        state = state.copy(error = it.message, isLoading = false, successfull = null)
                    }

                    is Result.Loading -> {
                        state = state.copy(isLoading = true,successfull = null, error = null)
                    }

                    is Result.Success -> {
                        state = state.copy(successfull = it.data, isLoading = false, error = null)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}