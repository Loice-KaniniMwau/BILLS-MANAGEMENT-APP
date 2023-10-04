package com.loice.assessment3.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginResponse(
   @Expose var message:String,
  @Expose @SerializedName("access_token") var access_token:String,
 @Expose @SerializedName("user_id")   var user_id:String
)



//data class RegisterResponse(
//var message:String,
//var user:User
//
//)

//{
//    "message": "login successful",
//    "access_token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJmcmVzaCI6ZmFsc2UsImlhdCI6MTY2MTE3NjA3MywianRpI
//    joiZjhjMTZjYWUtMmI2Mi00YTUzLThmMGQtZTQ3Nzg5NmVmYjFlIiwidHlwZSI6ImFjY2VzcyIsInN1YiI6IjAyY2NiNDBlLTc5ZGQtNGQ5YS05ZGYwLTBiY2MyZGU0YjVmMiIsIm5iZi
//    I6MTY2MTE3NjA3MywiZXhwIjoxNjYxMjYyNDczfQ.VrikfaSv_IJY1Z69cZf6NPKDOxFuC04vWCS9mCKn98M",
//    "user_id": "02ccb40e-79dd-4d9a-9df0-0bcc2de4b5f2"
//}