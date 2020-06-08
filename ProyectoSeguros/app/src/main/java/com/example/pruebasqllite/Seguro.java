package com.example.pruebasqllite;

public class Seguro {
    int id;
    String nombre;
    enum TipoSeguro{VIVIENDA,VIDA,COCHE};
    TipoSeguro tipoSeguro;
    boolean activo;

    public Seguro(int id, String nombre, TipoSeguro tipoSeguro,boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.tipoSeguro = tipoSeguro;
        this.activo = activo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoSeguro getTipoSeguro() {
        return tipoSeguro;
    }

    public void setTipoSeguro(TipoSeguro tipoSeguro) {
        this.tipoSeguro = tipoSeguro;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Seguro{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", tipoSeguro=" + tipoSeguro +
                ", activo=" + activo +
                '}';
    }
}
