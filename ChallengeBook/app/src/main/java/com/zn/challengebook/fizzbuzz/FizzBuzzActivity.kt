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

        //RangeSlider del numero
        rsNumberSelected.addOnChangeListener { _, value, _ ->

            val numberSelected = value.toInt()

            //si no es multiplo de 3 o 5 se imprime solo el num
            if (numberSelected % 3 != 0 && numberSelected % 5  != 0 ){
                tvFizzOrBuzz.text = ""
            }else if (numberSelected % 3 == 0 && numberSelected % 5 == 0 ){
                tvFizzOrBuzz.text = "FizzBuzz" //si es multiplo de 3 y 5 se imprime Fizzbuzz
            }else if (numberSelected % 3 == 0){
                tvFizzOrBuzz.text = "Fizz" //si es multiplo de 3 se imprime Fizz
            }else{
                tvFizzOrBuzz.text = "Buzz" //si es multiplo de 5 se imprime Buzz
            }

            //Muestra el numero en pantalla
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