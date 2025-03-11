package com.example.act9;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ActFive extends AppCompatActivity {
    private int[] images;
    private String[] names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load images dynamically, excluding those with "special_" and "ic_launcher" prefix
        images = getAllDrawables();
        names = getResources().getStringArray(R.array.sabritas_names);

        TextView selectionIndicator = new TextView(this);
        RecyclerView recyclerView = new RecyclerView(this);
        Button actionButton = new Button(this);
        actionButton.setText("Submit");

        // Initial button state (disabled and grayed out)
        actionButton.setEnabled(false);
        actionButton.setAlpha(0.5f);

        ImageAdapter adapter = new ImageAdapter(images, names, selectionIndicator, actionButton);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        recyclerView.setAdapter(adapter);


        //Solo muestra una alerta no intrusiva que muestra las selecciones hechas a partir del HahsSet
        actionButton.setOnClickListener(v -> {

//            int selectedIds = adapter.getSelectedPositions();

//            Log.d("Selections", ""+selectedIds);
//            if (!selectedIds.isEmpty()) {
//                Intent intent = new Intent(ActFive.this, ActFiveB.class);
//                intent.putIntegerArrayListExtra("SELECTED_IMAGES", selectedIds);
//                startActivity(intent);
//            } else {
//                Toast.makeText(this, "No selection made!", Toast.LENGTH_SHORT).show();
//            }
        });

        LinearLayout rootLayout = LayoutUtils.createThreePartLayout(this, recyclerView, selectionIndicator, actionButton);
        setContentView(rootLayout);
    }

    // Extrae todos los elementos de res/drawables en una lista
    private int[] getAllDrawables() {
        List<Integer> drawableIds = new ArrayList<>();
        Field[] fields = R.drawable.class.getDeclaredFields();

        for (Field field : fields) {
            try {
                int resId = field.getInt(null);
                String resName = field.getName();

                // Excluimos las imagenes de paketaxos y los iconos de la app
                if (!resName.startsWith("special_") && !resName.startsWith("ic_launcher")) {
                    drawableIds.add(resId);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        // Convertimos la lista a array
        int[] drawableArray = new int[drawableIds.size()];
        for (int i = 0; i < drawableIds.size(); i++) {
            drawableArray[i] = drawableIds.get(i);
        }
        return drawableArray;
    }

}
