package com.mitocode.ecoats.data.repository

import com.mitocode.ecoats.core.Result
import com.mitocode.ecoats.data.model.LoginRequest
import com.mitocode.ecoats.data.model.RegisterRequest
import com.mitocode.ecoats.data.networking.Api
import com.mitocode.ecoats.domain.model.User
import com.mitocode.ecoats.domain.model.toUser
import com.mitocode.ecoats.domain.repository.RegisterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class RegisterRepositoryImp : RegisterRepository {
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
            emit(Result.Error(message = "Compruebe su conexión al internet"))
        }
        catch (ex:Exception)
        {
            emit(Result.Error(message = ex.message))
        }

    }
}