package com.loice.assessment3.model

import com.google.gson.annotations.SerializedName
data class RegisterRequest(

    @SerializedName ("first_name")  var firstName:String,
   @SerializedName("last_name") var lastName:String,
   @SerializedName("email") var email:String,
  @SerializedName("phone_number")  var phoneNumber:String,
  @SerializedName("password")  var password:String



)
