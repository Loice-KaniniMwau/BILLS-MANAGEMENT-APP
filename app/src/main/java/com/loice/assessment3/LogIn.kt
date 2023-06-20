package com.loice.assessment3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.loice.assessment3.databinding.ActivityLogInBinding

class LogIn : AppCompatActivity() {
    lateinit var binding: ActivityLogInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        binding.btRegisterr.setOnClickListener {
            val intent=Intent(this,Registration::class.java)
            startActivity(intent)
        }
        binding.btBillz.setOnClickListener {
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        loginErrors()
        validateLogin()
    }




    fun loginErrors(){
        var name=binding.etUserName.text.toString()
        var password=binding.etPassword.text.toString()
        var passwordConf=binding.etPasswordConf.text.toString()
        var error=false
        if(name.isBlank()){
            binding.tilUserName.error="Username is required "
            error=true
        }

        if(password.isBlank()){
            binding.tilPassword.error="Password is required"
            error=true
        }

        if(passwordConf !=password){
            binding.tilPasswordConf.error="Password does not match"
            error=true
        }


    }
    fun validateLogin(){
        binding.tilUserName.editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.tilUserName.error = null
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
        binding.tilPassword.editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.tilPassword.error = null
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
        binding.tilPasswordConf.editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.tilPasswordConf.error = null
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })


    }

}