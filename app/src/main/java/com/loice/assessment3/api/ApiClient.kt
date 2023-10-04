package com.loice.assessment3.api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


object ApiClient {
    val gson= GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()

    val retrofit=Retrofit.Builder()
        .baseUrl("http://13.37.106.218" )
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    fun<T> buildClient(apiInterface:Class<T>):T{
        return retrofit.create(apiInterface)
    }



}