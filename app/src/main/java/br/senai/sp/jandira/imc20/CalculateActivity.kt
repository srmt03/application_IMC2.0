package br.senai.sp.jandira.imc20

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import br.senai.sp.jandira.imc20.databinding.ActivityCalculateBinding
import br.senai.sp.jandira.imc20.utils.getBmi

class CalculateActivity : AppCompatActivity() {

    lateinit var binding: ActivityCalculateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        loadProfile()

        binding.buttonCalculate.setOnClickListener {
            bmiCalculate()
        }
    }

    private fun bmiCalculate() {
        if (validate()){
            //Abir a tela de exibir resutado
            val openResult = Intent(this, ResultActivity::class.java)

            //Enviar dados pra outra tela
            openResult.putExtra("weight", binding.editTextWeight.text.toString().toInt())
            openResult.putExtra("height", binding.editTextHeight.text.toString().toDouble())


            //Iniciar a tela de resultado
            startActivity(openResult)
        }
    }

    private fun validate(): Boolean{
        if (binding.editTextWeight.text.isEmpty()){
            binding.editTextWeight.error = "Weight is required"
            return false
        }
        if (binding.editTextHeight.text.isEmpty()){
            binding.editTextHeight.error = "Height is required"
            return false
        }
        return true
    }

    private fun loadProfile() {
        //Abrir o arquivo Shared Preferences
        val dados = getSharedPreferences("dados", MODE_PRIVATE)

        binding.textViewUsername.text = dados.getString("name", "")
        binding.textViewEmail.text = dados.getString("email", "")
        binding.viewWeight.text = "${resources.getText(R.string.weight)} ${dados.getInt("weight", 0)}Kg"
        binding.viewHeight.text = "${resources.getText(R.string.tall)} ${dados.getFloat("height", 0.0f)}"

    }
}