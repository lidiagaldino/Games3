package br.senai.sp.jandira.games3.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.senai.sp.jandira.games3.R
import br.senai.sp.jandira.games3.adapter.GamesAdapter
import br.senai.sp.jandira.games3.databinding.ActivitySignUpBinding
import br.senai.sp.jandira.games3.model.User
import java.time.LocalDate

class UserActivity : AppCompatActivity() {
    lateinit var rvGames: RecyclerView
    lateinit var adapterGames: GamesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_user)

        rvGames = findViewById(R.id.rv_games)
        rvGames.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val nome = findViewById<TextView>(R.id.name)
        val email = findViewById<TextView>(R.id.email)
        val level = findViewById<TextView>(R.id.level)
        var age = findViewById<TextView>(R.id.age)

        val ageNumber = LocalDate.parse(age.toString())

        nome.text = intent.getStringExtra("name")
        email.text = intent.getStringExtra("email")
        level.text = intent.getStringExtra("level")
        age.text = ageNumber.toString()


        //adapterGames = GamesAdapter(this)
        //adapterGames.updateGamesList(GamesDao.getGames(this))

        //rvGames.adapter = adapterGames
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.menu_user, menu)

        return true
    }
}