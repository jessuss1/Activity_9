package com.example.act9

import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.act9.databinding.ActSixBinding

class ActSix : BaseActivity() {

    lateinit var binding: ActSixBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActSixBinding.inflate(layoutInflater)
        val root = binding.root

        findViewById<ViewGroup>(R.id.fragment_container)?.addView(root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.resultTextView.text = ""
        binding.textView4.text = ""

        binding.button.setOnClickListener {
            binding.textView4.text = ""
            val imm = getSystemService(InputMethodManager::class.java)
            imm?.hideSoftInputFromWindow(currentFocus?.windowToken, 0)

            val selectedButton = binding.radioGroup.checkedRadioButtonId

            if (selectedButton == R.id.tempRadioButton) {
                if (binding.inputEditText.text.toString().trim().isNotEmpty()) {
                    tempConversion()
                }
            } else {
                currencyConversion()
            }
        }
    }

    private fun tempConversion() {
        try {
            val input = binding.inputEditText.text.toString().toFloat()
            val result = (input * 9f / 5f) + 32
            val roundedResult = Math.round(result)

            binding.resultTextView.text = "$input °C = $roundedResult °F"
            binding.textView4.text="El valor esta redondeado*"

        } catch (e: NumberFormatException) {
            val toast = Toast.makeText(this, "Ingrese solo números!", Toast.LENGTH_SHORT)
            val view = toast.view
            view?.setBackgroundResource(android.R.color.holo_orange_light)
            toast.show()
        }
    }

    private fun currencyConversion() {
        try {
            val usdRate = 20.44f
            val input = binding.inputEditText.text.toString().toFloat()
            val result = input / usdRate

            val formattedResult = String.format("%.2f", result)

            binding.resultTextView.text = "$input MXN = $$formattedResult USD"
        } catch (e: NumberFormatException) {
            val toast = Toast.makeText(this, "Ingrese solo números!", Toast.LENGTH_SHORT)
            val view = toast.view
            view?.setBackgroundResource(android.R.color.holo_orange_light)
            toast.show()
        }
    }
}
