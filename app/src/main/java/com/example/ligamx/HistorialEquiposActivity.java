    package com.example.ligamx;

    import android.database.Cursor;
    import android.os.Bundle;
    import androidx.appcompat.app.AppCompatActivity;
    import androidx.recyclerview.widget.LinearLayoutManager;
    import androidx.recyclerview.widget.RecyclerView;

    import java.util.ArrayList;

    public class HistorialEquiposActivity extends AppCompatActivity {

        private RecyclerView recyclerPartidos;
        private PartidosAdapter partidosAdapter;
        private ArrayList<Partido> listaPartidos = new ArrayList<>();
        private SQLiteHelper dbHelper;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_historial_equipos);

            recyclerPartidos = findViewById(R.id.recyclerHistorialPartidos);
            recyclerPartidos.setLayoutManager(new LinearLayoutManager(this));

            listaPartidos = new ArrayList<>();
            dbHelper = new SQLiteHelper(this);

            cargarPartidos();

            partidosAdapter = new PartidosAdapter(this, listaPartidos);
            recyclerPartidos.setAdapter(partidosAdapter);
        }

        private void cargarPartidos() {
            Cursor cursor = dbHelper.obtenerHistorialPartidos();
            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndexOrThrow("id")); // Asegúrate de usar el nombre correcto
                    String equipoLocal = cursor.getString(cursor.getColumnIndexOrThrow(SQLiteHelper.COLUMN_PARTIDO_EQUIPO_LOCAL));
                    String equipoVisitante = cursor.getString(cursor.getColumnIndexOrThrow(SQLiteHelper.COLUMN_PARTIDO_EQUIPO_VISITANTE));
                    double cantidadApuesta = cursor.getDouble(cursor.getColumnIndexOrThrow(SQLiteHelper.COLUMN_PARTIDO_CANTIDAD_APUESTA));
                    String ganador = cursor.getString(cursor.getColumnIndexOrThrow(SQLiteHelper.COLUMN_PARTIDO_GANADOR)); // Agrega esto

                    Partido partido = new Partido(id, equipoLocal, equipoVisitante, null, cantidadApuesta, ganador);
                    listaPartidos.add(partido);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }




    }
