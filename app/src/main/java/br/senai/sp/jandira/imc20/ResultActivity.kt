package br.senai.sp.jandira.imc20

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.senai.sp.jandira.imc20.databinding.ActivityCalculateBinding
import br.senai.sp.jandira.imc20.databinding.ActivityResultBinding
import br.senai.sp.jandira.imc20.utils.getBmi
import br.senai.sp.jandira.imc20.utils.getStatusBmi

class ResultActivity : AppCompatActivity() {
    lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        //Recuperar os valores que estao na intent
        val peso = intent.getIntExtra("weight", 0)
        val altura = intent.getDoubleExtra("height", 0.0)

        val bmi = getBmi(peso, altura)
        val status = getStatusBmi(bmi, this)

        binding.textViewResult.text = "%.2f".format(bmi)
        binding.textViewStatus.text = status
    }

}