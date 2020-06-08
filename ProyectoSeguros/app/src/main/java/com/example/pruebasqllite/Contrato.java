package com.example.pruebasqllite;

public class Contrato {

    int id, id_vendedor;
    String dni;


    public Contrato(int id, int id_vendedor, String dni){
        this.id=id;
        this.id_vendedor=id_vendedor;
        this.dni=dni;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdvendedor() {
        return id_vendedor;
    }

    public void setIdvendedor(int id_vendedor) {
        this.id_vendedor = id_vendedor;
    }

    public String getDNI() {
        return dni;
    }

    public void setDNI(String DNI) {
        this.dni = DNI;
    }

    public String toString(){ return "id: " + this.id + " id_vendedor : " + this.id_vendedor + " DNI: " + this.dni; };
}
