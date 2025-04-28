package br.com.livrokotlin.imc_calculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var editPeso: EditText
    private lateinit var editAltura: EditText
    private lateinit var botaoCalcular: Button
    private lateinit var textResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editPeso = findViewById(R.id.edit_peso)
        editAltura = findViewById(R.id.edit_altura)
        botaoCalcular = findViewById(R.id.botao_calcular)
        textResultado = findViewById(R.id.text_resultado)

        botaoCalcular.setOnClickListener {
            calcularIMC()
        }
    }

    private fun calcularIMC() {
        val pesoStr = editPeso.text.toString()
        val alturaStr = editAltura.text.toString()

        if (pesoStr.isEmpty() || alturaStr.isEmpty()) {
            Toast.makeText(this, getString(R.string.preencha_campos), Toast.LENGTH_SHORT).show()
            return
        }

        val peso = pesoStr.toFloatOrNull()
        val altura = alturaStr.toFloatOrNull()

        if (peso == null || altura == null) {
            Toast.makeText(this, getString(R.string.preencha_campos), Toast.LENGTH_SHORT).show()
            return
        }

        val imc = peso / (altura * altura)
        val resultado = when {
            imc < 18.5 -> "Abaixo do peso"
            imc in 18.5..24.9 -> "Peso normal"
            imc in 25.0..29.9 -> "Sobrepeso"
            imc in 30.0..34.9 -> "Obesidade grau 1"
            imc in 35.0..39.9 -> "Obesidade grau 2"
            else -> "Obesidade grau 3"
        }

        // Corrigido para usar a formatação com Locale
        val imcFormatted = String.format(Locale.getDefault(), "%.2f", imc)
        textResultado.text = getString(R.string.resultado_imc, imcFormatted, resultado)
    }
}
