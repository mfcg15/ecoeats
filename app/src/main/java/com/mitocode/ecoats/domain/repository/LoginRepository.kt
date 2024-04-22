package com.mitocode.ecoats.domain.repository

import com.mitocode.ecoats.core.Result
import com.mitocode.ecoats.domain.model.User
import kotlinx.coroutines.flow.Flow

interface LoginRepository {

    suspend fun signIn(email:String, password:String) : Flow<Result<User>>

}