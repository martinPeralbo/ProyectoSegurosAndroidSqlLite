package com.example.pruebasqllite;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Userlist extends AppCompatActivity {


    DeveloperBD dbd;
    ArrayList<Cliente> listaClientes;
    ArrayList<String> listaNombres;
    boolean aceptado;
    boolean esadmin;
    Button mostrarVendedores,mostrarContratos;
    static int vendedorActual;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);

        //Lista usuarios


        mostrarVendedores= findViewById(R.id.verVendedoresU);
        mostrarContratos = findViewById(R.id.verContratosU);
        try {
            esadmin = getIntent().getExtras().getBoolean("esadmin");
            vendedorActual=getIntent().getExtras().getInt("vendedorActual");
            if (!esadmin) {
                mostrarVendedores.setVisibility(View.INVISIBLE);
                mostrarContratos.setVisibility(View.INVISIBLE);

            }
        }catch (NullPointerException e){

        }
        final ListView list = findViewById(R.id.listaCliente);
        //Log.v("abcde",String.valueOf(list.getSolidColor()));
        dbd = new DeveloperBD(getApplicationContext());
        listaClientes = dbd.obtenerClientes();
        listaNombres = new ArrayList<String>() ;
        final Intent intent =  new Intent(this, Userlist.class);
        for (Cliente c : listaClientes) {
            if(c.isActivo())
            listaNombres.add(c.getDni());
        }
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, listaNombres){
            public View getView(int position, View convertView, ViewGroup parent){
                // Get the Item from ListView
                View view = super.getView(position, convertView, parent);

                // Initialize a TextView for ListView each Item
                TextView tv = (TextView) view.findViewById(android.R.id.text1);

                // Set the text color of TextView (ListView Item)
                tv.setTextColor(Color.WHITE);

                // Generate ListView Item using TextView
                return view;
            }
        };
        list.setAdapter(arrayAdapter);
        for (int i = 0; i < list.getChildCount(); i++) {
            ((TextView)list.getChildAt(i)).setTextColor(getResources().getColor(R.color.blanco));}
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String clickedItem=(String) list.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),clickedItem,Toast.LENGTH_LONG).show();
                Intent i =  new Intent(getApplicationContext(), CrearContratoConCliente.class);
                i.putExtra("nombreCliente",clickedItem);
                i.putExtra("vendedorActual",vendedorActual);
                startActivity(i);
            }

        });
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {
                // TODO Auto-generated method stub

                    String clickedItem = (String) list.getItemAtPosition(pos);

                    //////////////////////////////////
                    //Programar el borrado del cliente en la base de datos y una confirmacion de borrar
                    Cliente cl = dbd.cargarCliente(clickedItem);
                    dbd.desactivarCliente(cl);
                    listaClientes = dbd.obtenerClientes();
                    listaNombres = new ArrayList<String>();
                    for (Cliente c : listaClientes) {
                        if (c.isActivo())
                            listaNombres.add(c.getDni());
                    }

                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), clickedItem + " BORRADO", Toast.LENGTH_SHORT).show();
                    /////////////////////////////////

                return true;
            }
        });















    }

    public void aceptar() {
        Toast t=Toast.makeText(this,"Bienvenido a probar el programa.", Toast.LENGTH_SHORT);
        t.show();
    }

    public void cancelar() {
        //
    }


    public void registrarCliente(View v){

            //Toast.makeText(getApplicationContext(),"No debe de haber campos vacios",Toast.LENGTH_SHORT).show();
            Intent i =  new Intent(this, Signup.class);
            startActivity(i);


    }

    public void verVendedores(View v){

        //Toast.makeText(getApplicationContext(),"No debe de haber campos vacios",Toast.LENGTH_SHORT).show();
        Intent i =  new Intent(this, VendedoresList.class);
        startActivity(i);


    }
    public void verContratos(View v){

        //Toast.makeText(getApplicationContext(),"No debe de haber campos vacios",Toast.LENGTH_SHORT).show();
        Intent i =  new Intent(this, Contratoslist.class);
        startActivity(i);


    }

}
