package br.senai.sp.jandira.imc20

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import br.senai.sp.jandira.imc20.databinding.ActivityCalculateBinding
import br.senai.sp.jandira.imc20.databinding.ActivitySingupBinding
import br.senai.sp.jandira.imc20.model.User

class SingupActivity : AppCompatActivity() {
    lateinit var editName: EditText
    lateinit var editEmail: EditText
    lateinit var editPassword: EditText
    lateinit var editWeight: EditText
    lateinit var editHeight: EditText
    lateinit var buttonSave: Button

    lateinit var binding: ActivitySingupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_singup)

        binding = ActivitySingupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        //Instanciar os componentes (view)
        editName = findViewById(R.id.editTextName)
        editEmail = findViewById(R.id.editTextEmail)
        editPassword = findViewById(R.id.editTextPassword)
        editWeight = findViewById(R.id.editTextWeight)
        editHeight = findViewById(R.id.editTextHeight)
        buttonSave = findViewById(R.id.buttonUserSave)

        //Colocar clique no botao
        buttonSave.setOnClickListener {
            saveUser()
        }
        binding.buttonUserSave.setOnClickListener {
            saveUser()
        }
    }
    private fun saveUser() {
        if (validate()) {
            val user  = User()
            user.id = 1
            user.name = editName.text.toString()
            user.email = editEmail.text.toString()
            user.password = editPassword.text.toString()
            user.weight = editWeight.text.toString().toInt()
            user.height = editHeight.text.toString().toDouble()

            //Gravar usuarios no Shared Preferences
            //Step 1 - Obter uma instancia no SharedPreferences
            val dados = getSharedPreferences("dados", MODE_PRIVATE)
            //Step 2 - Criar um editor para o arquivo
            val editor = dados.edit()
            //Step 3 - Inserir os dados no arquivo SharedPreferences
            editor.putInt("id", user.id)
            editor.putString("name", user.name)
            editor.putString("email", user.email)
            editor.putString("password", user.password)
            editor.putInt("weight", user.weight)
            editor.putFloat("height", user.height.toFloat())

            if (editor.commit()) {
                Toast.makeText(this, "Cadastro do usuário feito com sucesso", Toast.LENGTH_SHORT).show()
                //Fecha a Activity
                finish()
            } else {
                Toast.makeText(this, "Ocorreu um erro no cadastro", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validate(): Boolean {
        if(binding.editTextName.text.isEmpty()){
            binding.editTextName.error = "Name is required"
            return false
        }
        if (binding.editTextEmail.text.isEmpty()){
            binding.editTextEmail.error = "Email is required"
            return false
        }
        if (binding.editTextPassword.text.isEmpty()){
            binding.editTextPassword.error = "Password is required"
            return false
        }
        if (binding.editTextHeight.text.isEmpty()){
            binding.editTextHeight.error = "Height is required"
            return false
        }
        if (binding.editTextWeight.text.isEmpty()){
            binding.editTextWeight.error = "Weight is required"
        }
        return true
    }
}