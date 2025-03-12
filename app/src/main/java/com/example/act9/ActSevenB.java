package com.example.act9;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.content.FileProvider;

import com.example.act9.databinding.ActSevenBBinding;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ActSevenB extends AppCompatActivity {

    private String[] maestrosRedes;
    private String[] materiasRedes;
    private String[] maestrosProgramacion;
    private String[] materiasProgramacion;
    String name;
    String fase;
    int selectedIndex;

    private ActSevenBBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActSevenBBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(binding.schedule, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        name = getIntent().getStringExtra("name");
        fase = getIntent().getStringExtra("fase");
        selectedIndex = getIntent().getIntExtra("selectedIndex", 0);

        binding.displayNameTextView.setText("Nombre:\n" + name);
        binding.displayFaseTextView.setText("Fase:\n" + fase);

        if (selectedIndex == 1 || selectedIndex == 2) {
            materiasProgramacion = getResources().getStringArray(R.array.materiasProgramacion);
            maestrosProgramacion = getResources().getStringArray(R.array.maestrosProgramacion);

            for (int i = 0; i < maestrosProgramacion.length; i++) {
                int tableRow = getResources().getIdentifier("row" + i, "id", getPackageName());
                int maestroTextView = getResources().getIdentifier("maestro" + i, "id", getPackageName());
                TextView maestroTV = binding.getRoot().findViewById(maestroTextView);

                int materiaTextView = getResources().getIdentifier("materia" + i, "id", getPackageName());
                TextView materiaTV = binding.getRoot().findViewById(materiaTextView);

                if (maestroTV != null && materiaTV != null) {
                    if (i % 2 == 0) {
                        binding.getRoot().findViewById(tableRow).setBackgroundColor(Color.argb(25, 0, 122, 255));
                    }
                    maestroTV.setText(maestrosProgramacion[i]);
                    materiaTV.setText(materiasProgramacion[i]);
                }
            }

        } else if (selectedIndex == 3 || selectedIndex == 4) {
            maestrosRedes = getResources().getStringArray(R.array.maestrosRedes);
            materiasRedes = getResources().getStringArray(R.array.materiasRedes);

            for (int i = 0; i < maestrosRedes.length; i++) {
                int tableRow = getResources().getIdentifier("row" + i, "id", getPackageName());
                int maestroTextView = getResources().getIdentifier("maestro" + i, "id", getPackageName());
                TextView maestroTV = binding.getRoot().findViewById(maestroTextView);

                int materiaTextView = getResources().getIdentifier("materia" + i, "id", getPackageName());
                TextView materiaTV = binding.getRoot().findViewById(materiaTextView);

                if (maestroTV != null && materiaTV != null) {
                    if (i % 2 == 0) {
                        binding.getRoot().findViewById(tableRow).setBackgroundColor(Color.argb(25, 0, 122, 255));
                    }
                    maestroTV.setText(maestrosRedes[i]);
                    materiaTV.setText(materiasRedes[i]);
                }
            }

        } else {
            Toast.makeText(this, "The selected index was " + selectedIndex + "!", Toast.LENGTH_LONG).show();
        }

        binding.shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Uri imageUri = captureTableScreenshot(binding.schedule);

                if (imageUri == null) {
                    Toast.makeText(ActSevenB.this, "Error capturing table", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Now only send the screenshot directly via email
                sendScreenshot(imageUri);
            }
        });
    }

    private Uri captureTableScreenshot(View tableView) {
        tableView.setDrawingCacheEnabled(true);
        tableView.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(tableView.getDrawingCache());
        tableView.setDrawingCacheEnabled(false);

        File file = new File(getCacheDir(), "table_screenshot.png");
        try (FileOutputStream fos = new FileOutputStream(file)) {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return FileProvider.getUriForFile(this, "com.example.act9.fileprovider", file);
    }

    private void sendScreenshot(Uri imageUri) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/png");
        intent.putExtra(Intent.EXTRA_STREAM, imageUri);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        // Prefill email fields
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"jeesuus479@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Horario");
        intent.putExtra(Intent.EXTRA_TEXT, "Aqui esta tu horario");

        intent.setPackage("com.google.android.gm");

        try {
            startActivity(intent);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "Gmail app not installed", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100) {
            File file = new File(getCacheDir(), "table_screenshot.png");
            if (file.exists()) {
                file.delete();
            }
        }
    }
}
