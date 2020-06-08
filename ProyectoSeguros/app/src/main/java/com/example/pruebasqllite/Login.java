package com.example.pruebasqllite;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Login extends AppCompatActivity
{
    EditText pswd,usrusr;
    TextView lin;
    DeveloperBD dbd;
    boolean esAdmin;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        esAdmin=false;
        lin = (TextView) findViewById(R.id.lin);
        usrusr = (EditText) findViewById(R.id.user);
        pswd = (EditText) findViewById(R.id.pswd);
       /* Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/LatoLight.ttf");
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(), "fonts/LatoRegular.ttf");
        lin.setTypeface(custom_font1);
        usrusr.setTypeface(custom_font);
        pswd.setTypeface(custom_font);*/
        dbd = new DeveloperBD( getApplicationContext());
       // dbd.agregarVendedor("1","1234","1",1);
        //int a = dbd.obtenerClientes().size();
        //Log.v("abcd",String.valueOf(a));

    }

    public void hacerLogin(View v){
        int idvendedor =0;
        boolean existe =false;
       ArrayList<Vendedor> vendedores = dbd.obtenerVendedores();
        if(vendedores.size() !=0) {
            for (Vendedor vend : vendedores) {
                if (usrusr.getText().toString().equals(vend.getNombre()) && pswd.getText().toString().equals(vend.getPass())) {
                    idvendedor=vend.id;
                    existe = true;
                    if(vend.admin){
                        esAdmin=true;
                    }
                }
            }
        }


        if(existe){
            //dbd.agregarCliente("3","pepe3","pepe@gmail.com","646356748",1);
            //Toast.makeText(getApplicationContext(),"No debe de haber campos vacios",Toast.LENGTH_SHORT).show();


            Intent i =  new Intent(this, Userlist.class);
            i.putExtra("esadmin",esAdmin);
            i.putExtra("vendedorActual",idvendedor);
            startActivity(i);
        }
        else{

            /////////////////////////////////////
            //Metodo para rellenar de forma rapida la base de datos y ver asi ejemplos
            //rellenarBasedeDatos();

            //////////////////////////////////////////////////////
             //Pruebas
            //dbd.agregarSeguro(1,"ggg", Seguro.TipoSeguro.COCHE,1);
           // Seguro c = dbd.cargarSeguro(1);
           // Log.v("abc",c.toString());
           // dbd.activarSeguro(c);
            //c.setNombre("cosas");
           // c.setTipoSeguro(Seguro.TipoSeguro.VIVIENDA);
            //dbd.actualizarSeguro(c);
           //Log.v("abc",c.toString());

            //dbd.activarCliente(c);
           // c=dbd.cargarCliente("12345678T");
           // Log.v("abc1",c.toString());

            Toast.makeText(getApplicationContext(),"Login incorrecto",Toast.LENGTH_SHORT).show();

        }

    }

    public void rellenarBasedeDatos(){
        dbd.AgregarVendedor(1,"pepe","a",1,1);
        dbd.AgregarVendedor(2,"luis","a",1,0);
        dbd.agregarCliente("51425364R","jose","jose@gmail.com","635849088",1);
        dbd.agregarCliente("51425464R","alberto","alberto@gmail.com","635842088",1);
        dbd.agregarCliente("51415464R","federico","federico@gmail.com","675842088",1);
        dbd.AgregarContrato(1,1,"51425364R");
        dbd.AgregarContrato(1,2,"51425364R");

    }
}
