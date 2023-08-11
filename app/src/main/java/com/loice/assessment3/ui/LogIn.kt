package com.loice.assessment3.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.loice.assessment3.R
import com.loice.assessment3.databinding.ActivityLogInBinding
import com.loice.assessment3.model.LoginRequest
import com.loice.assessment3.model.LoginResponse
import com.loice.assessment3.utils.Constants
import com.loice.assessment3.viewmodel.LoginViewModel

class LogIn : AppCompatActivity() {
    lateinit var binding: ActivityLogInBinding
    val userViewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()

        binding.btLogin.setOnClickListener {
            loginErrors()
        }
        binding.btSignUpp.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        userViewModel.errLiveData.observe(this, Observer {
                err->Toast.makeText(this,err,Toast.LENGTH_LONG).show()
            binding.pbprogressBarr.visibility=View.GONE
        })
        userViewModel.logliveData.observe(this, Observer {loginResponse->
            persistLogin(loginResponse)
            binding.pbprogressBarr.visibility=View.GONE
            Toast.makeText(this,loginResponse.message,Toast.LENGTH_LONG).show()
            startActivity(Intent(this, HomeActivity::class.java))
            finish()

        })



    }




   private fun loginErrors(){
        val email=binding.etEmaill.text.toString()
        val password=binding.tilPasswordd.text.toString()
//        var passwordConf=binding.etPasswordConf.text.toString()
        var error=false
        if(email.isBlank()){
            binding.tilEmaill.error= getString(R.string.username_is_required)
            error=true
        }

        if(password.isBlank()){
            binding.etPassword.error= getString(R.string.password_is_required)
            error=true
        }
        if(!error){
            val loginRequest=LoginRequest(
                email=email,
                password=password,
            )
            binding.pbprogressBarr.visibility=View.VISIBLE
            userViewModel.loginUser(loginRequest)



        }



    }

    fun persistLogin(loginResponse: LoginResponse){
        val sharedPrefs=getSharedPreferences(Constants.PREFS,Context.MODE_PRIVATE)
        val editor=sharedPrefs.edit()
        editor.putString(Constants.user_id,loginResponse.user_id)
        editor.putString(Constants.access_token,loginResponse.access_token)
        editor.apply()

    }
//package com.loice.assessment3.ui
//
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.view.View
//import android.widget.Toast
//import androidx.activity.viewModels
//import androidx.lifecycle.Observer
//import com.loice.assessment3.databinding.ActivityMainBinding
//import com.loice.assessment3.model.RegisterRequest
//import com.loice.assessment3.viewmodel.UserViewModel
//
//class MainActivity : AppCompatActivity() {
//    lateinit var binding: ActivityMainBinding
//    val userViewModel: UserViewModel by viewModels()
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//    }
//
//    override fun onResume() {
//        super.onResume()
//
//        userViewModel.errLiveData.observe(this, Observer {
//            err->Toast.makeText(this,err,Toast.LENGTH_LONG).show()
//            binding.pbProgressBar.visibility=View.GONE
//        })
//        userViewModel.regLiveData.observe(this, Observer {regResponse->
//            Toast.makeText(this,regResponse.message,Toast.LENGTH_LONG).show()
//            startActivity(Intent(this, LogIn::class.java))
//            binding.pbProgressBar.visibility=View.GONE
//        })
//        binding.btSignUp.setOnClickListener {
//            val intent = Intent(this, LogIn::class.java)
//            startActivity(intent)
//            registrationErrors()
//
//
//        }
//
//
//    }
//
//    fun registrationErrors() {
//        val firstname = binding.etFirstName.text.toString()
//        val lastName = binding.etLastName.text.toString()
//        val email = binding.etEmail.text.toString()
//        val phoneNumber = binding.etPhoneNumber.text.toString()
//
//        var password = binding.etPassword.toString()
//
//        var error = false
//        if (firstname.isBlank()) {
//            binding.tilFirstName.error = "Username is required "
//            error = true
//        }
//
//        if (email.isBlank()) {
//            binding.tilEmail.error = "Email is required"
//            error = true
//        }
//
//        if (lastName.isBlank()) {
//            binding.tilLastName.error = "last name is required"
//            error = true
//        }
//        if (password.isBlank()) {
//            binding.tilLastName.error = "Password is required"
//            error = true
//        }
//
//        if(!error){
//            val registerRequest=RegisterRequest(
//                firstName =firstname,
//                email=email,
//                lastName = lastName,
//                password = password,
//                phoneNumber = phoneNumber,
//
//
//            )
//            binding.pbProgressBar.visibility=View.VISIBLE
//            userViewModel.registerUser(registerRequest)
//        }
//
//
//
//
//    }
//
//}



}