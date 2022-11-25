package br.senai.sp.jandira.games3.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "tbl_user")
class User {

    @PrimaryKey(autoGenerate = true) var id = 0

    var name = ""
    //var photo: Drawable? = null
    var email = ""
    var password = ""
    var city = ""
    var birthDate = ""
    var sex = 'I'
    var console = ""
    var level = EnumLevel.BEGINNER
}