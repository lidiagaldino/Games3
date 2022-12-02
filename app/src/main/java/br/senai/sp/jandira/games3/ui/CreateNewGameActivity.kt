package br.senai.sp.jandira.games3.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.senai.sp.jandira.games3.databinding.ActivityCreateNewGameBinding
import br.senai.sp.jandira.games3.model.Games
import br.senai.sp.jandira.games3.repository.GamesRepository

class CreateNewGameActivity : AppCompatActivity() {

    lateinit var binding: ActivityCreateNewGameBinding
    lateinit var gamesRepository: GamesRepository
    lateinit var games: Games

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =ActivityCreateNewGameBinding.inflate(layoutInflater)

        setContentView(binding.root)

        gamesRepository = GamesRepository(this)
        games = Games()

        binding.button.setOnClickListener {
            if (verificar()){
                save()
            }
        }
    }

    private fun save() {

        games.name = binding.editNameGame.text.toString()
        games.anoLancamento = binding.editDateGame.text.toString().toInt()
        games.descricao = binding.editDescriptionGame.text.toString()
        games.company = binding.editCompanyGame.text.toString()
        games.finalizado = false

        val id = gamesRepository.saveGame(games)

        Toast.makeText(this, "Salvo", Toast.LENGTH_LONG).show()


        finish()
    }

    private fun verificar(): Boolean {
        if (binding.editCompanyGame.text.isEmpty()){
            binding.editCompanyGame.error = "Company name is required"
            return false
        }
        if (binding.editDateGame.text.isEmpty()){
            binding.editDateGame.error = "Date is required"
            return false
        }

        if (binding.editNameGame.text.isEmpty()){
            binding.editNameGame.error = "Name is required"
            return false
        }

        if (binding.editDescriptionGame.text.isEmpty()){
            binding.editDescriptionGame.error = "Description is required"
            return false
        }

        return true
    }
}