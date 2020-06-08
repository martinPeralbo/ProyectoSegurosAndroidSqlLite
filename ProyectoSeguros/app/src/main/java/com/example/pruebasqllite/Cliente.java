package com.example.pruebasqllite;

import android.widget.EditText;

public class Cliente {
    String dni,nombre,mail,mophone;
    boolean activo;


    public Cliente(String dni, String nombre, String mail, String mophone, boolean activo) {
        this.dni = dni;
        this.nombre = nombre;
        this.mail = mail;
        this.mophone = mophone;
        this.activo = activo;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMophone() {
        return mophone;
    }

    public void setMophone(String mophone) {
        this.mophone = mophone;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", mail='" + mail + '\'' +
                ", mophone='" + mophone + '\'' +
                ", activo=" + activo +
                '}';
    }
}
