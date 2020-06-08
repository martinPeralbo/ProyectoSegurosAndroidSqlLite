package com.example.pruebasqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CrearContratoConCliente extends AppCompatActivity {
    public  int id;
    public String nombre;
    public String tipoSeguro;
    DeveloperBD dbd;
    String dniCliente;
    int vendedorId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_contrato_con_cliente);
        dbd= new DeveloperBD(getApplicationContext());
        tipoSeguro="VIVIENDA";
        Bundle b = getIntent().getExtras();
        dniCliente = b.getString("nombreCliente");
        vendedorId = b.getInt("vendedorActual");
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.tiposseguros, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
             tipoSeguro =  (String) spinner.getItemAtPosition(position);
                ((TextView) parentView.getChildAt(0)).setTextColor(Color.WHITE);
             //Toast.makeText(getApplicationContext(),tipoSeguro,Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here

            }

        });



    }



    public void crearContrato(View v){

        EditText idE= findViewById(R.id.idVendedor);
        EditText nombreE = findViewById(R.id.NombreVendedor);
        if(idE.getText().toString().equals("") || nombreE.getText().toString().equals("") ){
            Toast.makeText(getApplicationContext(),"No se deben dejar campos vacios",Toast.LENGTH_SHORT).show();

        }
        else {
            try {
                id = Integer.parseInt(idE.getText().toString());
                nombre = nombreE.getText().toString();
                Seguro.TipoSeguro expresionTipoSeguro = Seguro.TipoSeguro.valueOf(tipoSeguro);
                /////////////////////
                dbd.agregarSeguro(id,nombre,expresionTipoSeguro,1);
                dbd.AgregarContrato(vendedorId,id,dniCliente);
                //Faltaria insertar el contrato en la base de datos
                //////////////////////////
                Intent i =  new Intent(this, Userlist.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(),"Contrato creado",Toast.LENGTH_SHORT).show();

            }catch (NumberFormatException e){
                Toast.makeText(getApplicationContext(),"Ese id no cumple el formato",Toast.LENGTH_SHORT).show();

            }



        }




    }
}
