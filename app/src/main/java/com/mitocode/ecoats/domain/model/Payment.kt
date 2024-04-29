package com.mitocode.ecoats.domain.model

import com.mitocode.ecoats.data.database.model.PaymentEntity

data class Payment(
    val id : String,
    val fecha: String,
    val hora: String,
    val total : Int,
    val idUser: Int
)

fun Payment.ToEntityPayment() : PaymentEntity {
    return PaymentEntity(
        id = this.id,
        fecha = this.fecha,
        hora = this.hora,
        total = this.total,
        idUser = this.idUser
    )
}

fun List<PaymentEntity>.ToEntityPaymentList() : List<Payment> = map {
    Payment(
        id = it.id,
        fecha = it.fecha,
        hora = it.hora,
        total = it.total,
        idUser = it.idUser
    )
}