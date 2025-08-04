package com.zn.challengebook.fizzbuzz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.slider.RangeSlider
import com.zn.challengebook.MenuActivity
import com.zn.challengebook.R

class FizzBuzzActivity : AppCompatActivity() {

    private lateinit var rsNumberSelected: RangeSlider
    private lateinit var tvNumberSelected: TextView
    private lateinit var tvFizzOrBuzz: TextView
    private lateinit var btnReturnMenu: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_fizz_buzz)

        initComponents()

        rsNumberSelected.addOnChangeListener { _, value, _ ->
            val numberSelected = value.toInt()

            if (numberSelected % 3 != 0 && numberSelected % 5  != 0 ){
                tvFizzOrBuzz.text = ""
            }else if (numberSelected % 3 == 0 && numberSelected % 5 == 0 ){
                tvFizzOrBuzz.text = "FizzBuzz"
            }else if (numberSelected % 3 == 0){
                tvFizzOrBuzz.text = "Fizz"
            }else{
                tvFizzOrBuzz.text = "Buzz"
            }


            tvNumberSelected.text = numberSelected.toString()
        }

        btnReturnMenu.setOnClickListener {
            val returnMenu = Intent(this, MenuActivity::class.java)
            startActivity(returnMenu)
        }

    }

    private fun initComponents() {
        rsNumberSelected = findViewById(R.id.rsNumberSelected)
        tvNumberSelected = findViewById(R.id.tvNumberSelected)
        tvFizzOrBuzz = findViewById(R.id.tvFizzOrBuzz)
        btnReturnMenu = findViewById(R.id.btnReturnMenu)
    }
}