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
import com.loice.assessment3.databinding.ActivityMainBinding
import com.loice.assessment3.model.RegisterRequest
import com.loice.assessment3.utils.Constants
import com.loice.assessment3.viewmodel.UserViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val userViewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onResume() {
        super.onResume()
        binding.btSignUp.setOnClickListener {
            registrationErrors()
        }



        userViewModel.errLiveData.observe(this, Observer {
            err->Toast.makeText(this,err,Toast.LENGTH_LONG).show()
            binding.pbProgressBar.visibility=View.GONE
        })
        userViewModel.regLiveData.observe(this, Observer {regResponse->
            binding.pbProgressBar.visibility=View.GONE
            Toast.makeText(this,regResponse.message,Toast.LENGTH_LONG).show()
            startActivity(Intent(this, LogIn::class.java))

        })
        binding.btLoginn.setOnClickListener {
            val intent=Intent(this,LogIn::class.java)
            startActivity(intent)
            finish()
        }


    }

   private fun registrationErrors() {
        val password = binding.etPassword.text.toString()
        val firstname = binding.etFirstName.text.toString()
        val lastName = binding.etLastName.text.toString()
        val email = binding.etEmail.text.toString()
        val phoneNumber = binding.etPhoneNumber.text.toString()

        val passwordConf=binding.etPasswordConf.text.toString()

        var error = false
        if (firstname.isBlank()) {
            binding.tilFirstName.error = "Username_ is_ required "
            error = true
        }

        if (email.isBlank()) {
            binding.tilEmail.error = getString(R.string.email_is_required)
            error = true
        }
        if(phoneNumber.isBlank()){
            binding.tilPhoneNumber.error= getString(R.string.phone_number_is_required)
        }

        if (lastName.isBlank()) {
            binding.tilLastName.error = getString(R.string.last_name_is_required)
            error = true
        }
        if (password.isBlank()) {
            binding.tilLastName.error = "Password is required"
            error = true
        }
        if(password!=passwordConf){
            binding.tilPasswordConf.error= getString(R.string.password_does_not_match)
            error=true
        }

        if(!error){
            val registerRequest=RegisterRequest(
                firstName =firstname,
                email=email,
                lastName = lastName,
                password = password,
                phoneNumber = phoneNumber,




            )
            binding.pbProgressBar.visibility=View.VISIBLE
            userViewModel.registerUser(registerRequest)
        }




    }
    //launcher activity


}