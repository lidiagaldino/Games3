package br.senai.sp.jandira.games3.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import br.senai.sp.jandira.games3.R
import br.senai.sp.jandira.games3.databinding.ActivitySignUpBinding
import br.senai.sp.jandira.games3.model.EnumLevel
import br.senai.sp.jandira.games3.model.User
import br.senai.sp.jandira.games3.repository.GamesRepository
import java.text.DateFormat
import java.time.Year
import java.util.*

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    lateinit var userRepository: GamesRepository
    lateinit var user: User


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userRepository = GamesRepository(this)

        val lista = userRepository.getAllConsole()

        val arrayAdapter = ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, lista)
        binding.spinner.adapter = arrayAdapter

        binding.enumLevel.text = EnumLevel.BEGINNER.toString()

        binding.slider.addOnChangeListener { _, value, _ ->
            if (value.toInt() == 1){
                binding.enumLevel.text = EnumLevel.BEGINNER.toString()
            } else if (value.toInt() == 2){
                binding.enumLevel.text = EnumLevel.BASIC.toString()
            }else if (value.toInt() == 3){
                binding.enumLevel.text = EnumLevel.CASUAL.toString()
            }else if (value.toInt() == 4){
                binding.enumLevel.text = EnumLevel.ADVANCED.toString()
            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.menu_new_user, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.menu_save){
            if (verificar()){
                user = User()
                save()
            }
            return true
        }else if(item.itemId == R.id.menu_setting){
            Toast.makeText(this, "Configurações", Toast.LENGTH_SHORT).show()
            return true
        } else{
            Toast.makeText(this, "Sair", Toast.LENGTH_SHORT).show()
            return true
        }
    }

    private fun verificar(): Boolean {

        if (Year.now().value < binding.editBirthday.text.toString().substring(0, 4).toInt()){
            binding.editBirthday.error = "Digite uma data valida"
            return false
        }

        if (binding.editEmail.text.isEmpty()){
            binding.editEmail.error = "E-mail obrigatório"
            return false
        }

        if (binding.editName.text.isEmpty()){
            binding.editName.error = "Nome obrigatório"
            return false
        }

        if (binding.editPassword.text.isEmpty()){
            binding.editPassword.error = "Senha é obrigatório"
            return false
        }

        return true
    }

    private fun save() {

        user.email = binding.editEmail.text.toString()
        user.password = binding.editPassword.text.toString()
        user.name = binding.editName.text.toString()
        user.city = binding.editCity.text.toString()

        val op = binding.radioGroup!!.checkedRadioButtonId

        user.sex = op.toChar()
        user.console = binding.spinner.selectedItem.toString()
        user.level = EnumLevel.valueOf(binding.enumLevel.text.toString())
        user.birthDate = binding.editBirthday.text.toString()

        userRepository = GamesRepository(this)


        val id = userRepository.saveUser(user)

        Toast.makeText(this, "Salvo", Toast.LENGTH_LONG).show()


        finish()
    }
}