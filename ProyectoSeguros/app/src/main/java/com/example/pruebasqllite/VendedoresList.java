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

public class VendedoresList extends AppCompatActivity {


    DeveloperBD dbd;
    ArrayList<Vendedor> listaVendedores;
    ArrayList<String> listaNombres;
    boolean aceptado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendedores_list);


        //Cambiar todo a version vendedores

        //Lista usuarios

        final ListView list = findViewById(R.id.listaCliente);
        //Log.v("abcde",String.valueOf(list.getSolidColor()));
        dbd = new DeveloperBD(getApplicationContext());
        listaVendedores = dbd.obtenerVendedores();
        listaNombres = new ArrayList<String>() ;
        final Intent intent =  new Intent(this, VendedoresList.class);
        for (Vendedor c : listaVendedores) {
            if(c.getActivo())
                listaNombres.add(String.valueOf(c.getId()));
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
                Intent i =  new Intent(getApplicationContext(), MostrarVendedor.class);
                i.putExtra("idVendedor",clickedItem);
                //Tener cuidado que es un String
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
                Vendedor cl = dbd.CargarVendedor(Integer.parseInt(clickedItem));
                dbd.desactivarVendedor(cl);
                listaVendedores = dbd.obtenerVendedores();
                listaNombres = new ArrayList<String>();
                for (Vendedor c : listaVendedores) {
                    if (c.getActivo())
                        listaNombres.add(String.valueOf(c.getId()));
                }

                startActivity(intent);
                Toast.makeText(getApplicationContext(), clickedItem + " BORRADO", Toast.LENGTH_SHORT).show();
                /////////////////////////////////

                return true;
            }
        });




    }


    public void registrarVendedor(View v){

        //Toast.makeText(getApplicationContext(),"No debe de haber campos vacios",Toast.LENGTH_SHORT).show();
        Intent i =  new Intent(this, RegistrarVendedor.class);
        startActivity(i);


    }

    public void verContratos(View v){

        //Toast.makeText(getApplicationContext(),"No debe de haber campos vacios",Toast.LENGTH_SHORT).show();
        Intent i =  new Intent(this, Contratoslist.class);
        startActivity(i);


    }
    public void verClientes(View v){

        //Toast.makeText(getApplicationContext(),"No debe de haber campos vacios",Toast.LENGTH_SHORT).show();
        Intent i =  new Intent(this, Userlist.class);
        startActivity(i);


    }


}
