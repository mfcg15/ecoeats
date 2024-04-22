package com.mitocode.ecoats.data.networking

import com.mitocode.ecoats.data.model.LoginRequest
import com.mitocode.ecoats.data.model.LoginResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

object Api {

    private val builder : Retrofit.Builder = Retrofit.Builder()
        .baseUrl("http://betolix-001-site1.etempurl.com/")
        .addConverterFactory(GsonConverterFactory.create())

    interface HealthyMethods{

        @POST("api/securities/login")
        suspend fun logIn(@Body request: LoginRequest) : Response<LoginResponse>

    }

    fun build() : HealthyMethods{
        return builder.build().create(HealthyMethods::class.java)
    }

}