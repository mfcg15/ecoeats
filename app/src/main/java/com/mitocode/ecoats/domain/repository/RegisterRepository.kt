package com.mitocode.ecoats.domain.repository

import com.mitocode.ecoats.core.Result
import com.mitocode.ecoats.domain.model.User
import kotlinx.coroutines.flow.Flow

interface RegisterRepository {

    suspend fun signup(name:String,lastname:String, email:String, password:String) : Flow<Result<User>>

}