package com.mitocode.ecoats.data.repository

import com.mitocode.ecoats.core.Result
import com.mitocode.ecoats.data.networking.Api
import com.mitocode.ecoats.data.model.LoginRequest
import com.mitocode.ecoats.domain.model.User
import com.mitocode.ecoats.domain.model.toUser
import com.mitocode.ecoats.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class LoginRepositoryImp : LoginRepository {
    override suspend fun signIn(email: String, password: String) : Flow<Result<User>> = flow {

        try{

            emit(Result.Loading())

            val response = Api.build().logIn(
                LoginRequest(
                    email = email,
                    password = password
                )
            )

            if(response.isSuccessful)
            {

                val loginResponse = response.body()

                if(loginResponse?.success == true)
                {
                    emit(Result.Success(data = loginResponse.data.toUser()))
                }
                else
                {
                    emit(Result.Error(message = loginResponse?.message))
                }
            }
            else
            {
                emit(Result.Error(message = response.message()))
            }
        }
        catch (ex:IOException)
        {
            emit(Result.Error(message = "Compruebe su conexión al internet"))
        }
        catch (ex:Exception)
        {
            emit(Result.Error(message = ex.message))
        }
    }
}