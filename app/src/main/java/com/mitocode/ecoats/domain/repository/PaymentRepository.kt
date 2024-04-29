package com.mitocode.ecoats.domain.repository

import androidx.room.ColumnInfo

interface PaymentRepository {

    suspend fun savePayment(total: Int, idUser:Int)

}