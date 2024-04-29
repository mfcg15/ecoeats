package com.mitocode.ecoats.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mitocode.ecoats.data.database.model.PaymentEntity

@Dao
interface PaymentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePayment(paymentEntity: PaymentEntity)

    @Query("select * from payment_table where idUser=:idUser")
    suspend fun getPayments(idUser :Int):List<PaymentEntity>

}