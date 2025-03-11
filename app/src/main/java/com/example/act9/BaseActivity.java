package com.example.act9;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;



//This class inserts the Fragment in the inheritors
public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        if (savedInstanceState == null) {

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new CloseButtonFragment())
                    .commit();
        }
    }
}
