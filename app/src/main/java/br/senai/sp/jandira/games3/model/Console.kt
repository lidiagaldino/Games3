package br.senai.sp.jandira.games3.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_console")
class Console {

    @PrimaryKey(autoGenerate = true) var id = 0

    var nome = ""
    var fabricante = ""
    var descricao = ""
    //var photo: Drawable? = null
    var anoLancamento = 0
}