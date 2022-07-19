package com.example.bayrakquiz.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.bayrakquiz.Classes.BayraklarDao
import com.example.bayrakquiz.Classes.VeriTabaniYardimcisi
import com.example.bayrakquiz.R
import com.example.bayrakquiz.databinding.ActivityMainBinding
import com.example.bayrakquiz.databinding.ActivityResultBinding
import com.info.sqlitekullanimihazirveritabani.DatabaseCopyHelper
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        veriTabaniKopyala()

        binding.baslaButton.setOnClickListener {
            startActivity(Intent(this@MainActivity,QuizActivity::class.java))
        }






    }

    fun veriTabaniKopyala(){

        val copyHelper = DatabaseCopyHelper(applicationContext)

        try {
            copyHelper.createDataBase()
        }catch (e:Exception){
            e.printStackTrace()
        }

        try {
            copyHelper.openDataBase()
        }catch (e:Exception){
            e.printStackTrace()
        }

    }


}