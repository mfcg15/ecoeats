package com.mitocode.ecoats.presentation.payments

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mitocode.ecoats.domain.repository.PaymentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(val repository: PaymentRepository): ViewModel() {
    var state by mutableStateOf(PaymentState())
        private set

    fun savePayment(total: Int, idUser:Int) {

        viewModelScope.launch {
            withContext(Dispatchers.IO){
                repository.savePayment(total,idUser)
            }
            state = state.copy(isLoading = false)
        }
    }
}