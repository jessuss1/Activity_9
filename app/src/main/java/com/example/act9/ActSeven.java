package com.example.act9;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import com.example.act9.R;
import com.example.act9.databinding.ActSevenBinding;

public class ActSeven extends AppCompatActivity {

    private ActSevenBinding binding;
    public int currentSelection;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActSevenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });









        binding.studentsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                currentSelection = binding.studentsSpinner.getSelectedItemPosition();

                Log.d("Test","Your selection "+currentSelection);


                //Si la eleccion del spinner es la primera (index 0) desactiva el boton
                if (currentSelection == 0){

                    binding.faseTextView.setText("");
                    binding.seeScheduleButton.setAlpha(.5f);
                    binding.seeScheduleButton.setEnabled(false);
                }
                else if (currentSelection == 3 || currentSelection == 4) {
                    binding.faseTextView.setText("Redes");
                    binding.seeScheduleButton.setAlpha(1);
                    binding.seeScheduleButton.setEnabled(true);

                } else {
                    binding.faseTextView.setText("Programacion");
                    binding.seeScheduleButton.setAlpha(1);
                    binding.seeScheduleButton.setEnabled(true);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {


            }
        });


        binding.seeScheduleButton.setOnClickListener(v -> {

            String name = binding.studentsSpinner.getSelectedItem().toString();
            String fase = binding.faseTextView.getText().toString();
            int selectedIndex = currentSelection;

            Intent intent = new Intent(ActSeven.this, ActSevenB.class);
            intent.putExtra("name", name);
            intent.putExtra("fase", fase);
            intent.putExtra("selectedIndex", selectedIndex);

            startActivity(intent);
        });


    }



}

