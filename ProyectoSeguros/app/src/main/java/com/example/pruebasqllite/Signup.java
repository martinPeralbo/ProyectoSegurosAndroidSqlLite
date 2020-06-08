package com.example.pruebasqllite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Signup extends AppCompatActivity
{
    EditText mail,mophone,dni,nombre;
    TextView sup;
    DeveloperBD db;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        db = new DeveloperBD(getApplicationContext());
        nombre = (EditText) findViewById(R.id.NombreVendedor);
        dni = (EditText) findViewById(R.id.PassVendedor);
        mail = (EditText) findViewById(R.id.idVendedor);
        mophone = (EditText) findViewById(R.id.mobphone);



       /* Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/LatoLight.ttf");
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(), "fonts/LatoRegular.ttf");
        mophone.setTypeface(custom_font);
        sup.setTypeface(custom_font1);
        dir.setTypeface(custom_font);
        nombre.setTypeface(custom_font);
        mail.setTypeface(custom_font);*/

    }


    public void subirCliente(View v){

        if(nombre.getText().toString().equals("") || dni.getText().toString().equals("")|| mail.getText().toString().equals("") || mophone.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"No se pueden subir clientes  si hay campos vacios",Toast.LENGTH_SHORT).show();

        }
        else{
           // Toast.makeText(getApplicationContext(),"Subir cliente",Toast.LENGTH_SHORT).show();
            db.agregarCliente(dni.getText().toString(), nombre.getText().toString(), mail.getText().toString(), mophone.getText().toString(),1);
            Intent i =  new Intent(this, Userlist.class);
            startActivity(i);
            Toast.makeText(getApplicationContext(),"Usuario creado",Toast.LENGTH_SHORT).show();
        }

    }

}
