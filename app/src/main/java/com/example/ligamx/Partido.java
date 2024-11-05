package com.example.ligamx;

public class Partido {
    private int id;
    private String equipoLocal;
    private String equipoVisitante;
    private String estadio;
    private double cantidadPartido;
    private String ganador;

    public Partido(int id, String equipoLocal, String equipoVisitante, String estadio, double cantidadPartido, String ganador) {
        this.id = id;
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.estadio = estadio;
        this.cantidadPartido = cantidadPartido;
        this.ganador = ganador;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public String getEquipoLocal() {
        return equipoLocal;
    }

    public String getEquipoVisitante() {
        return equipoVisitante;
    }

    public String getEstadio() {
        return estadio;
    }

    public double getCantidadPartido() {
        return cantidadPartido;
    }

    public String getGanador() {
        return ganador;
    }
}
