package br.senai.sp.jandira.games3.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.senai.sp.jandira.games3.R
import br.senai.sp.jandira.games3.adapter.GamesAdapter
import br.senai.sp.jandira.games3.repository.GamesRepository
import java.time.Year

class UserActivity : AppCompatActivity() {
    lateinit var rvGames: RecyclerView
    lateinit var adapterGames: GamesAdapter
    lateinit var gamesRepository: GamesRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_user)

        rvGames = findViewById(R.id.rv_games)
        rvGames.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val nome = findViewById<TextView>(R.id.name)
        val email = findViewById<TextView>(R.id.email)
        val level = findViewById<TextView>(R.id.level)
        var age = findViewById<TextView>(R.id.age)

        val dataNascimento = intent.getStringExtra("date")

        val ageNumber = Year.now().value - (dataNascimento?.substring(0, 4)?.toInt() ?: Year.now().value)

        nome.text = intent.getStringExtra("name")
        email.text = intent.getStringExtra("email")
        level.text = intent.getStringExtra("level")
        age.text = ageNumber.toString()
    }

    override fun onResume() {
        super.onResume()
        carregarRV()
    }

    private fun carregarRV() {
        gamesRepository = GamesRepository(this)

        val lista = gamesRepository.getAllGames()

        adapterGames = GamesAdapter(lista,this)
        adapterGames.updateGamesList(gamesRepository.getAllGames())

        rvGames.adapter = adapterGames
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.menu_user, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.menu_add){
            val openCreateNewGameActivity = Intent(this, CreateNewGameActivity::class.java)
            startActivity(openCreateNewGameActivity)

            return true
        }

        return true
    }
}