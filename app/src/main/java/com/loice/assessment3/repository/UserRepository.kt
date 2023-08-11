package com.loice.assessment3.repository

import com.loice.assessment3.api.ApiClient
import com.loice.assessment3.api.ApiInterface
import com.loice.assessment3.model.RegisterRequest
import com.loice.assessment3.model.RegisterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class UserRepository {
    val apiClient=ApiClient.buildClient(ApiInterface::class.java)
    suspend fun registerUser(registerRequest: RegisterRequest):Response<RegisterResponse>{
        return withContext(Dispatchers.IO){
            apiClient.registerUser(registerRequest)
        }
    }

//    val apiClient2=ApiClient
}