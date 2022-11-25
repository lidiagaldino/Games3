package br.senai.sp.jandira.games3.dao

import androidx.room.*
import br.senai.sp.jandira.games3.model.Console
import br.senai.sp.jandira.games3.model.Games
import br.senai.sp.jandira.games3.model.User

@Dao
interface GamesDao {

    @Insert
    fun saveUser (user: User): Long

    @Delete
    fun deleteUser(user: User): Int

    @Update
    fun updateUser(user: User): Int

    @Insert
    fun saveConsole (console: Console): Long

    @Delete
    fun deleteConsole(console: Console): Int

    @Update
    fun updateConsole(console: Console): Int

    @Query("SELECT nome FROM tbl_console ORDER BY nome ASC")
    fun getAllConsole(): List<String>

    @Insert
    fun saveGame (games: Games): Long

    @Delete
    fun deleteGame(games: Games): Int

    @Update
    fun updateGame(games: Games): Int

    @Query("SELECT * FROM tbl_games ORDER BY name ASC")
    fun getAllGames(): List<Games>

    @Query("SELECT * FROM tbl_user WHERE email = :email AND password = :pass")
    fun autenticarUser(email: String, pass: String): User
}