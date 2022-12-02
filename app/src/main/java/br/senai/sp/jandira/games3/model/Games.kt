package br.senai.sp.jandira.games3.model

import android.graphics.drawable.Drawable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_games")
class Games {

    @PrimaryKey(autoGenerate = true) var id = 0
    var name = ""
    var descricao = ""
    //var photo: Drawable? = null
    var company = ""
    var anoLancamento = 0
    var finalizado: Boolean? = null
}