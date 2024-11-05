package com.example.ligamx;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class GenerarApuestaActivity extends AppCompatActivity {
    private Spinner spinnerEquipoLocal, spinnerEquipoVisitante;
    private EditText txtCantidadApuesta;
    private Button btnGenerarResultado;
    private SQLiteHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generar_apuesta);

        dbHelper = new SQLiteHelper(this);

        spinnerEquipoLocal = findViewById(R.id.spinnerEquipoLocal);
        spinnerEquipoVisitante = findViewById(R.id.spinnerEquipoVisitante);
        txtCantidadApuesta = findViewById(R.id.txtCantidadApuesta);
        btnGenerarResultado = findViewById(R.id.btnGenerarResultado);

        cargarEquiposEnSpinners();

        btnGenerarResultado.setOnClickListener(v -> generarApuesta());
    }

    private void cargarEquiposEnSpinners() {
        List<String> equipos = obtenerNombresEquipos();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, equipos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerEquipoLocal.setAdapter(adapter);
        spinnerEquipoVisitante.setAdapter(adapter);
    }

    private List<String> obtenerNombresEquipos() {
        List<String> nombresEquipos = new ArrayList<>();
        Cursor cursor = dbHelper.obtenerEquipos();

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    int nombreIndex = cursor.getColumnIndex("nombre");
                    if (nombreIndex >= 0) {
                        String nombreEquipo = cursor.getString(nombreIndex);
                        nombresEquipos.add(nombreEquipo);
                    }
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        return nombresEquipos;
    }

    private void generarApuesta() {
        String equipoLocal = spinnerEquipoLocal.getSelectedItem().toString();
        String equipoVisitante = spinnerEquipoVisitante.getSelectedItem().toString();
        double cantidad;

        try {
            cantidad = Double.parseDouble(txtCantidadApuesta.getText().toString());
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Ingresa una cantidad válida", Toast.LENGTH_SHORT).show();
            return;
        }

        // Selección aleatoria del ganador
        Random random = new Random();
        String ganador;
        if (random.nextBoolean()) {
            ganador = equipoLocal;
        } else {
            ganador = equipoVisitante;
        }

        // Registrar el partido en la base de datos
        dbHelper.registrarPartido(equipoLocal, equipoVisitante, cantidad, ganador);

        // Mostrar un mensaje con el resultado de la apuesta
        Toast.makeText(this, "Apuesta generada: El ganador es " + ganador, Toast.LENGTH_SHORT).show();
    }

}
