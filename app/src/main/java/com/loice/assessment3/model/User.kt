package com.loice.assessment3.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(
 @Expose @SerializedName( "phone_number")  var phoneNumber:String,
 @Expose @SerializedName("first_name")  var firstName:String,
  @Expose @SerializedName("last_name") var lastName:String,
    var email:String,
 @Expose @SerializedName(  "user_id")  var userId:String
)
