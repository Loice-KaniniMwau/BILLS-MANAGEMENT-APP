package com.loice.assessment3.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loice.assessment3.model.RegisterRequest
import com.loice.assessment3.model.RegisterResponse
import com.loice.assessment3.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel:ViewModel() {
    val userRepository=UserRepository()
    val regLiveData=MutableLiveData<RegisterResponse>()
    val errLiveData=MutableLiveData<String>()

    fun registerUser(registerRequest: RegisterRequest){
        viewModelScope.launch {
            val response=userRepository.registerUser(registerRequest)
            if(response.isSuccessful){
                regLiveData.postValue(response.body())
            }
            else{
                errLiveData.postValue(response.errorBody()?.string())
            }
        }
    }
}