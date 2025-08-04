package com.zn.challengebook

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.zn.challengebook.fizzbuzz.FizzBuzzActivity

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)

        val fizzBuzzBtn = findViewById<Button>(R.id.fizzBuzzBtn)

        fizzBuzzBtn.setOnClickListener { navigateToFizzBuzzApp() }

    }

    fun navigateToFizzBuzzApp(){
        val fizzBuzzAct = Intent(this, FizzBuzzActivity::class.java)
        startActivity(fizzBuzzAct)
    }
}