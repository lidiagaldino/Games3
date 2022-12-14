package br.senai.sp.jandira.games3.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import br.senai.sp.jandira.games3.databinding.ActivityMainBinding
import br.senai.sp.jandira.games3.model.Console
import br.senai.sp.jandira.games3.repository.GamesRepository

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var console: Console
    lateinit var userRepository: GamesRepository

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        console = Console()
        userRepository = GamesRepository(this)
        val lista = userRepository.getAllConsole()

        if (lista.size == 0){
            cadastro()
        }

        binding.textSignup.setOnClickListener{
            val openSignupActivity = Intent(this, SignUpActivity::class.java)
            startActivity(openSignupActivity)
        }

        checkRemember()

        binding.buttonLogin.setOnClickListener {

            if(inputValidate()){
                val usuario = binding.editEmail.text.toString()
                val senha = binding.editPass.text.toString()

                val logar = userRepository.autenticate(usuario, senha)

                if(logar != null){
                    if (binding.checkBox.isChecked){
                        val sharedPreferences = getSharedPreferences("user", MODE_PRIVATE)
                        val editor = sharedPreferences.edit()
                        editor.putString("email", logar.email)
                        editor.putString("senha", logar.password)
                        editor.commit()
                    }
                    val openUserActivity = Intent(this, UserActivity::class.java)
                    openUserActivity.putExtra("name", logar.name)
                    openUserActivity.putExtra("email", logar.email)
                    openUserActivity.putExtra("level", logar.level.toString())
                    openUserActivity.putExtra("date", logar.birthDate)
                    startActivity(openUserActivity)
                } else{
                    Toast.makeText(this, "Usu??rio n??o encontrado", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

    private fun checkRemember() {

        val sharedPreferences = getSharedPreferences("user", MODE_PRIVATE)
        val email = sharedPreferences.getString("email", "") as String
        val pass = sharedPreferences.getString("senha", "") as String

        if (email.isNotEmpty() || pass.isNotEmpty()){

            fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)
            
            binding.editPass.text = pass.toEditable()
            binding.editEmail.text = email.toEditable()

        }
    }

    private fun cadastro() {
        console.anoLancamento = 2013
        console.descricao = "O PlayStation 4, ou PS4 (como ?? mais conhecido) ?? um video game da Sony lan??ado em 2013. O console tem tr??s vers??es principais: fat, slim e Pro. A primeira (e original) tem esse nome por causa do peso e conta com 500 GB de armazenamento."
        console.fabricante = "Sony"
        console.nome = "Playstation 4"

        userRepository = GamesRepository(this)

        userRepository.saveConsole(console)

        console.anoLancamento = 2013
        console.descricao = "O Xbox One ?? uma central multim??dia al??m de um videogame. Nele podem ser acessados diversos aplicativos como Netflix, YouTube e redes sociais que permitem o usu??rio ter Internet, TV e games em uma ??nica plataforma, al??m de ser compat??vel com o Windows 10 e a Xbox Live."
        console.fabricante = "Microsoft"
        console.nome = "Xbox One"

        userRepository.saveConsole(console)
    }

    private fun inputValidate(): Boolean {
        if (binding.editEmail.text.isEmpty()){
            binding.editEmail.error = "E-mail is required"
            return false
        }
        if (binding.editPass.text.isEmpty()){
            binding.editPass.error = "Password is required"
            return false
        }

        return true
    }
}