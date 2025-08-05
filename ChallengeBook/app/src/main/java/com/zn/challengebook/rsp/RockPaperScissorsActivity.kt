package com.zn.challengebook.rsp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.zn.challengebook.R


class RockPaperScissorsActivity : AppCompatActivity() {

    private var player1Selection = "R"
    private var player2Selection = "R"

    private lateinit var viewPlayer1Rock: CardView
    private lateinit var viewPlayer1Paper: CardView
    private lateinit var viewPlayer1Scissors: CardView
    private lateinit var viewPlayer2Rock: CardView
    private lateinit var viewPlayer2Paper: CardView
    private lateinit var viewPlayer2Scissors: CardView
    private lateinit var btnJugar: Button
    private lateinit var tvGanador: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_rock_scissors_paper)

        initComponents()
        initListeners()

    }

    fun initListeners() {

        //jugador1 selecciona piedra
        viewPlayer1Rock.setOnClickListener {
            viewPlayer1Rock.setCardBackgroundColor(setSelectedColor())
            viewPlayer1Paper.setCardBackgroundColor(setUnselectedColor())
            viewPlayer1Scissors.setCardBackgroundColor(setUnselectedColor())
            player1Selection = "R"
        }

        //jugador1 selecciona papel
        viewPlayer1Paper.setOnClickListener {
            viewPlayer1Paper.setCardBackgroundColor(setSelectedColor())
            viewPlayer1Rock.setCardBackgroundColor(setUnselectedColor())
            viewPlayer1Scissors.setCardBackgroundColor(setUnselectedColor())
            player1Selection = "P"
        }

        //jugador1 selecciona tijera
        viewPlayer1Scissors.setOnClickListener {
            viewPlayer1Scissors.setCardBackgroundColor(setSelectedColor())
            viewPlayer1Rock.setCardBackgroundColor(setUnselectedColor())
            viewPlayer1Paper.setCardBackgroundColor(setUnselectedColor())
            player1Selection = "S"
        }

        //jugador2 selecciona piedra
        viewPlayer2Rock.setOnClickListener {
            viewPlayer2Rock.setCardBackgroundColor(setSelectedColor())
            viewPlayer2Paper.setCardBackgroundColor(setUnselectedColor())
            viewPlayer2Scissors.setCardBackgroundColor(setUnselectedColor())
            player2Selection = "R"
        }

        //jugador2 selecciona papel
        viewPlayer2Paper.setOnClickListener {
            viewPlayer2Paper.setCardBackgroundColor(setSelectedColor())
            viewPlayer2Rock.setCardBackgroundColor(setUnselectedColor())
            viewPlayer2Scissors.setCardBackgroundColor(setUnselectedColor())
            player2Selection = "P"
        }

        //jugador2 selecciona tijera
        viewPlayer2Scissors.setOnClickListener {
            player2Selection = "S"
            viewPlayer2Scissors.setCardBackgroundColor(setSelectedColor())
            viewPlayer2Rock.setCardBackgroundColor(setUnselectedColor())
            viewPlayer2Paper.setCardBackgroundColor(setUnselectedColor())
        }

        btnJugar.setOnClickListener {
            tvGanador.text = quienGana(player1Selection, player2Selection)
        }

    }

    fun initComponents() {
        viewPlayer1Rock = findViewById(R.id.viewPlayer1Rock)
        viewPlayer1Paper = findViewById(R.id.viewPlayer1Paper)
        viewPlayer1Scissors = findViewById(R.id.viewPlayer1Scissors)
        viewPlayer2Rock = findViewById(R.id.viewPlayer2Rock)
        viewPlayer2Paper = findViewById(R.id.viewPlayer2Paper)
        viewPlayer2Scissors = findViewById(R.id.viewPlayer2Scissors)
        btnJugar = findViewById(R.id.btnJugar)
        tvGanador = findViewById(R.id.tvGanador)
    }

    private fun setSelectedColor(): Int {
        val colorReference = R.color.secondary_color
        return ContextCompat.getColor(this, colorReference)
    }

    private fun setUnselectedColor(): Int {
        val colorReference = R.color.card_background_color
        return ContextCompat.getColor(this, colorReference)
    }

    private fun quienGana(player1: String, player2: String): String {

        val jugador1 = "El ganador es el Jugador 1"
        val jugador2 = "El ganador es el Jugador 2"
        val empate = "Empate"
        val errorRPS = "Error"

        when (player1) {
            "R" ->
                return when (player2) {
                    "P" -> {
                        jugador2
                    }

                    "S" -> {
                        jugador1
                    }

                    "R" -> {
                        empate
                    }

                    else -> {
                        errorRPS
                    }
                }

            "P" -> return when (player2) {
                "P" -> {
                    empate
                }

                "S" -> {
                    jugador2
                }

                "R" -> {
                    jugador1
                }

                else -> {
                    errorRPS
                }
            }

            "S" -> return when (player2) {
                "P" -> {
                    jugador1
                }

                "S" -> {
                    empate
                }

                "R" -> {
                    jugador2
                }

                else -> {
                    errorRPS
                }
            }

        }

        return "Error"

    }
}