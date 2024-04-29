package com.mitocode.ecoats.domain.repository

import com.mitocode.ecoats.core.Result
import com.mitocode.ecoats.domain.model.Payment
import kotlinx.coroutines.flow.Flow

interface PaymentRepository {

    suspend fun savePayment(total: Int, idUser:Int)

    suspend fun getPaymentsUser(idUser:Int) : Flow<Result<List<Payment>>>

}