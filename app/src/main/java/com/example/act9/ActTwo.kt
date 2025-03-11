package com.example.act9

import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.act9.databinding.ActTwoBinding


class ActTwo : BaseActivity() {

    private lateinit var binding: ActTwoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActTwoBinding.inflate(layoutInflater)
        val rootView = binding.root

        findViewById<ViewGroup>(R.id.fragment_container)?.addView(rootView)

        ViewCompat.setOnApplyWindowInsetsListener(binding.actTwoLayout) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            binding.actTwoLayout.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.outputLabel.text = ""

        binding.okButton.setOnClickListener{ v ->

            try {

                val imm = getSystemService(InputMethodManager::class.java)
                imm?.hideSoftInputFromWindow(currentFocus?.windowToken, 0)

                val name: String? = binding.nameTextField.text.toString()

                binding.outputLabel.text = "Hola $name!"

            } catch (e: Exception){

                Log.e("IMM", "IMM provoked an exeption")

            }

        }

        binding.quitButton.setOnClickListener{ v ->
            finish()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("Message", binding.nameTextField.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        binding.outputLabel.text = savedInstanceState.getString("Message")
    }
}