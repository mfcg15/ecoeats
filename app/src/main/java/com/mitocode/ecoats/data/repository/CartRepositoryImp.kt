package com.mitocode.ecoats.data.repository

import com.mitocode.ecoats.core.Result
import com.mitocode.ecoats.data.database.dao.CartDao
import com.mitocode.ecoats.domain.model.Cart
import com.mitocode.ecoats.domain.model.ToEntityCart
import com.mitocode.ecoats.domain.model.ToEntityCartList
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

    override suspend fun getCart(idUser:Int): Flow<Result<List<Cart>>> = flow {

        try
        {
            emit(Result.Loading())

            val cartEntity = cartDao.getCArtUser(idUser)

            if(cartEntity.isEmpty())
            {
                emit(Result.Error(message = "El carrito esta vacio"))
            }
            else
            {
                emit(Result.Success(data = cartEntity.ToEntityCartList()))
            }

        }
        catch (ex:Exception)
        {
            emit(Result.Error(message = ex.message))
        }
    }

    override suspend fun getTotalPayment(idUser: Int) : Flow<Result<Int>> = flow {

        try
        {
            emit(Result.Loading())

            val cartEntity = cartDao.getCArtUser(idUser)

            var totalPayment :Int= 0

            cartEntity.forEach {
                totalPayment += it.total
            }

            emit(Result.Success(data = totalPayment))
        }
        catch (ex:Exception)
        {
            emit(Result.Error(message = ex.message))
        }
    }

    override suspend fun updateDishCart(idDishCart:Int, cantidad : Int) {
        val precio = cartDao.getPrecio(idDishCart)
        val total = precio*cantidad
        cartDao.updateDishCart(cantidad,total,idDishCart)
    }

    override suspend fun updateCartPayment(idUser:Int) {
        val cartEntity = cartDao.getCArtUser(idUser)

        cartEntity.forEach {
            cartDao.updateDishPayment(it.id)
        }
    }

    override suspend fun DeleteDishCart(idDishCart:Int) {
        cartDao.deleteDishCart(idDishCart)
    }
}