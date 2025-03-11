package com.example.act9

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.act9.databinding.ActFourBBinding

class ActFourB : BaseActivity(){

    private lateinit var binding: ActFourBBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActFourBBinding.inflate(layoutInflater)
        val rootView = binding.root

        findViewById<ViewGroup>(R.id.fragment_container)?.addView(rootView)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val name = intent.getStringExtra("name")
        val group = intent.getStringExtra("group")
        val career = intent.getStringExtra("career")

        binding.nameLabel.text="Nombre: $name"
        binding.groupLabel.text="Grupo: $group"
        binding.careerLabel.text="Carrera: $career"

        binding.backButton.setOnClickListener{

                val intent = Intent(this@ActFourB, ActFour::class.java)
                startActivity(intent)
                finish()
        }

    }

}