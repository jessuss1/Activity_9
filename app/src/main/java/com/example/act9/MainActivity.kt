package com.example.act9

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.act9.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        Disables the "Continuar" button if the user selects the first item (index 0) on the Spinner
//        Spinner entries are located @ res/values/strings.xml


        var selectedActivity: Class<*>? = null

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position == 0) {
//                    Sets the button alpha to .5 to give the grayed out effect and disables it
                    binding.button.alpha = 0.5f
                    binding.button.isEnabled = false
                    selectedActivity = null

                } else {
//                    Restores the button properties
                    binding.button.alpha = 1f
                    binding.button.isEnabled = true

                    selectedActivity = when (position){
//                      NOTICE:  ALL LAYOUT FILES MUST BE DECLARED IN MANIFEST OTHERWISE IT WON'T OPEN AND THE APP WILL CRASH
                        1 -> ActOne::class.java
                        2 -> ActTwo::class.java
                        3 -> ActThree::class.java
                        4 -> ActFour::class.java
//                        ActFive is a placeholder for now
                        5 -> ActFive::class.java
                        6 -> ActSix::class.java

//                        TODO: Agregar las actividades 5, 7 y 8

                        else -> null
                    }

                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

//        ***************** onItemSelectedListener END *****************

//        Boton para lanzar la nueva actividad
        binding.button.setOnClickListener{


            if (selectedActivity != null){

                val intent = Intent (this, selectedActivity)
                startActivity(intent)
            }
        }
//   ***************** onClickListener END *****************

    }
}