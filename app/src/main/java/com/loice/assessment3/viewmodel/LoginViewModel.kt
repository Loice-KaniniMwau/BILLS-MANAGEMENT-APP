package com.loice.assessment3.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loice.assessment3.model.LoginRequest
import com.loice.assessment3.model.LoginResponse
import com.loice.assessment3.model.RegisterRequest
import com.loice.assessment3.model.RegisterResponse

import com.loice.assessment3.repository.LoginRepository
import com.loice.assessment3.repository.UserRepository

import kotlinx.coroutines.launch

class LoginViewModel :ViewModel(){
    val loginRepository=LoginRepository()
    val logliveData=MutableLiveData<LoginResponse>()
    val errLiveData=MutableLiveData<String>()

    fun loginUser(loginRequest: LoginRequest){
        viewModelScope.launch {
            val response=loginRepository.loginUser(loginRequest)
            if(response.isSuccessful){
                logliveData.postValue(response.body())
            }
            else{
                errLiveData.postValue(response.errorBody()?.string())
            }
        }
    }
}


