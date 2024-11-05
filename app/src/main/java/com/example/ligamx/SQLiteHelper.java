package com.example.ligamx;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "LigaMX.db";
    private static final int DATABASE_VERSION = 1;

    // Nombres de las tablas
    private static final String TABLE_EQUIPO = "equipos";
    private static final String TABLE_PARTIDO = "partidos";

    // Columnas de la tabla equipo
    private static final String COLUMN_EQUIPO_ID = "id";
    private static final String COLUMN_EQUIPO_NOMBRE = "nombre";
    private static final String COLUMN_EQUIPO_ALIAS = "alias";
    private static final String COLUMN_EQUIPO_LOCACION = "locacion";
    private static final String COLUMN_EQUIPO_LOGO = "logo";

    // Columnas de la tabla partido
    public static final String COLUMN_PARTIDO_ID = "id";
    public static final String COLUMN_PARTIDO_EQUIPO_LOCAL = "equipoLocal";
    public static final String COLUMN_PARTIDO_EQUIPO_VISITANTE = "equipoVisitante";
    public static final String COLUMN_PARTIDO_CANTIDAD_APUESTA = "cantidadApuesta";
    public static final String COLUMN_PARTIDO_GANADOR = "ganador";
    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Creación de la tabla equipo
        String CREATE_EQUIPOS_TABLE = "CREATE TABLE " + TABLE_EQUIPO + " (" +
                COLUMN_EQUIPO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_EQUIPO_NOMBRE + " TEXT NOT NULL, " +
                COLUMN_EQUIPO_ALIAS + " TEXT NOT NULL, " +
                COLUMN_EQUIPO_LOCACION + " TEXT NOT NULL, " +
                COLUMN_EQUIPO_LOGO + " INTEGER NOT NULL" + ");";
        db.execSQL(CREATE_EQUIPOS_TABLE);

        // Creación de la tabla partido
        String CREATE_PARTIDOS_TABLE = "CREATE TABLE " + TABLE_PARTIDO + " (" +
                COLUMN_PARTIDO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PARTIDO_EQUIPO_LOCAL + " TEXT NOT NULL, " +
                COLUMN_PARTIDO_EQUIPO_VISITANTE + " TEXT NOT NULL, " +
                COLUMN_PARTIDO_CANTIDAD_APUESTA + " REAL NOT NULL, " +
                COLUMN_PARTIDO_GANADOR + " TEXT" + ");";
        db.execSQL(CREATE_PARTIDOS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EQUIPO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PARTIDO);
        onCreate(db);
    }

    // Método para agregar equipo
    public void agregarEquipo(Equipo equipo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EQUIPO_NOMBRE, equipo.getNombre());
        values.put(COLUMN_EQUIPO_ALIAS, equipo.getAlias());
        values.put(COLUMN_EQUIPO_LOCACION, equipo.getLocacion());
        values.put(COLUMN_EQUIPO_LOGO, equipo.getLogo());

        long resultado = db.insert(TABLE_EQUIPO, null, values);
        if (resultado == -1) {
            Log.e("SQLiteHelper", "Error al insertar el equipo");
        } else {
            Log.d("SQLiteHelper", "Equipo insertado con éxito, ID: " + resultado);
        }
        db.close();
    }

    // Método para obtener todos los equipos
    public Cursor obtenerEquipos() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_EQUIPO, null);

        // Log para ver si hay datos
        if (cursor != null && cursor.getCount() > 0) {
            Log.d("SQLiteHelper", "Equipos encontrados: " + cursor.getCount());
        } else {
            Log.d("SQLiteHelper", "No se encontraron equipos en la base de datos");
        }
        return cursor;
    }

    // Método para registrar un partido
    public void registrarPartido(String equipoLocal, String equipoVisitante, double cantidadApuesta, String ganador) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PARTIDO_EQUIPO_LOCAL, equipoLocal);
        values.put(COLUMN_PARTIDO_EQUIPO_VISITANTE, equipoVisitante);
        values.put(COLUMN_PARTIDO_CANTIDAD_APUESTA, cantidadApuesta);
        values.put(COLUMN_PARTIDO_GANADOR, ganador);

        long result = db.insert(TABLE_PARTIDO, null, values);
        if (result == -1) {
            Log.e("SQLiteHelper", "Error al insertar el partido");
        } else {
            Log.d("SQLiteHelper", "Partido insertado con éxito, ID: " + result);
        }
        db.close();
    }

    // Método para obtener el historial de partidos
    public Cursor obtenerHistorialPartidos() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT p.id, p.equipoLocal, p.equipoVisitante, p.cantidadApuesta, p.ganador, " +
                "e1.locacion AS locacionLocal, e2.locacion AS locacionVisitante " +
                "FROM " + TABLE_PARTIDO + " p " +
                "JOIN " + TABLE_EQUIPO + " e1 ON p.equipoLocal = e1.nombre " +
                "JOIN " + TABLE_EQUIPO + " e2 ON p.equipoVisitante = e2.nombre", null);
    }


    public long registrarEquipo(String nombreEquipo, String alias, int logoSeleccionado, String locacion) {
        SQLiteDatabase db = this.getWritableDatabase(); // Obtener una instancia de la base de datos
        ContentValues values = new ContentValues(); // Crear un objeto ContentValues para almacenar los valores

        // Llenar el objeto ContentValues con los datos del equipo
        values.put(COLUMN_EQUIPO_NOMBRE, nombreEquipo);
        values.put(COLUMN_EQUIPO_ALIAS, alias);
        values.put(COLUMN_EQUIPO_LOGO, logoSeleccionado);
        values.put(COLUMN_EQUIPO_LOCACION, locacion);

        // Insertar la fila en la tabla equipos
        long result = db.insert(TABLE_EQUIPO, null, values);
        db.close(); // Cerrar la base de datos
        return result; // Retornar el ID de la nueva fila (o -1 si hubo un error)
    }
}
