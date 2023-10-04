package com.loice.assessment3.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
data class RegisterRequest(

   @Expose @SerializedName ("first_name")  var firstName:String,
  @Expose @SerializedName("last_name") var lastName:String,
  @Expose @SerializedName("email") var email:String,
 @Expose @SerializedName("phone_number")  var phoneNumber:String,
 @Expose @SerializedName("password")  var password:String



)
