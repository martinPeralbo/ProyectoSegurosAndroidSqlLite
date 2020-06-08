package com.example.pruebasqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Contratoslist extends AppCompatActivity {


    DeveloperBD dbd;
    ArrayList<Contrato> listaContratos;
    ArrayList<String> listaNombres;
    boolean aceptado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contratoslist);



        final ListView list = findViewById(R.id.listaCliente);
        //Log.v("abcde",String.valueOf(list.getSolidColor()));
        dbd = new DeveloperBD(getApplicationContext());
        listaContratos = dbd.obtenerContratos();
        listaNombres = new ArrayList<String>() ;
        final Intent intent =  new Intent(this, Userlist.class);
        for (Contrato c : listaContratos) {

                listaNombres.add("IdSeguro: " + c.id + " IdVendedor: " + c.id_vendedor + " Dni: " + c.dni);
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
               /* Toast.makeText(getApplicationContext(),clickedItem,Toast.LENGTH_LONG).show();
                Intent i =  new Intent(getApplicationContext(), MostrarContrato.class);
                i.putExtra("nombreCliente",clickedItem);
                startActivity(i);*/
            }

        });
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {
                // TODO Auto-generated method stub

               /* String clickedItem = (String) list.getItemAtPosition(pos);

                //////////////////////////////////
                //Programar el borrado del cliente en la base de datos y una confirmacion de borrar
                Cliente cl = dbd.cargarCliente(clickedItem);
                dbd.desactivarCliente(cl);
                listaContratos = dbd.obtenerContratos();
                listaNombres = new ArrayList<String>();
                for (Contrato c : listaContratos) {

                        listaNombres.add(c.getDni());
                }

                startActivity(intent);
                Toast.makeText(getApplicationContext(), clickedItem + " BORRADO", Toast.LENGTH_SHORT).show();
                /////////////////////////////////
                */
                return true;
            }
        });




    }

    public void verClientes(View v){

        //Toast.makeText(getApplicationContext(),"No debe de haber campos vacios",Toast.LENGTH_SHORT).show();
        Intent i =  new Intent(this, Userlist.class);
        startActivity(i);


    }

    public void verVendedores(View v){

        //Toast.makeText(getApplicationContext(),"No debe de haber campos vacios",Toast.LENGTH_SHORT).show();
        Intent i =  new Intent(this, VendedoresList.class);
        startActivity(i);


    }
}
