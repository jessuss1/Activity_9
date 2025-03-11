package com.example.act9

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

//This class adds the functionality to the "X" button which closes the current activity
class CloseButtonFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_close_button, container, false)

        val closeButton: Button = view.findViewById(R.id.closeButton)
        closeButton.setOnClickListener{ requireActivity().finish() }
        return view
    }
}