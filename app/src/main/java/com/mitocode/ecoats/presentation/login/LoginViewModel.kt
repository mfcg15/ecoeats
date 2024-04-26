package com.mitocode.ecoats.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mitocode.ecoats.core.Result
import com.mitocode.ecoats.data.repository.LoginRepositoryImp
import com.mitocode.ecoats.domain.repository.LoginRepository
import com.mitocode.ecoats.presentation.util.ConnectivityChecker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(val repository: LoginRepository,  val connectivityChecker: ConnectivityChecker) : ViewModel(){

    var state by mutableStateOf(LoginState())

    fun signIn(email:String,password:String)
    {
        viewModelScope.launch {
            repository.signIn(email,password).onEach {
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