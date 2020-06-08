package com.example.pruebasqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegistrarVendedor extends AppCompatActivity {

    EditText mail,dni,nombre;
    CheckBox checkBox;
    TextView sup;
    DeveloperBD db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_vendedor);
        db = new DeveloperBD(getApplicationContext());
        nombre = (EditText) findViewById(R.id.NombreVendedor);
        dni = (EditText) findViewById(R.id.PassVendedor);
        mail = (EditText) findViewById(R.id.idVendedor);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
    }

    public void subirVendedor(View v){

        if(nombre.getText().toString().equals("") || dni.getText().toString().equals("")|| mail.getText().toString().equals("") ){
            Toast.makeText(getApplicationContext(),"No se pueden subir clientes  si hay campos vacios",Toast.LENGTH_SHORT).show();

        }
        else{
            // Toast.makeText(getApplicationContext(),"Subir cliente",Toast.LENGTH_SHORT).show();
            //db.agregarCliente(dni.getText().toString(), nombre.getText().toString(), mail.getText().toString(), mophone.getText().toString(),1);
            Intent i =  new Intent(this, VendedoresList.class);
            startActivity(i);
            Toast.makeText(getApplicationContext(),"Vendedor creado",Toast.LENGTH_SHORT).show();

        }

    }
}
