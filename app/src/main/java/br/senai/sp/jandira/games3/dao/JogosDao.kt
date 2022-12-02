package br.senai.sp.jandira.games3.dao

import android.content.Context
import br.senai.sp.jandira.games3.R
import br.senai.sp.jandira.games3.model.Games
import br.senai.sp.jandira.games3.model.Jogos

class JogosDao {

    companion object{

        fun getGames(context: Context): List<Jogos>{

            val game1 = Jogos()

            game1.id = 1
            game1.name = "Street Fighter V"
            game1.company = "Capcom"
            game1.descricao = "Street Fighter, popularmente abreviado para SF, é uma popular série de jogos de luta."
            game1.photo = context.getDrawable(R.drawable.streetfighter_5)
            game1.anoLancamento = 2016

            val game2 = Jogos()

            game2.id = 2
            game2.name = "Ninja Turtles II"
            game2.company = "Konami"
            game2.descricao = "Teenage Mutant Ninja Turtles: Shredder's Revenge é um jogo eletrônico beat 'em up desenvolvido pela Tribute Games."
            game2.photo = context.getDrawable(R.drawable.tartaruga_ninja)
            game1.anoLancamento = 1991

            val game3 = Jogos()

            game3.id = 3
            game3.name = "The Last Of Us"
            game3.company = "Sony"
            game3.descricao = "The Last of Us é um jogo eletrônico de ação-aventura e sobrevivência desenvolvido pela Naughty Dog e publicado pela Sony Computer Entertainment."
            game3.photo = context.getDrawable(R.drawable.the_last_of_us)
            game1.anoLancamento = 2013

            val game4 = Jogos()

            game4.id = 4
            game4.name = "Assassins Creed Valhala"
            game4.company = "Ubsoft"
            game4.descricao = "Assassin's Creed Valhalla é um jogo eletrônico de RPG de ação desenvolvido pela Ubisoft Montreal e publicado pela Ubisoft."
            game4.photo = context.getDrawable(R.drawable.assassins_creed)
            game1.anoLancamento = 2020

            val gamesLista = listOf<Jogos>(game1, game2, game3, game4)

            return gamesLista
        }
    }
}