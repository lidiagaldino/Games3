package br.senai.sp.jandira.games3.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.senai.sp.jandira.games3.model.Console
import br.senai.sp.jandira.games3.model.Games
import br.senai.sp.jandira.games3.model.User

@Database(entities = [Games::class, User::class, Console::class], version = 1)
abstract class GamesDB: RoomDatabase() {

    abstract fun gamesDao(): GamesDao

    companion object {

        private lateinit var instace: GamesDB

        fun getDatabase(context: Context):GamesDB{
            if (!::instace.isInitialized){
                instace = Room.databaseBuilder(context, GamesDB::class.java, "db_games").allowMainThreadQueries().build()
            }
            return instace
        }
    }

}