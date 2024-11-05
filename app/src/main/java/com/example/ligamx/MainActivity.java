package com.example.ligamx;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnRegistroEquipo).setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, RegistroEquiposActivity.class));
        });

        findViewById(R.id.btnConsultarEquipos).setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ConsultarPartidosActivity.class));
        });

        findViewById(R.id.btnGenerarApuesta).setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, GenerarApuestaActivity.class));
        });

        findViewById(R.id.btnHistorialPartidos).setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, HistorialEquiposActivity.class));
        });
    }
}
