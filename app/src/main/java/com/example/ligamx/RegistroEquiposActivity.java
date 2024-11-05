package com.example.ligamx;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegistroEquiposActivity extends AppCompatActivity {

    private ImageButton[] logoButtons;
    private EditText txtNombreEquipo, txtAlias; // EditText para el nombre y alias del equipo
    private int logoSeleccionado = -1; // Variable para almacenar el índice del logo seleccionado
    private SQLiteHelper dbHelper;
    private Spinner spinnerLocacion; // Spinner para seleccionar la locación

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_equipo);

        dbHelper = new SQLiteHelper(this);

        txtNombreEquipo = findViewById(R.id.txtNombreEquipo); // EditText para el nombre
        txtAlias = findViewById(R.id.txtAlias); // EditText para el alias
        spinnerLocacion = findViewById(R.id.spinnerLocacion); // Inicializa el Spinner

        logoButtons = new ImageButton[]{
                findViewById(R.id.btnLogo1),
                findViewById(R.id.btnLogo2),
                findViewById(R.id.btnLogo3),
                findViewById(R.id.btnLogo4),
                findViewById(R.id.btnLogo5),
                findViewById(R.id.btnLogo6),
                findViewById(R.id.btnLogo7),
                findViewById(R.id.btnLogo8),
                findViewById(R.id.btnLogo9),
                findViewById(R.id.btnLogo10),
                findViewById(R.id.btnLogo11),
                findViewById(R.id.btnLogo12),
                findViewById(R.id.btnLogo13),
                findViewById(R.id.btnLogo14),
                findViewById(R.id.btnLogo15),
                findViewById(R.id.btnLogo16),
                findViewById(R.id.btnLogo17),
                findViewById(R.id.btnLogo18),
        };

        for (int i = 0; i < logoButtons.length; i++) {
            final int index = i;
            logoButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onLogoSelected(index);
                }
            });
        }

        findViewById(R.id.btnRegistrar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarEquipo();
            }
        });
    }

    private void onLogoSelected(int index) {
        logoSeleccionado = index; // Guardar el índice del logo seleccionado
        Toast.makeText(this, "Logo seleccionado: " + logoSeleccionado, Toast.LENGTH_SHORT).show();
    }

    private void registrarEquipo() {
        String nombreEquipo = txtNombreEquipo.getText().toString();
        String alias = txtAlias.getText().toString();

        // Obtener la locación seleccionada del Spinner
        String locacionSeleccionada = spinnerLocacion.getSelectedItem().toString();

        if (nombreEquipo.isEmpty() || alias.isEmpty() || locacionSeleccionada.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Registrar el equipo en la base de datos
        long id = dbHelper.registrarEquipo(nombreEquipo, alias, logoSeleccionado, locacionSeleccionada);
        if (id > 0) {
            Toast.makeText(this, "Equipo registrado con éxito", Toast.LENGTH_SHORT).show();
            // Limpiar los campos después de registrar
            txtNombreEquipo.setText("");
            txtAlias.setText("");
            logoSeleccionado = -1;
            spinnerLocacion.setSelection(0);
        } else {
            Toast.makeText(this, "Error al registrar el equipo", Toast.LENGTH_SHORT).show();
        }

    }

}
