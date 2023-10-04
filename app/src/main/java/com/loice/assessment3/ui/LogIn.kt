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
import com.loice.assessment3.model.Bill
import com.loice.assessment3.model.LoginRequest
import com.loice.assessment3.model.LoginResponse
import com.loice.assessment3.utils.Constants
import com.loice.assessment3.viewmodel.BillsViewModel
import com.loice.assessment3.viewmodel.LoginViewModel

class LogIn : AppCompatActivity() {
    lateinit var binding: ActivityLogInBinding
    val userViewModel: LoginViewModel by viewModels()
    val billsViewModel:BillsViewModel by viewModels()

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


        userViewModel.errLiveData.observe(this, Observer {
                err->Toast.makeText(this,err,Toast.LENGTH_LONG).show()
            binding.pbprogressBarr.visibility=View.GONE
        })
        userViewModel.logliveData.observe(this, Observer {loginResponse->
            persistLogin(loginResponse)
            billsViewModel.fetchRemoteData()
            binding.pbprogressBarr.visibility=View.GONE
            Toast.makeText(this,loginResponse.message,Toast.LENGTH_LONG).show()
            startActivity(Intent(this, HomeActivity::class.java))
            finish()

        })
        binding.btSignUpp.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }



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




}