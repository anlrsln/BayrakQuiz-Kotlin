package com.example.bayrakquiz.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bayrakquiz.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var dogruSayac = intent.getIntExtra("dogruSayac",0)
        dogruSayac+=1
        var basariOrani:Int = (dogruSayac*100)/5

        binding.buttonTekrarDene.setOnClickListener {
            startActivity(Intent(this@ResultActivity,QuizActivity::class.java))
            finish()
        }

        binding.textViewDogruYanlisSonuc.text = "${dogruSayac} Dogru  ${5-dogruSayac} Yanlis"
        binding.textViewBasariSonuc.text = "%${basariOrani} Basari"


    }
}