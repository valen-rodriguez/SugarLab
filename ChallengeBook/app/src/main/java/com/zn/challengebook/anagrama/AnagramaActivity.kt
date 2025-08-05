package com.zn.challengebook.anagrama

import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.zn.challengebook.R
import com.zn.challengebook.consola.esAnagrama

class AnagramaActivity : AppCompatActivity() {

    private lateinit var tiWord1: TextInputEditText
    private lateinit var tiWord2: TextInputEditText
    private lateinit var btnEsAnagrama: Button
    private lateinit var tvEsAnagrama: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_anagrama)
        initComponents()

        btnEsAnagrama.setOnClickListener {

            val word1: Editable? = tiWord1.text
            val word2: Editable? = tiWord2.text
            val word1Lower = word1.toString().lowercase()
            val word2Lower = word2.toString().lowercase()

            if (word1Lower == null || word2Lower == null) {
                tvEsAnagrama.text = "No es anagrama"
            } else if (word1Lower == word2Lower) {
                tvEsAnagrama.text = "No es anagrama"
            } else if (word1Lower.length != word2Lower.length) {
                tvEsAnagrama.text = "No es anagrama"
            } else {
                val arrayWord1 = word1Lower.toCharArray().sortedArray()
                val arrayWord2 = word2Lower.toCharArray().sortedArray()

                if (arrayWord1.contentEquals(arrayWord2)) {
                    tvEsAnagrama.text = "Es anagrama"
                }else{
                    tvEsAnagrama.text = "No es anagrama"
                }
            }
        }
    }

    private fun initComponents() {
        tiWord1 = findViewById(R.id.tiWord1)
        tiWord2 = findViewById(R.id.tiWord2)
        btnEsAnagrama = findViewById(R.id.btnEsAnagrama)
        tvEsAnagrama = findViewById(R.id.tvEsAnagrama)
    }
}