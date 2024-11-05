package com.example.ligamx;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ConsultarPartidosActivity extends AppCompatActivity {
    private ListView listViewEquipos;
    private ArrayList<String> listaEquipos;
    private ArrayAdapter<String> adapter;
    private SQLiteHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_partidos);

        // Inicializar el helper de la base de datos
        dbHelper = new SQLiteHelper(this);

        // Inicializar la lista y el adapter
        listaEquipos = new ArrayList<>();
        listViewEquipos = findViewById(R.id.listViewEquipos);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaEquipos);
        listViewEquipos.setAdapter(adapter);

        // Cargar equipos
        cargarEquipos();
    }

    private void cargarEquipos() {
        Cursor cursor = dbHelper.obtenerEquipos(); // Asegúrate de que este método esté correctamente implementado en SQLiteHelper

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    int nombreIndex = cursor.getColumnIndex("nombre");
                    int aliasIndex = cursor.getColumnIndex("alias");
                    int locacionIndex = cursor.getColumnIndex("locacion");
                    int logoIndex = cursor.getColumnIndex("logo");

                    if (nombreIndex >= 0 && aliasIndex >= 0 && locacionIndex >= 0 && logoIndex >= 0) {
                        String nombreEquipo = cursor.getString(nombreIndex);
                        String alias = cursor.getString(aliasIndex);
                        String locacion = cursor.getString(locacionIndex);
                        int logo = cursor.getInt(logoIndex);

                        // Puedes personalizar el formato del texto a mostrar en la lista
                        String equipoInfo = "Nombre: " + nombreEquipo + "\nAlias: " + alias + "\nLocación: " + locacion + "\nLogo ID: " + logo;
                        listaEquipos.add(equipoInfo);
                    } else {
                        Log.e("cargarEquipos", "Una o más columnas no fueron encontradas en el cursor.");
                    }
                } while (cursor.moveToNext());
            }
            cursor.close(); // Asegúrate de cerrar el cursor
        } else {
            Log.e("cargarEquipos", "El cursor es null.");
        }
        adapter.notifyDataSetChanged(); // Notificar al adaptador que los datos han cambiado
    }

}
