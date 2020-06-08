package com.example.pruebasqllite;

public class Vendedor {

    int id;
    String nombre,pass;
    Boolean activo,admin;

    public Vendedor(int id,String nombre,String pass,boolean activo, boolean admin){
        this.id=id;
        this.nombre=nombre;
        this.pass=pass;
        this.activo=activo;
        this.admin=admin;
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

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public String toString(){ return "id: " + this.id + " nombre: " + this.nombre + " pass: " + this.pass + " activo: " + this.activo;}

}
