package com.loice.assessment3.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.loice.assessment3.databinding.ActivitySplashBinding
import com.loice.assessment3.utils.Constants
class SplashActivity : AppCompatActivity() {
    lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        redirectUser()
    }
    override fun onResume() {
        super.onResume()
        binding.button.setOnClickListener {
            val intent=Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
    fun redirectUser(){
        val sharedPrefs=getSharedPreferences(Constants.PREFS,Context.MODE_PRIVATE)
        val userId=sharedPrefs.getString(Constants.user_id,Constants.empty_string)
        if (userId.isNullOrBlank()){
            startActivity(Intent(this,LogIn::class.java))
        }
        else{
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
}