package com.loice.assessment3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.loice.assessment3.databinding.ActivityMainBinding
import com.loice.assessment3.databinding.ActivityRegistrationBinding

class Registration : AppCompatActivity() {
    lateinit var binding: ActivityRegistrationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRegistrationBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        super.onResume()
        binding.btLogin.setOnClickListener {
            val intent=Intent(this,LogIn::class.java)
            startActivity(intent)
        }
        registrationErrors()
        validateRegistration()


    }



     fun registrationErrors(){
            var name=binding.etUsername.text.toString()
            var email =binding.etEmail.text.toString()
            var phoneNumber=binding.etPhonenumber.text.toString()
            var error=false
            if(name.isBlank()){
                binding.tilUsername.error="Username is required "
                error=true
            }

            if(email.isBlank()){
                binding.tilEmail.error="Email is required"
                error=true
            }

            if(phoneNumber.isBlank()){
                binding.tilPhonenumber.error="Password is required"
                error=true
            }



        }
        fun validateRegistration(){
            binding.tilUsername.editText?.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    binding.tilUsername.error = null
                }

                override fun afterTextChanged(s: Editable?) {

                }
            })
            binding.tilEmail.editText?.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    binding.tilEmail.error = null
                }

                override fun afterTextChanged(s: Editable?) {

                }
            })
            binding.tilPhonenumber.editText?.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    binding.tilPhonenumber.error = null
                }

                override fun afterTextChanged(s: Editable?) {

                }
            })


        }


    }

