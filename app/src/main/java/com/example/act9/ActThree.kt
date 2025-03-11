package com.example.act9

import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.ScrollView
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.act9.databinding.ActThreeBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ActThree : BaseActivity() {

    private lateinit var binding: ActThreeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActThreeBinding.inflate(layoutInflater)
        val rootView = binding.root

        findViewById<ViewGroup>(R.id.fragment_container)?.addView(rootView)

        var message = "onCreate was Triggered!"
        Log.d("Cycle", message)
        appendLog(message)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.destroyButton.setOnClickListener{
            finish()
        }

    }
    override fun onStart() {
        super.onStart()

        val message = "onCreate was Triggered!"
        Log.d("Cycle", message)
        appendLog(message)
    }

    override fun onResume() {
        super.onResume()
        val message = "onResume was Triggered!"
        Log.d("Cycle", message)
        appendLog(message)
    }

    override fun onPause() {
        super.onPause()
        val message = "onPause was triggered!"
        Log.d("Cycle", message)
        appendLog(message)
    }

    override fun onStop() {
        super.onStop()
        val message = "onStop was triggered!"
        Log.d("Cycle", message)
        appendLog(message)
    }

    override fun onDestroy() {
        super.onDestroy()
        val message = "onDestroy was triggered!"
        Log.d("Cycle", message)
    }

    fun appendLog(message: String){
        val timestamp: String = SimpleDateFormat("hh:mm:ss", Locale.getDefault()).format(Date())
        val logMessage = "[$timestamp] $message"

        binding.logTextView.append("$logMessage \n")

        binding.scrollView2.post{binding.scrollView2.fullScroll(ScrollView.FOCUS_DOWN)}

        if (binding.logTextView.lineCount > 15){
            val texto: String = binding.logTextView.text.toString()
            val start: Int = texto.indexOf("\n", texto.indexOf("\n") + 1)
            binding.logTextView.text = texto.substring(start)
        }
    }
}