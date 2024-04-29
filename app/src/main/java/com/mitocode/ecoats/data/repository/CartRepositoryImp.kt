package com.mitocode.ecoats.data.repository

import com.mitocode.ecoats.core.Result
import com.mitocode.ecoats.data.database.dao.CartDao
import com.mitocode.ecoats.domain.model.Cart
import com.mitocode.ecoats.domain.model.ToEntityCart
import com.mitocode.ecoats.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CartRepositoryImp @Inject constructor(val cartDao: CartDao) : CartRepository {
    override suspend fun isDishCart(idUser: Int, idDish:Int) : Flow<Result<Boolean>> = flow {

        try
        {
            emit(Result.Loading())


            emit(Result.Success(data = false))

            val isDC = cartDao.isInCart(idUser,idDish)

            if(isDC == 0)
            {
                emit(Result.Success(data = false))
            }
            else
            {
                emit(Result.Success(data = true))
            }

        }
        catch (ex:Exception)
        {
            emit(Result.Error(message = ex.message))
        }
    }

    override suspend fun saveDishCart(idUser:Int, idDish : Int, dishName : String,dishImagen : String, cantidad:Int, precio:Int) {
        val total = cantidad*precio
        val cartAux = Cart(0,idUser,idDish,dishName,dishImagen,cantidad,precio,total)
        val cartEntity = cartAux.ToEntityCart()
       cartDao.saveInCart(cartEntity)
    }
}