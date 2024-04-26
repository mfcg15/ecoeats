package com.mitocode.ecoats.data.repository

import android.content.SharedPreferences
import android.util.Log
import com.mitocode.ecoats.core.Result
import com.mitocode.ecoats.data.database.dao.UserDao
import com.mitocode.ecoats.data.networking.model.LoginRequest
import com.mitocode.ecoats.data.networking.model.RegisterRequest
import com.mitocode.ecoats.data.networking.Api
import com.mitocode.ecoats.domain.model.ToEntityUser
import com.mitocode.ecoats.domain.model.User
import com.mitocode.ecoats.domain.model.toUser
import com.mitocode.ecoats.domain.repository.RegisterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class RegisterRepositoryImp @Inject constructor(val sharedPreferences: SharedPreferences,  val userDao: UserDao) : RegisterRepository {
    override suspend fun signup(
        name: String,
        lastname: String,
        email: String,
        password: String,
    ): Flow<Result<User>> = flow {

        try {

            emit(Result.Loading())

            val respUser = Api.build().logIn(LoginRequest(email = "jledesma2509@gmail.com", password = "123"))

            val token : String = respUser.body()?.data?.token!!

            sharedPreferences.edit().putString("KEY_TOKEN",token).apply()

            val response = Api.build().register(
                "Bearer $token",
                RegisterRequest(
                    name = name,
                    lastname = lastname,
                    email = email,
                    password = password
                )
            )

            if(response.isSuccessful)
            {

                val registerResponse = response.body()

                if(registerResponse?.success == true)
                {
                    val userEntity = response.body()?.data?.ToEntityUser()
                    if (userEntity != null) {
                        userDao.save(userEntity)
                    }
                    emit(Result.Success(data = registerResponse.data.toUser()))
                }
                else
                {
                    emit(Result.Error(message = registerResponse?.message))
                }
            }
            else
            {
                emit(Result.Error(message = response.message()))
            }

        }
        catch (ex: IOException)
        {
            emit(Result.Error(message = "Compruebe su conexión a internet"))
        }
        catch (ex:Exception)
        {
            emit(Result.Error(message = ex.message))
        }

    }
}