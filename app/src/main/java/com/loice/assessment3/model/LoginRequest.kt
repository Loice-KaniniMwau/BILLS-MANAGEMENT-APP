package com.loice.assessment3.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginRequest(
  @Expose  @SerializedName("email") var email:String,
   @Expose @SerializedName("password") var password:String
)




//
//{
//    "email": "kano87@gmail.com",
//    "password": "kano87"
//}
//data class RegisterRequest(
//
//    @SerializedName ("first_name")  var firstName:String,
//   @SerializedName("last_name") var lastName:String,
//   @SerializedName("email") var email:String,
//  @SerializedName("phone_number")  var phoneNumber:String,
//  @SerializedName("password")  var password:String
//
//
//
//)
