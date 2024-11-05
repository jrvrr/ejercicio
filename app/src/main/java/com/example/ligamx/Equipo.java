package com.example.ligamx;

public class Equipo {
    private String nombre;
    private String alias;
    private String locacion;
    private int logo;

    public Equipo(String nombre, String alias, String locacion, int logo) {
        this.nombre = nombre;
        this.alias = alias;
        this.locacion = locacion;
        this.logo = logo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getAlias() {
        return alias;
    }

    public String getLocacion() {
        return locacion;
    }

    public int getLogo() {
        return logo;
    }
}
