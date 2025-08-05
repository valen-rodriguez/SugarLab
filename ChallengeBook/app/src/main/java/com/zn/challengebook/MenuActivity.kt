package com.zn.challengebook

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.zn.challengebook.anagrama.AnagramaActivity
import com.zn.challengebook.fizzbuzz.FizzBuzzActivity
import com.zn.challengebook.rsp.RockPaperScissorsActivity

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)

        val fizzBuzzBtn = findViewById<Button>(R.id.fizzBuzzBtn)
        val anagramaBtn = findViewById<Button>(R.id.anagramaBtn)
        val rpsBtn = findViewById<Button>(R.id.rpsBtn)

        fizzBuzzBtn.setOnClickListener { navigateToFizzBuzzApp() }
        anagramaBtn.setOnClickListener { navigateToAnagramaApp() }
        rpsBtn.setOnClickListener { navigateToRPSApp() }

    }

    fun navigateToFizzBuzzApp(){
        val fizzBuzzAct = Intent(this, FizzBuzzActivity::class.java)
        startActivity(fizzBuzzAct)
    }

    fun navigateToAnagramaApp(){
        val anagramaAct = Intent(this, AnagramaActivity::class.java)
        startActivity(anagramaAct)
    }

    fun navigateToRPSApp(){
        val rpsAct = Intent(this, RockPaperScissorsActivity::class.java)
        startActivity(rpsAct)
    }
}