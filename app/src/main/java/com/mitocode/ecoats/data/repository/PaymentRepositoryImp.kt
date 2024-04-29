package com.mitocode.ecoats.data.repository

import com.mitocode.ecoats.data.database.dao.PaymentDao
import com.mitocode.ecoats.domain.model.Payment
import com.mitocode.ecoats.domain.model.ToEntityPayment
import com.mitocode.ecoats.domain.repository.PaymentRepository
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

class PaymentRepositoryImp @Inject constructor(val paymentDao: PaymentDao) : PaymentRepository {
    override suspend fun savePayment(total: Int, idUser: Int) {

        val fecha = Date()

        val dia: String = SimpleDateFormat("d/MM/yyyy").format(fecha)

        val hora :String = SimpleDateFormat("HH : mm").format(fecha)

        val codBoleta = "B0"+idUser+"A"+
                SimpleDateFormat("yyyy").format(fecha)+SimpleDateFormat("MM").format(fecha)+SimpleDateFormat("d").format(fecha)+"H"+
                SimpleDateFormat("HH").format(fecha)+SimpleDateFormat("mm").format(fecha)

        val paymentAux = Payment(codBoleta,dia,hora,total,idUser)
        val PaymentEntity = paymentAux.ToEntityPayment()
        paymentDao.savePayment(PaymentEntity)
    }
}