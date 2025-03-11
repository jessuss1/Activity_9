package com.example.act9

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.act9.databinding.ActFourBinding

class ActFour : BaseActivity() {

    private lateinit var binding: ActFourBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActFourBinding.inflate(layoutInflater)
        val rootView = binding.root

        findViewById<ViewGroup>(R.id.fragment_container)?.addView(rootView)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        binding.careerComboBox.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position == 0) {
                    binding.nextButton.alpha = 0.5f
                    binding.nextButton.isEnabled = false
                } else {
                    binding.nextButton.alpha = 1f
                    binding.nextButton.isEnabled = true
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }




        binding.nextButton.setOnClickListener{

            val name: String? = binding.nameTextField.text.toString()
            val group: String? = binding.groupTextField.text.toString()
            val career = binding.careerComboBox.selectedItem.toString()

            val bundle = Bundle()
            bundle.putString("name", name)
            bundle.putString("group", group)
            bundle.putString("career", career)

            Intent(this, ActFourB::class.java).apply {
                putExtras(bundle)
                startActivity(this)
            }
        }

    }
}