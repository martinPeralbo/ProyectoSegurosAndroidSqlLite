package com.example.pruebasqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;

public class MostrarVendedor extends AppCompatActivity {

    EditText id,nombre,pass;
    CheckBox esAdmin;
    DeveloperBD db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_vendedor);

        int numeroId = Integer.parseInt(getIntent().getExtras().getString("idVendedor"));
        db = new DeveloperBD(getApplicationContext());
        Vendedor v = db.CargarVendedor(numeroId);
        id = (EditText)findViewById(R.id.idVendedor);
        nombre =(EditText) findViewById(R.id.NombreVendedor);
        pass = (EditText) findViewById(R.id.PassVendedor);
        esAdmin = (CheckBox) findViewById(R.id.checkBox2);

        id.setText(String.valueOf(v.getId()));
        nombre.setText(v.nombre);
        pass.setText(v.pass);
        esAdmin.setChecked(v.admin);


    }


}
