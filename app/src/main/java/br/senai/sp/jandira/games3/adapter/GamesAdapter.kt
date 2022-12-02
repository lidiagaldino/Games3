package br.senai.sp.jandira.games3.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.senai.sp.jandira.games3.R
import br.senai.sp.jandira.games3.model.Games
import br.senai.sp.jandira.games3.model.Jogos

class GamesAdapter(val context: Context): RecyclerView.Adapter<GamesAdapter.HolderGames>() {

    private var gamesList = listOf<Jogos>()

    fun updateGamesList(games: List<Jogos>){
        this.gamesList = games
        notifyDataSetChanged()
    }

    class HolderGames(view: View): RecyclerView.ViewHolder(view){

        val textTituloGames = view.findViewById<TextView>(R.id.holder_titulo)
        val textMarcaGames = view.findViewById<TextView>(R.id.holder_marca)
        val textDescricacao = view.findViewById<TextView>(R.id.holder_descricao)
        val imageHolder = view.findViewById<ImageView>(R.id.holder_image)

        fun bind(games: Jogos) {

            textTituloGames.text = games.name
            textDescricacao.text = games.descricao
            textMarcaGames.text = games.company
            imageHolder.setImageDrawable(games.photo)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderGames {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.holder_layout, parent, false)

        return HolderGames(view)
    }

    override fun onBindViewHolder(holder: HolderGames, position: Int) {
        holder.bind(gamesList.get(position))
    }

    override fun getItemCount(): Int {
        return gamesList.size
    }
}