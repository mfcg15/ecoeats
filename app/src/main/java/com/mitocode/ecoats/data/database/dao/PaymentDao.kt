package com.mitocode.ecoats.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.mitocode.ecoats.data.database.model.PaymentEntity

@Dao
interface PaymentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePayment(paymentEntity: PaymentEntity)

}